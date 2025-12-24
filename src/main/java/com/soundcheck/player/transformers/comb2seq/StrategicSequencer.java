package com.soundcheck.player.transformers.comb2seq;

import com.soundcheck.factory.annotations.Component;
import com.soundcheck.factory.annotations.Primary;
import com.soundcheck.processor.Distribution;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Primary
@Component
public class StrategicSequencer implements CombinatorToSequence {

  @Override
  public String getSimpleSchemeFromCombinator(String noteString, int sampleSize) {
    List<Integer> notes = Arrays.asList(noteString.split("")).stream().map(str -> Integer.parseInt(str))
        .collect(Collectors.toList());

    Collections.sort(notes);

    Random random = new Random();
    int index = random.nextInt(notes.size());

    String res = "";
    res += notes.get(index);

    Distribution<String> choices = new Distribution<>();
    choices.add("next", 1.0);
    choices.add("prev", 1.0);
    choices.add("same", 0.05);

    for (int i = 0; i < notes.size(); ++i) {
      if (index == 0) {
        choices.remove("prev");
      } else if (index == notes.size() - 1) {
        choices.remove("next");
      }

      String step = choices.sample();
      switch (step) {
        case "prev":
          index = index - 1;
          break;
        case "next":
          index = index + 1;
          break;
        default:
          break;
      }

      res += notes.get(index);
      if (!choices.getValues().contains("prev")) {
        choices.add("prev", 1.0);
      } else if (!choices.getValues().contains("next")) {
        choices.add("next", 1.0);
      }
    }

    return res;
  }
}
