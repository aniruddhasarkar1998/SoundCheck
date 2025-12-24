package com.soundcheck.processor;

import com.soundcheck.factory.annotations.Component;
import com.soundcheck.player.Sargam;
import com.soundcheck.player.transformers.DigitsToNotes;
import com.soundcheck.player.transformers.DigitsToNotesImpl;
import com.soundcheck.processor.contracts.Transformation;
import com.soundcheck.raag.models.Syntax;

import java.util.*;

@Component
public class TransformationImpl implements Transformation {
  private Syntax syntax;
  private Set<String> flushSet = new HashSet<>();
  private Map<String, Boolean> schemeInRange = new HashMap<>();

  private DigitsToNotes digitsToNotes = new DigitsToNotesImpl();

  public DigitsToNotes getDigitsToNotes() {
    return digitsToNotes;
  }

  public void setDigitsToNotes(DigitsToNotes digitsToNotes) {
    this.digitsToNotes = digitsToNotes;
  }

  @Override
  public Set<String> transform(Syntax syntax, String low, String high) {
    this.syntax = syntax;
    Map<String, Distribution> derivations = syntax.getDerivations();

    Queue<Distribution<String>> queue = new LinkedList<>();
    for (Distribution<String> dist : derivations.values()) {
      queue.add(dist);
    }

    while (!queue.isEmpty()) {
      Distribution<String> dist = queue.remove();
      for (String ders : dist.getValues()) {
        for (String str : ders.split("-")) {
          if (!Sargam.swar.containsKey(str) && !derivations.containsKey(str)) {
            if (str.contains("*") && derivations.containsKey(str.replace("*", ""))) {
              queue = editQueue(queue, str.replace("*", ""), '*', low, high);
            } else if (str.contains("_") && derivations.containsKey(str.replace("_", ""))) {
              queue = editQueue(queue, str.replace("_", ""), '_', low, high);
            }
          }
        }
      }
    }

    return flushSet;
  }

  private Queue<Distribution<String>> editQueue(
      Queue<Distribution<String>> queue, String var, char appendix, String low, String high
  ) {
    String newVar = var + appendix;

    Map<String, Distribution> derivations = syntax.getDerivations();

    Distribution<String> oldDist = derivations.get(var);
    Distribution<String> newDist = new Distribution<>();

    for (String der : oldDist.getValues()) {
      String currDer = "";
      boolean hasOutOfRange = false;

      String oldDer = der;

      for (String str : der.split("-")) {
        if (appendix == '_') {
          String id = "";
          if (str.contains("(")) {
            String[] parts = str.split("\\(");
            String schemeCalled = parts[0];
            String swar = parts[1].substring(0, parts[1].length() - 1);

            String changedSwar = downgrade(swar);

            if (changedSwar == null) id = null;
            else id = schemeCalled + "(" + changedSwar + ")";

            if (!inRange(changedSwar, low, high)) {
              id = null;
            }
          } else {
            id = downgrade(str);
          }

          if (id == null) {
            hasOutOfRange = true;
            break;
          } else if (Sargam.swar.containsKey(id)) {
            if (inRange(id, low, high)) currDer += id + "-";
            else {
              hasOutOfRange = true;
              break;
            }
          } else {
            currDer += id + "-";
          }
        } else if (appendix == '*') {
          String id = "";
          if (str.contains("(")) {
            String[] parts = str.split("\\(");
            String schemeCalled = parts[0];
            String swar = parts[1].substring(0, parts[1].length() - 1);

            String changedSwar = upgrade(swar);
            if (changedSwar == null) id = null;
            else id = schemeCalled + "(" + changedSwar + ")";

            if (!inRange(changedSwar, low, high)) {
              id = null;
            }
          } else {
            id = upgrade(str);
          }

          if (id == null) {
            hasOutOfRange = true;
            break;
          } else if (Sargam.swar.containsKey(id)) {
            if (inRange(id, low, high)) currDer += id + "-";
            else {
              hasOutOfRange = true;
              break;
            }
          } else {
            currDer += id + "-";
          }
        }
      }

      if (!hasOutOfRange) {
        currDer = currDer.substring(0, currDer.length() - 1);
        newDist.add(currDer, oldDist.get(oldDer));
      }
    }

    if (newDist.isEmpty()) {
      flushSet.add(newVar);
    } else {
      queue.add(newDist);
      derivations.put(newVar, newDist);
    }

    return queue;
  }

  private boolean checkRange(String schemeCalled, String swar, String low, String high) {
    String key = schemeCalled + "(" + swar + ")";
    if (schemeInRange.containsKey(key)) {
      return schemeInRange.get(key);
    } else {
      Distribution<String> scheme = syntax.getSchemes().get(schemeCalled);
      boolean hasOutOfRange = false;

      for (String digits : scheme.getValues()) {
        List<String> notes = digitsToNotes.transform(digits, swar,
            syntax.getAscent(), syntax.getDescent());

        for (String note : notes) {
          if (Sargam.swar.get(note) < Sargam.swar.get(low) ||
              Sargam.swar.get(note) > Sargam.swar.get(high)) {
            hasOutOfRange = true;
            break;
          }
        }

        if (hasOutOfRange) break;
      }

      schemeInRange.put(key, hasOutOfRange);
      return hasOutOfRange;
    }
  }

  private String upgrade(String str) {
    char last = str.charAt(str.length() - 1);
    if (last == '*') return null;
    if (last == '_') {
      return str.replace("_", "");
    } else {
      return str + "*";
    }
  }

  private String downgrade(String str) {
    char last = str.charAt(str.length() - 1);
    if (last == '_') return null;
    if (last == '*') {
      return str.replace("*", "");
    } else {
      return str + "_";
    }
  }

  private boolean inRange(String swar, String low, String high) {
    if (!Sargam.swar.containsKey(swar)) return false;
    return Sargam.swar.get(swar) <= Sargam.swar.get(high) &&
        Sargam.swar.get(swar) >= Sargam.swar.get(low);
  }
}
