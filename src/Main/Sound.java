package Main;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundUrl = new URL[30];

    public Sound() {
        soundUrl[0] = getClass().getResource("/sound/Coin.wav");
        soundUrl[1] = getClass().getResource("/sound/Powerup.wav");
        soundUrl[2] = getClass().getResource("/sound/swoop1.wav");
        soundUrl[3] = getClass().getResource("/sound/Dream Sakura-remix.wav");
        soundUrl[4] = getClass().getResource("/sound/Boss Battle WAV.wav");
        soundUrl[5] = getClass().getResource("/sound/Flash of Light_2.wav");
        soundUrl[6] = getClass().getResource("/sound/Winning Sound WAV.wav");
        soundUrl[7] = getClass().getResource("/sound/Special Event WAV.wav");
    }



    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {

        clip.start();
    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();
    }
}
