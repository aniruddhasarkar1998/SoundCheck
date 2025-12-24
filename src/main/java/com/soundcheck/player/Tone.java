package com.soundcheck.player;

import javax.sound.sampled.*;
import java.util.List;
import java.util.stream.Collectors;

public class Tone {
  public static float SAMPLE_RATE = 20000f;

  public static byte[] sound(double hz, int msec, double vol) throws LineUnavailableException {
    if (hz <= 0) throw new IllegalArgumentException("Frequency less than or equal to zero");
    if (msec <= 0) throw new IllegalArgumentException("Duration less than zero");
    if (vol > 1.0 || vol < 0.0) throw new IllegalArgumentException("Volume out of range 0.0 - 1.0");

    byte[] buffer = new byte[(int) SAMPLE_RATE * msec / 1000];

    for (int i = 0; i < buffer.length; i++) {
      double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
      buffer[i] = (byte) (Math.sin(angle) * 127.0 * vol);
    }

    for (int i = 0; i < SAMPLE_RATE / 100.0 && i < buffer.length / 2; i++) {
      buffer[i] = (byte) (buffer[i] * i / (SAMPLE_RATE / 100.0));
      buffer[buffer.length - 1 - i] = (byte) (buffer[buffer.length - 1 - i] * i / (SAMPLE_RATE / 100.0));
    }

    AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
    SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
    sdl.open(af);
    sdl.start();
    sdl.write(buffer, 0, buffer.length);
    sdl.drain();
    sdl.close();

    return buffer;
  }

  public static Object[] sound(List<String> seq, int msec, double vol) throws LineUnavailableException {
    if (msec <= 0) throw new IllegalArgumentException("Duration less than zero");
    if (vol > 1.0 || vol < 0.0) throw new IllegalArgumentException("Volume out of range 0.0 - 1.0");

    byte[] buffer = new byte[(int) SAMPLE_RATE * msec / 1000 * seq.size()];
    int start = 0;

    List<Double> frequencies = seq.stream()
        .map(note -> Sargam.swar.get(note))
        .collect(Collectors.toList());

    for (double hz : frequencies) {
      int end = start + (int) SAMPLE_RATE * msec / 1000;

      for (int j = start; j < end; ++j) {
        double angle = (j - start) / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
        buffer[j] = (byte) (Math.sin(angle) * 127.0 * vol);
      }

//            for(int j = start; j < start + SAMPLE_RATE / 100 && j < end; ++j) {
//                buffer[j] = (byte)(buffer[j] * (j - start) / (SAMPLE_RATE / 100.0));
//                buffer[end - 1 - j] = (byte)(buffer[end - 1 - j] * (j - start) / (SAMPLE_RATE / 100.0));
//            }

      start = end;
    }

    AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
    SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
    sdl.open(af);
    sdl.start();
    sdl.write(buffer, 0, buffer.length);
    sdl.drain();
    sdl.close();

    return new Object[]{buffer, frequencies};
  }
}