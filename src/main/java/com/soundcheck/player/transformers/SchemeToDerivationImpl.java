package com.soundcheck.player.transformers;

import com.soundcheck.processor.Distribution;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class SchemeToDerivationImpl implements SchemeToDerivation {
  DigitsToNotes digitsToNotes;

  @Override
  public Distribution<String> transform(
      Distribution<String> schemeDist, String swar, List<String> ascent, List<String> descent
  ) {
    return schemeDist.getValues().stream().map(
        digits -> schemeDist.keyString(digits) + "-" + schemeDist.get(digits)
    ).map(entry -> {
      String[] strArray = entry.split("-");

      String digits = strArray[0];
      String probability = strArray[1];

      return digitsToNotes.transform(digits, swar, ascent, descent) + "-" + probability;
    }).collect(new Collector<String, Distribution<String>, Distribution<String>>() {

      @Override
      public Supplier<Distribution<String>> supplier() {
        return Distribution::new;
      }

      @Override
      public BiConsumer<Distribution<String>, String> accumulator() {
        return (distribution, entry) -> {
          String[] strArray = entry.split("-");

          String digits = strArray[0];
          String probability = strArray[1];

          distribution.add(digits, Double.parseDouble(probability));
        };
      }

      @Override
      public BinaryOperator<Distribution<String>> combiner() {
        return null;
      }

      @Override
      public Function<Distribution<String>, Distribution<String>> finisher() {
        return Function.identity();
      }

      @Override
      public Set<Characteristics> characteristics() {
        return null;
      }
    });
  }
}
