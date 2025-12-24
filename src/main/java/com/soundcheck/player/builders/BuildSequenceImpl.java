package com.soundcheck.player.builders;

import com.soundcheck.factory.annotations.Autowired;
import com.soundcheck.factory.annotations.Component;
import com.soundcheck.logger.CustomLogger;
import com.soundcheck.player.Sargam;
import com.soundcheck.player.transformers.DigitsToNotes;
import com.soundcheck.processor.Distribution;
import com.soundcheck.raag.models.Syntax;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Component
public class BuildSequenceImpl implements BuildSequence {

  @Autowired
  private DigitsToNotes digitsToNotes;

  @Autowired
  private CustomLogger logger;

  private String whatHappens;

  @Override
  public String getWhatHappens() {
    return whatHappens;
  }

  public void setWhatHappens(String whatHappens) {
    this.whatHappens = whatHappens;
  }

  public DigitsToNotes getDigitsToNotes() {
    return digitsToNotes;
  }

  public void setDigitsToNotes(DigitsToNotes digitsToNotes) {
    this.digitsToNotes = digitsToNotes;
  }

  @Override
  public List<String> getSequence(Syntax syntax, String start, int numNotes) {
    List<String> seq = new ArrayList<>();
    Map<String, Distribution> derivations = syntax.getDerivations();
    Map<String, Distribution> schemes = syntax.getSchemes();

    Stack<String> choices = new Stack<>();
    choices.push(start);
    int count = 0;

    System.out.println(start);

    while (count != numNotes) {
      if (choices.isEmpty()) {
        System.out.println(String.format("Stack got empty. So reinitializing with \"%s\" variable.", start));
        whatHappens += String.format("Stack got empty. So reinitializing with \"%s\" variable. \n", start);
        choices.push(start);
        whatHappens += "\n\n\n";
        continue;
      }

      String currChoice = choices.pop();
//            logger.addLog("currChoice: " + currChoice);
      whatHappens += "currChoice: " + currChoice + "\n";

      if (Sargam.swar.containsKey(currChoice)) {
        seq.add(currChoice);
//                logger.addLog("Note added to sequence: " + currChoice);
        whatHappens += "Note added to sequence: " + currChoice + "\n";
        ++count;
        whatHappens += "\n\n\n";
        continue;
      }

      if (currChoice.contains("(")) {
        String[] parts = currChoice.split("\\(");
        String schemeName = parts[0];
        String swar = parts[1].substring(0, parts[1].length() - 1);

        String sample = (String) schemes.get(schemeName).sample();

        List<String> notes = digitsToNotes.transform(
            sample,
            swar, syntax.getAscent(),
            syntax.getDescent()
        );

        System.out.println("Palta chosen ==> [scheme: " + sample +
            ",  notes: " + notes + "]");

        whatHappens += "Palta chosen ==> [scheme: " + sample +
            ",  notes: " + notes + "]" + "\n";

//                logger.addLog("Palta chosen ==> [scheme: " + sample +
//                        ",  notes: " + notes + "]");

        for (int i = notes.size() - 1; i >= 0; --i) {
          choices.push(notes.get(i));
        }

        whatHappens += "\n\n\n";
        continue;
      }

      Distribution<String> distribution = derivations.get(currChoice);
      String der = distribution.sample();

      String[] ids = der.split("-");

      for (int i = ids.length - 1; i >= 0; i--) {
        choices.push(ids[i]);
//                logger.addLog("pushed to stack: " + ids[i]);
        whatHappens += "pushed to stack: " + ids[i] + "\n";
      }

//            logger.addLog("stack: " + choices);
      whatHappens += "stack: " + choices + "\n";
      whatHappens += "\n\n";
    }

    whatHappens += "seq: " + seq;

    return seq;
  }
}
