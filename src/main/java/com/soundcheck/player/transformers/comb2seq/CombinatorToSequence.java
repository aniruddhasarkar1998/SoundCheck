package com.soundcheck.player.transformers.comb2seq;

public interface CombinatorToSequence {
  public String getSimpleSchemeFromCombinator(
      String notes,
      int sampleSize
  );
}
