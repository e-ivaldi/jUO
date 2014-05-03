package com.foolver.juo.packetHandling.packets.request;

public class SendSkillPacket extends AbstractRequestPacket {

  private short skillId;
  private byte skillLockState;

  public SendSkillPacket(short skillId, byte skillLockState) {
    this.skillId = skillId;
    this.skillLockState = skillLockState;
  }

  public short getSkillId() {
    return skillId;
  }

  public byte getSkillLockState() {
    return skillLockState;
  }

}
