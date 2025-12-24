package com.soundcheck.player;

import java.util.*;
import javax.sound.sampled.LineUnavailableException;

public class Sargam {
  private static double leap = Math.pow(2.0, (1.0 / 12.0));
  private static double baseFrequency = 400.0;

  public static HashMap<String, Double> swar = new HashMap<>();

  static {
    swar.put("Sa_", (baseFrequency / Math.pow(leap, 12.0)));
    swar.put("re_", (baseFrequency / Math.pow(leap, 11.0)));
    swar.put("Re_", (baseFrequency / Math.pow(leap, 10.0)));
    swar.put("ga_", (baseFrequency / Math.pow(leap, 9.0)));
    swar.put("Ga_", (baseFrequency / Math.pow(leap, 8.0)));
    swar.put("ma_", (baseFrequency / Math.pow(leap, 7.0)));
    swar.put("Ma_", (baseFrequency / Math.pow(leap, 6.0)));
    swar.put("Pa_", (baseFrequency / Math.pow(leap, 5.0)));
    swar.put("dha_", (baseFrequency / Math.pow(leap, 4.0)));
    swar.put("Dha_", (baseFrequency / Math.pow(leap, 3.0)));
    swar.put("ni_", (baseFrequency / Math.pow(leap, 2.0)));
    swar.put("Ni_", (baseFrequency / Math.pow(leap, 1.0)));
    swar.put("Sa", baseFrequency);
    swar.put("re", (baseFrequency * Math.pow(leap, 1.0)));
    swar.put("Re", (baseFrequency * Math.pow(leap, 2.0)));
    swar.put("ga", (baseFrequency * Math.pow(leap, 3.0)));
    swar.put("Ga", (baseFrequency * Math.pow(leap, 4.0)));
    swar.put("ma", (baseFrequency * Math.pow(leap, 5.0)));
    swar.put("Ma", (baseFrequency * Math.pow(leap, 6.0)));
    swar.put("Pa", (baseFrequency * Math.pow(leap, 7.0)));
    swar.put("dha", (baseFrequency * Math.pow(leap, 8.0)));
    swar.put("Dha", (baseFrequency * Math.pow(leap, 9.0)));
    swar.put("ni", (baseFrequency * Math.pow(leap, 10.0)));
    swar.put("Ni", (baseFrequency * Math.pow(leap, 11.0)));
    swar.put("Sa*", (baseFrequency * Math.pow(leap, 12.0)));
    swar.put("re*", (baseFrequency * Math.pow(leap, 13.0)));
    swar.put("Re*", (baseFrequency * Math.pow(leap, 14.0)));
    swar.put("ga*", (baseFrequency * Math.pow(leap, 15.0)));
    swar.put("Ga*", (baseFrequency * Math.pow(leap, 16.0)));
    swar.put("ma*", (baseFrequency * Math.pow(leap, 17.0)));
    swar.put("Ma*", (baseFrequency * Math.pow(leap, 18.0)));
    swar.put("Pa*", (baseFrequency * Math.pow(leap, 19.0)));
    swar.put("dha*", (baseFrequency * Math.pow(leap, 20.0)));
    swar.put("Dha*", (baseFrequency * Math.pow(leap, 21.0)));
    swar.put("ni*", (baseFrequency * Math.pow(leap, 22.0)));
    swar.put("Ni*", (baseFrequency * Math.pow(leap, 23.0)));
    swar.put("Sa**", (baseFrequency * Math.pow(leap, 24.0)));
  }

  public Sargam(double baseFrequency) {
    this.baseFrequency = baseFrequency;
    setSargam();
  }

