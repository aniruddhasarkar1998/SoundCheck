package com.soundcheck.player;

import com.soundcheck.factory.context.FactoryContext;
import com.soundcheck.player.builders.BuildSequence;
import com.soundcheck.raag.models.Syntax;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Player extends Sargam {

  private Syntax syntax;
  private String start;
  private int numNotes;

  public Player(Double baseFrequency) {
    super(baseFrequency);
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public int getNumNotes() {
    return numNotes;
  }

  public void setNumNotes(int beatsPerCycle, int numCycles) {
    this.numNotes = numCycles * beatsPerCycle;
  }

  public Syntax getSyntax() {
    return syntax;
  }

  public void setSyntax(Syntax syntax) {
    this.syntax = syntax;
  }

  public List<String> play(
      int msec, double vol, String filePath, String playFileName, String wavFileName
  ) throws LineUnavailableException {
    Object[] objects = getSequence();
    List<String> seq = (List<String>) objects[0];
    String whatHappens = (String) objects[1];

    Object[] objects1 = Tone.sound(seq, msec, vol);
    byte[] buffer = (byte[]) objects1[0];
    if (!playFileName.isEmpty()) registerWhatHappens(whatHappens, filePath + "\\" + playFileName);
    if (!wavFileName.isEmpty()) writeToWAV(buffer, filePath + "\\" + wavFileName + ".wav");

    return seq;
  }

  public Object[] getSequence() {
    BuildSequence buildSequence = FactoryContext.getBean(BuildSequence.class);
    List<String> sequence = buildSequence.getSequence(syntax, start, numNotes);
    String whatHappens = buildSequence.getWhatHappens();
    return new Object[]{sequence, whatHappens};
  }

  private void writeToWAV(byte[] buffer, String filePath) {
    try {
      File file = new File(filePath);
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer));
      AudioSystem.write(inputStream, AudioFileFormat.Type.WAVE, file);
    } catch (IOException | UnsupportedAudioFileException e) {
      e.printStackTrace();
    }
  }

  private void registerWhatHappens(String whatHappens, String playFilePath) {
    try {
      Files.write(
          Paths.get(playFilePath),
          whatHappens.getBytes(),
          StandardOpenOption.CREATE,
          StandardOpenOption.TRUNCATE_EXISTING
      );
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
}