
package audio;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {

  private Clip backgroundClip;

  public MusicPlayer() {}

  // this keeps the main theme playing in a loop
  public void playBackgroundMusic(String filePath) {
    try {
      File musicPath = new File(filePath);
      if (musicPath.exists()) {
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
        backgroundClip = AudioSystem.getClip();
        backgroundClip.open(audioInput);
        backgroundClip.start();
        backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
      }
    } catch (Exception e) {
      System.out.println("Music error: " + e.getMessage());
    }
  }

  // this fires off a quick sound effect that doesn't loop
  public void playSoundEffect(String filePath) {
    try {
      File soundPath = new File(filePath);
      if (soundPath.exists()) {
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
        Clip effectClip = AudioSystem.getClip();
        effectClip.open(audioInput);
        effectClip.start();
      }
    } catch (Exception e) {
      // stays quiet if a sound fails so the game doesn't crash
    }
  }
} // end class