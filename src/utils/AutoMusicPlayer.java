package utils;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AutoMusicPlayer {
    private volatile boolean isPlaying = true; // 控制播放的标志
    private volatile boolean isLooping = true; // 控制循环的标志

    public void BGMPlayer(String fileName) {
        try {
            // 1. 加载 OGG 文件（只加载一次）
            File oggFile = new File(fileName);
            AudioInputStream originalStream = AudioSystem.getAudioInputStream(oggFile);

            // 2. 获取原始音频格式并打印（确认是否为 VORBISENC）
            AudioFormat originalFormat = originalStream.getFormat();
            System.out.println("Original Format: " + originalFormat);

            // 3. 定义目标 PCM 格式（强制转换）
            AudioFormat targetFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,  // PCM 格式
                    originalFormat.getSampleRate(),   // 采样率（如 44100 Hz）
                    16,                               // 采样位数（16 位）
                    originalFormat.getChannels(),    // 声道数（如 2=立体声）
                    originalFormat.getChannels() * 2, // 每帧字节数（16位=2字节）
                    originalFormat.getSampleRate(),   // 帧速率
                    false                             // 是否大端序
            );

            // 4. 将音频流转换为 PCM 格式（只转换一次）
            AudioInputStream pcmStream = AudioSystem.getAudioInputStream(targetFormat, originalStream);

            // 5. 缓存音频数据到内存中
            byte[] audioData = pcmStream.readAllBytes();

            // 6. 使用 SourceDataLine 流式播放
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, targetFormat);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(targetFormat);

            while (isLooping) { // 循环播放
                line.start();

                // 7. 播放缓存的音频数据
                int offset = 0;
                while (offset < audioData.length && isPlaying) {
                    int bytesToWrite = Math.min(20480, audioData.length - offset);
                    line.write(audioData, offset, bytesToWrite);
                    offset += bytesToWrite;
                }

                // 8. 等待播放完成
                line.drain();

                // 如果停止播放，则退出循环
                if (!isPlaying) {
                    break;
                }
            }

            line.stop();
            line.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // 停止播放
    public void stop() {
        isPlaying = false;
        isLooping = false;
    }

    // 开始播放（可用于重新启动）
    public void play() {
        isPlaying = true;
    }

    // 设置是否循环
    public void setLooping(boolean looping) {
        isLooping = looping;
    }
}

