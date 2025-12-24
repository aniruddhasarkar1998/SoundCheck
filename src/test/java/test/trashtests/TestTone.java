package test.trashtests;

import com.soundcheck.player.Tone;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class TestTone {
  public static void main(String[] args) throws LineUnavailableException {
//        String str = "ma Pa ni Dha Ni Ni Sa* Re* ma Pa ga ma Re Re Pa Pa ma Pa ga ma Re Sa Ni_ Sa";
    String str = "ma Pa ni Dha Ni Ni Sa*";
    String[] swars = str.split(" ");

    Object[] objects = Tone.sound(
        Arrays.asList(swars),
        30,
        0.1
    );

    byte[] buffer = (byte[]) objects[0];
    List<Double> frequencies = (List<Double>) objects[1];

    System.out.println(Arrays.toString(buffer));
    System.out.println(buffer.length);

    storeBuffer(
        "C:\\Users\\Aniruddha Sarkar\\Documents\\pyp files\\whatever\\",
        buffer
    );
  }

  static void storeBuffer(String folderPath, byte[] buffer) {
    String res = "";

    for (byte b : buffer) {
      res += b + " ";
    }

    try {
      Files.write(
          Paths.get(folderPath + "plot.txt"),
          res.getBytes(),
          StandardOpenOption.CREATE
      );
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}