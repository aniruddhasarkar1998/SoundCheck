package com.soundcheck.player.transformers;

import java.util.List;

public interface DigitsToNotes {
  public List<String> transform(
      String digits,
      String swar,
      List<String> ascent,
      List<String> descent
  );
}
