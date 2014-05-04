package com.foolver.juo.game;

public class SkillModel {

  public enum SkillLockState {
    UP((byte) 0x00), DOWN((byte) 0x01), LOCKED((byte) 0x02);

    private byte value;

    private SkillLockState(byte value) {
      this.value = value;
    }

    public byte getValue() {
      return value;
    }
  }

  private short value = 0;
  private short rawValue = 0;
  private SkillLockState lockState = SkillLockState.UP;

  public short getValue() {
    return value;
  }

  public short getRawValue() {
    return rawValue;
  }

  public void setRawValue(short rawValue) {
    //TODO: for the moment let's keep things easy and use raw value and value as the same param
    // I am gonna fix this later on
    this.value = rawValue;
    this.rawValue = rawValue;
  }

  public SkillLockState getLockState() {
    return lockState;
  }

  public void setLockState(SkillLockState lockState) {
    this.lockState = lockState;
  }

  public void incrementValue() {
    this.value += 1;
  }

  public void incrementRawValue() {
    this.rawValue += 1;
  }

}
