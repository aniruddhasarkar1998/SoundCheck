package com.soundcheck.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Distribution<T> {
  private Map<T, Double> distribution;
  private double distSum;
  private T def;
  private double maxProbability;

  public Distribution() {
    distribution = new HashMap<>();
    distSum = 0;
    maxProbability = -1;
  }

  public Set<T> getValues() {
    return distribution.keySet();
  }

  public void add(T value, double probability) {
    if (distribution.containsKey(value)) {
      distSum -= distribution.get(value);
    }

    this.distribution.put(value, probability);
    distSum += probability;

    if (probability > maxProbability) {
      maxProbability = probability;
      def = value;
    }
  }

  public void remove(String value) throws NullPointerException {
    if (!distribution.containsKey(value)) {
      throw new NullPointerException("Value:" + value + " not present in distribution.");
    } else {
      distSum -= distribution.remove(value);
    }
  }

  public Map<T, Double> getDistribution() {
    return distribution;
  }

  public void setDistribution(Map<T, Double> distribution) {
    this.distribution = distribution;
  }

  public Double get(T value) {
    return distribution.get(value);
  }

  public T sample() {
    double rand = Math.random();
    double ratio = 1.0f / distSum;
    double tempDist = 0;
    for (T i : distribution.keySet()) {
      tempDist += distribution.get(i);
      if (rand / ratio <= tempDist) {
        return i;
      }
    }
    return def;
  }

  public String keyString(T key) {
    return key.toString();
  }

  public boolean isEmpty() {
    return distribution.isEmpty();
  }

  public String toString() {
    String res = "";

    for (Map.Entry<T, Double> entry : distribution.entrySet()) {
      res += keyString(entry.getKey()) + ", " + entry.getValue() + " | ";
    }

    if (res.length() > 2) {
      res = res.substring(0, res.length() - 3);
    }

    return res;
  }
}
