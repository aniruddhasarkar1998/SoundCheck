package com.soundcheck.launcher;

import com.soundcheck.factory.context.FactoryContext;
import com.soundcheck.processor.contracts.RaagBuilder;
import com.soundcheck.raag.models.Configurations;
import com.soundcheck.player.Player;
import com.soundcheck.raag.models.Raag;
import com.soundcheck.raag.models.Syntax;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.util.Arrays;

public class Launcher {
  public static Object[] play(Object... args) {
    String filePath = (String) args[0];
    Boolean doPlay = (Boolean) args[1];
    RaagBuilder builder = FactoryContext.getBean(RaagBuilder.class);
    Raag raag = builder.buildRaag(filePath);

    try {
      return play(raag.getSyntax(), raag.getConfigurations(), doPlay, filePath);
    } catch (LineUnavailableException e) {
      throw new RuntimeException(e);
    }
  }

  public static Object[] play(
      Syntax syntax, Configurations configurations, boolean doPlay, String filePath
  ) throws LineUnavailableException {
    Double baseFrequency = configurations.getBaseFrequency();
    int msec = configurations.getMsec();
    double volume = configurations.getVolume();
    int beatsPerCycle = configurations.getBeatsPerCycle();
    int numCycles = configurations.getNumCycles();
    String playFileName = configurations.getPlayFileName();
    String wavFileName = configurations.getWavFileName();
    String start = configurations.getStart();

    Player player = FactoryContext.getBean(Player.class, Arrays.asList(Double.class), Arrays.asList(baseFrequency));

    if (doPlay) {
      player.setSyntax(syntax);
      player.setStart(start);
      player.setNumNotes(beatsPerCycle, numCycles);
      player.play(msec, volume, new File(filePath).getParent(),
          playFileName, wavFileName);
      return null;
    } else {
      player.setSyntax(syntax);
      player.setStart(start);
      player.setNumNotes(beatsPerCycle, numCycles);
      return player.getSequence();
    }
  }
}
