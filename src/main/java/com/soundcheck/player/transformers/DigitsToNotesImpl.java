package com.soundcheck.player.transformers;

import com.soundcheck.factory.annotations.Autowired;
import com.soundcheck.factory.annotations.Component;
import com.soundcheck.player.transformers.comb2seq.CombinatorToSequence;

import java.util.List;

@Component
public class DigitsToNotesImpl implements DigitsToNotes {

  @Autowired
  private SchemeToSequence schemeToSequence;

  @Autowired
  private CombinatorToSequence combinatorToSequence;

  @Override
  public List<String> transform(String scheme, String swar, List<String> ascent, List<String> descent) {
    if (scheme.contains("(")) {
      scheme = scheme.substring(1, scheme.length() - 1)
          .replace(" ", "");
      String[] strArray = scheme.split(",");
      String notes = strArray[0];
      int sampleSize = Integer.parseInt(strArray[1]);

      scheme = combinatorToSequence.getSimpleSchemeFromCombinator(notes, sampleSize);
    }

    return schemeToSequence.getSequenceFromSimpleScheme(scheme, swar, ascent, descent);
  }
}
