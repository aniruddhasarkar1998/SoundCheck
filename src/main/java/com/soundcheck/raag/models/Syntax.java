package com.soundcheck.raag.models;

import com.soundcheck.processor.Distribution;
import com.soundcheck.processor.ParserImpl;
import com.soundcheck.utils.ParserUtils;

import java.util.List;
import java.util.Map;

public class Syntax {

  private List<String> ascent;
  private List<String> descent;
  private Map<String, Distribution> derivations;
  private Map<String, Distribution> schemes;
  private String start;

  public List<String> getAscent() {
    return ascent;
  }

  public List<String> getDescent() {
    return descent;
  }

  public Map<String, Distribution> getDerivations() {
    return derivations;
  }

  public Map<String, Distribution> getSchemes() {
    return schemes;
  }

  public String getStart() {
    return start;
  }

  private Syntax(
      List<String> ascent, List<String> descent, Map<String, Distribution> derivations,
      Map<String, Distribution> schemes, String start
  ) {
    this.ascent = ascent;
    this.descent = descent;
    this.derivations = derivations;
    this.schemes = schemes;
    this.start = start;
  }

  @Override
  public String toString() {
    String res = "";

    if (schemes != null) {
      res += "palta {\n" +
          "\tsargam {\n" +
          "\t\tarohan: ";

      String ascentStr = ascent.toString();
      ascentStr = ascentStr.substring(1, ascentStr.length() - 1);

      res += ascentStr + ";\n\t\tavarohan: ";

      String descentStr = descent.toString();
      descentStr = descentStr.substring(1, descentStr.length() - 1);

      res += descentStr + ";\n\t}\n";

      for (Map.Entry<String, Distribution> entry : schemes.entrySet()) {
        res += "\t" + entry.getKey() + " -> " + entry.getValue().toString() + ";\n";
      }

      res += "}\n";
    }

    res += ParserUtils.getStringForSyntax(derivations, start);

    return res;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private List<String> ascent;
    private List<String> descent;
    private Map<String, Distribution> derivations;
    private Map<String, Distribution> schemes;
    private String start;

    public Builder setAscent(List<String> ascent) {
      this.ascent = ascent;
      return this;
    }

    public Builder setDescent(List<String> descent) {
      this.descent = descent;
      return this;
    }

    public Builder setDerivations(Map<String, Distribution> derivations) {
      this.derivations = derivations;
      return this;
    }

    public Builder setSchemes(Map<String, Distribution> schemes) {
      this.schemes = schemes;
      return this;
    }

    public Builder setStart(String start) {
      this.start = start;
      return this;
    }

    public Syntax build() {
      return new Syntax(ascent, descent, derivations, schemes, start);
    }
  }
}