package com.soundcheck.raag.models;

public class Configurations {
  private int beatsPerCycle;
  private int numCycles;
  private String low;
  private String high;
  private String outFileName = "final";
  private String start = "Start";
  private double baseFrequency = 360.0;
  private int msec = 140;
  private double volume = 0.2;
  private String playFileName = "";
  private String wavFileName = "";

  public int getBeatsPerCycle() {
    return beatsPerCycle;
  }

  public int getNumCycles() {
    return numCycles;
  }

  public String getLow() {
    return low;
  }

  public String getHigh() {
    return high;
  }

  public String getOutFileName() {
    return outFileName;
  }

  public String getStart() {
    return start;
  }

  public double getBaseFrequency() {
    return baseFrequency;
  }

  public int getMsec() {
    return msec;
  }

  public double getVolume() {
    return volume;
  }

  public String getPlayFileName() {
    return playFileName;
  }

  public String getWavFileName() {
    return wavFileName;
  }

  public Configurations(
      int beatsPerCycle, int numCycles, String low, String high,
      String outFileName, String start, double baseFrequency,
      int msec, double volume, String playFileName, String wavFileName
  ) {
    this.beatsPerCycle = beatsPerCycle;
    this.numCycles = numCycles;
    this.low = low;
    this.high = high;
    this.outFileName = outFileName;
    this.start = start;
    this.baseFrequency = baseFrequency;
    this.msec = msec;
    this.volume = volume;
    this.playFileName = playFileName;
    this.wavFileName = wavFileName;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return "beatsPerCycle: " + beatsPerCycle + ";\n" +
        "numCycles: " + numCycles + ";\n" +
        "low: " + low + ";\n" +
        "high: " + high + ";\n" +
        "outFileName: " + outFileName + ";\n" +
        "start: " + start + ";\n" +
        "baseFrequency: " + baseFrequency + ";\n" +
        "msec: " + msec + ";\n" +
        "volume: " + volume + ";\n" +
        "playFileName: " + playFileName + ";\n\n";
  }

  public static class Builder {

    private int beatsPerCycle;
    private int numCycles;
    private String low;
    private String high;
    private String outFileName = "final";
    private String start = "Start";
    private double baseFrequency = 360.0;
    private int msec = 140;
    private double volume = 0.2;
    private String playFileName = "";
    private String wavFileName = "";

    public Builder setBeatsPerCycle(int beatsPerCycle) {
      this.beatsPerCycle = beatsPerCycle;
      return this;
    }

    public Builder setNumCycles(int numCycles) {
      this.numCycles = numCycles;
      return this;
    }

    public Builder setLow(String low) {
      this.low = low;
      return this;
    }

    public Builder setHigh(String high) {
      this.high = high;
      return this;
    }

    public Builder setOutFileName(String outFileName) {
      this.outFileName = outFileName;
      return this;
    }

    public Builder setStart(String start) {
      this.start = start;
      return this;
    }

    public Builder setBaseFrequency(double baseFrequency) {
      this.baseFrequency = baseFrequency;
      return this;
    }

    public Builder setMsec(int msec) {
      this.msec = msec;
      return this;
    }

    public Builder setVolume(double volume) {
      this.volume = volume;
      return this;
    }

    public Builder setPlayFileName(String playFileName) {
      this.playFileName = playFileName;
      return this;
    }

    public Builder setWavFileName(String wavFileName) {
      this.wavFileName = wavFileName;
      return this;
    }

    public Configurations build() {
      return new Configurations(beatsPerCycle, numCycles, low, high, outFileName,
          start, baseFrequency, msec, volume, playFileName, wavFileName);
    }

  }

}
