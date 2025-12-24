package com.soundcheck.processor;

import com.soundcheck.factory.annotations.Component;
import com.soundcheck.processor.contracts.Flusher;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class FlusherImpl implements Flusher {

  @Override
  public void flush(Map<String, Distribution> derivations, Set<String> flushSet) {
    Map<String, List<Object[]>> locations = new HashMap<>();
    for (String var : derivations.keySet()) {
      Distribution<String> dist = derivations.get(var);
      for (String der : dist.getValues()) {
        for (String id : der.split("-")) {
          if (derivations.containsKey(id) || flushSet.contains(id)) {
            locations.computeIfAbsent(id, k -> new ArrayList<>()).add(new Object[]{var, der});
          }
        }
      }
    }

    Queue<String> queue = flushSet.stream().collect(Collectors.toCollection(LinkedList::new));

    while (!queue.isEmpty()) {
      String var = queue.remove();
      List<Object[]> locs = locations.get(var);
      for (Object[] location : locs) {
        String variable = (String) location[0];
        String derivation = (String) location[1];
        try {
          derivations.get(variable).remove(derivation);
        } catch (NullPointerException e) {
          continue;
        }
        if (derivations.get(variable).isEmpty()) {
          queue.add(variable);
          flushSet.add(variable);
        }
      }
    }

    flushSet.stream().filter(derivations::containsKey).forEach(derivations::remove);
  }
}
