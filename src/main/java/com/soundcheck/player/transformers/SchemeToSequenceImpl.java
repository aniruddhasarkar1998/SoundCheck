package com.soundcheck.player.transformers;

import com.soundcheck.factory.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SchemeToSequenceImpl implements SchemeToSequence {

  @Override
  public List<String> getSequenceFromSimpleScheme(
      String scheme, String swar, List<String> ascent, List<String> descent
  ) {
    List<String> notes = new ArrayList<>();
    notes.add(swar);
    int seedIndex;

    if (ascent.contains(swar)) {
      seedIndex = ascent.indexOf(swar);
    } else {
      if (swar.contains("*")) {
        swar = swar.replace("*", "");
        seedIndex = ascent.indexOf(swar) + ascent.size();
      } else {
        swar = swar.replace("_", "");
        seedIndex = ascent.indexOf(swar) - ascent.size();
      }
    }

    int startDigit = scheme.charAt(0) - 48;

    for (int i = 1; i < scheme.length(); i++) {
      int digit = scheme.charAt(i) - 48;
      int jump = digit - startDigit;

      int currIndex = seedIndex + jump;
      String currNote;

      try {
        currNote = ascent.get(currIndex);
      } catch (ArrayIndexOutOfBoundsException e) {
        if (currIndex >= ascent.size()) {
          currNote = ascent.get(currIndex % ascent.size()) + "*";
        } else {
          currNote = ascent.get(currIndex + ascent.size()) + "_";
        }
      }

      notes.add(currNote);
    }

    return notes;
  }
}
