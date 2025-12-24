package com.soundcheck.raag.models;

public class Raag {

  private Configurations configurations;

  private Syntax syntax;

  public Raag(Configurations configurations, Syntax syntax) {
    this.configurations = configurations;
    this.syntax = syntax;
  }

  public Configurations getConfigurations() {
    return configurations;
  }

  public Syntax getSyntax() {
    return syntax;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private Configurations configurations;

    private Syntax syntax;

    public Builder setConfigurations(Configurations configurations) {
      this.configurations = configurations;
      return this;
    }

    public Builder setSyntax(Syntax syntax) {
      this.syntax = syntax;
      return this;
    }

    public Raag build() {
      return new Raag(configurations, syntax);
    }

  }

}