  protected void setSargam() {
    swar.put("Sa_", (baseFrequency / Math.pow(leap, 12.0)));
    swar.put("re_", (baseFrequency / Math.pow(leap, 11.0)));
    swar.put("Re_", (baseFrequency / Math.pow(leap, 10.0)));
    swar.put("ga_", (baseFrequency / Math.pow(leap, 9.0)));
    swar.put("Ga_", (baseFrequency / Math.pow(leap, 8.0)));
    swar.put("ma_", (baseFrequency / Math.pow(leap, 7.0)));
    swar.put("Ma_", (baseFrequency / Math.pow(leap, 6.0)));
    swar.put("Pa_", (baseFrequency / Math.pow(leap, 5.0)));
    swar.put("dha_", (baseFrequency / Math.pow(leap, 4.0)));
    swar.put("Dha_", (baseFrequency / Math.pow(leap, 3.0)));
    swar.put("ni_", (baseFrequency / Math.pow(leap, 2.0)));
    swar.put("Ni_", (baseFrequency / Math.pow(leap, 1.0)));
    swar.put("Sa", baseFrequency);
    swar.put("re", (baseFrequency * Math.pow(leap, 1.0)));
    swar.put("Re", (baseFrequency * Math.pow(leap, 2.0)));
    swar.put("ga", (baseFrequency * Math.pow(leap, 3.0)));
    swar.put("Ga", (baseFrequency * Math.pow(leap, 4.0)));
    swar.put("ma", (baseFrequency * Math.pow(leap, 5.0)));
    swar.put("Ma", (baseFrequency * Math.pow(leap, 6.0)));
    swar.put("Pa", (baseFrequency * Math.pow(leap, 7.0)));
    swar.put("dha", (baseFrequency * Math.pow(leap, 8.0)));
    swar.put("Dha", (baseFrequency * Math.pow(leap, 9.0)));
    swar.put("ni", (baseFrequency * Math.pow(leap, 10.0)));
    swar.put("Ni", (baseFrequency * Math.pow(leap, 11.0)));
    swar.put("Sa*", (baseFrequency * Math.pow(leap, 12.0)));
    swar.put("re*", (baseFrequency * Math.pow(leap, 13.0)));
    swar.put("Re*", (baseFrequency * Math.pow(leap, 14.0)));
    swar.put("ga*", (baseFrequency * Math.pow(leap, 15.0)));
    swar.put("Ga*", (baseFrequency * Math.pow(leap, 16.0)));
    swar.put("ma*", (baseFrequency * Math.pow(leap, 17.0)));
    swar.put("Ma*", (baseFrequency * Math.pow(leap, 18.0)));
    swar.put("Pa*", (baseFrequency * Math.pow(leap, 19.0)));
    swar.put("dha*", (baseFrequency * Math.pow(leap, 20.0)));
    swar.put("Dha*", (baseFrequency * Math.pow(leap, 21.0)));
    swar.put("ni*", (baseFrequency * Math.pow(leap, 22.0)));
    swar.put("Ni*", (baseFrequency * Math.pow(leap, 23.0)));
    swar.put("Sa**", (baseFrequency * Math.pow(leap, 24.0)));
  }

  public static String[] arrange(String[] arohan, String[] avarohan) {
    int i = 0, j = avarohan.length - 1;
    List<String> list = new ArrayList<>();

    while (i < arohan.length && j >= 0) {
      if (swar.get(arohan[i]) < swar.get(avarohan[j])) {
        list.add(arohan[i]);
        i++;
      } else if (swar.get(arohan[i]) > swar.get(avarohan[j])) {
        list.add(avarohan[j]);
        j--;
      } else {
        list.add(arohan[i]);
        i++;
        j--;
      }
    }

    return list.toArray(new String[0]);
  }

  public void playSargam(int msec, double vol, String[] swaras) throws LineUnavailableException {
    for (int i = 0; i < swaras.length; i++) {
      Tone.sound(swar.get(swaras[i]), msec, vol);
    }
    for (int i = swaras.length - 2; i >= 0; i--) {
      Tone.sound(swar.get(swaras[i]), msec, vol);
    }
  }

  public void playNotes(int msec, double vol) throws LineUnavailableException {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter the notes: ");
    String[] swaras = scan.nextLine().split(" ");
    for (int i = 0; i < swaras.length; i++) {
      Tone.sound(swar.get(swaras[i]), msec, vol);
    }
    scan.close();
  }
}