package test.trashtests;

import com.soundcheck.launcher.Launcher;

import javax.sound.sampled.LineUnavailableException;

public class TestPlayer {
  public static void main(String[] args) throws LineUnavailableException {
    Launcher.play(
        "C:\\Users\\Aniruddha Sarkar\\Documents\\github\\SoundCheck\\src\\test\\raag_files\\miyan_ki_malhar\\test3\\mkm.raag",
        true
    );
  }
}
