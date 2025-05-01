package Main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class AudioPlayer {
    public SourceDataLine line;  // 改用SourceDataLine更灵活
    public AudioInputStream convertedStream;

    public void setFile(int i, URL[] soundUrl) {
        try {
            // 1. 获取原始音频流
            AudioInputStream originalStream = AudioSystem.getAudioInputStream(soundUrl[i]);
            AudioFormat originalFormat = originalStream.getFormat();

            // 2. 定义32位目标格式（根据实际需求选择PCM_FLOAT或PCM_SIGNED）
            AudioFormat targetFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_FLOAT,  // 使用32位浮点
                    originalFormat.getSampleRate(),
                    32,                             // 32位深度
                    originalFormat.getChannels(),
                    originalFormat.getChannels() * 4, // 帧大小 = 32bit(4字节)*声道数
                    originalFormat.getSampleRate(),
                    false                           // 小端字节序（通常更常见）
            );

            // 3. 检查系统是否支持目标格式
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, targetFormat);
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("系统不支持32位浮点格式，尝试转换为16位PCM...");
                // 降级到16位格式
                targetFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        originalFormat.getSampleRate(),
                        16,
                        originalFormat.getChannels(),
                        originalFormat.getChannels() * 2,
                        originalFormat.getSampleRate(),
                        false
                );
            }

            // 4. 转换音频流到目标格式
            convertedStream = AudioSystem.getAudioInputStream(targetFormat, originalStream);

            // 5. 打开音频线路并播放
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(targetFormat);
            line.start();

            // 6. 启动播放线程（避免阻塞主线程）
            new Thread(() -> playAudio()).start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playAudio() {
        try {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = convertedStream.read(buffer)) != -1) {
                // 如果是32位浮点数据，可在此处进行音频处理
                if (line.getFormat().getEncoding() == AudioFormat.Encoding.PCM_FLOAT) {
                    processFloatAudio(buffer, bytesRead);
                }
                line.write(buffer, 0, bytesRead);
            }
            line.drain();
            line.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 32位浮点数据处理示例（例如增益调整）
    private void processFloatAudio(byte[] audioBytes, int length) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(audioBytes, 0, length);
        byteBuffer.order(line.getFormat().isBigEndian() ?
                ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);

        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        float[] samples = new float[floatBuffer.remaining()];
        floatBuffer.get(samples);

        // 示例处理：音量放大50%
        for (int i = 0; i < samples.length; i++) {
            samples[i] = Math.max(-1.0f, Math.min(1.0f, samples[i] * 1.5f));
        }

        // 将处理后的数据写回缓冲区
        floatBuffer.rewind();
        floatBuffer.put(samples);
    }

    public void stop() {
        if (line != null && line.isRunning()) {
            line.stop();
            line.close();
        }
    }
}
