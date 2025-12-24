package test.trashtests;

import com.soundcheck.launcher.Launcher;

import javax.sound.sampled.LineUnavailableException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestLauncher {
  public static void main(String[] args) throws LineUnavailableException {
    int count = 0;
    for (int i = 0; i < 50; i++) {
      Object[] objects = Launcher.play(
          "src/test/raag_files/miyan_ki_malhar/test2/mkm1.raag",
          false
      );

      List<String> seq = (List<String>) objects[0];
      AtomicBoolean containsStar = new AtomicBoolean(false);

      seq.forEach(swar -> {
        if (swar.contains("*")) {
          containsStar.set(true);
        }
      });

      if (containsStar.equals(true)) count++;
    }

    System.out.println(count);
  }
}
