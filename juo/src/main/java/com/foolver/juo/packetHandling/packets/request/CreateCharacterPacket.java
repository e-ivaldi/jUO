package com.foolver.juo.packetHandling.packets.request;

import com.foolver.juo.packetHandling.packets.utils.Skill;

public class CreateCharacterPacket extends AbstractRequestPacket {

  private String name;

  private Skill skill1;
  private byte skill1Value;

  private Skill skill2;
  private byte skill2Value;

  private Skill skill3;
  private byte skill3Value;

  private short skinColor;

  public CreateCharacterPacket(String name, Skill skill1, byte skill1Value, Skill skill2, byte skill2Value,
      Skill skill3, byte skill3Value, short skinColor) {
    this.name = name;
    this.skill1 = skill1;
    this.skill1Value = skill1Value;
    this.skill2 = skill2;
    this.skill2Value = skill2Value;
    this.skill3 = skill3;
    this.skill3Value = skill3Value;
    this.skinColor = skinColor;
  }

  public String getName() {
    return name;
  }

  public Skill getSkill1() {
    return skill1;
  }

  public byte getSkill1Value() {
    return skill1Value;
  }

  public Skill getSkill2() {
    return skill2;
  }

  public byte getSkill2Value() {
    return skill2Value;
  }

  public Skill getSkill3() {
    return skill3;
  }

  public byte getSkill3Value() {
    return skill3Value;
  }

  public short getSkinColor() {
    return skinColor;
  }

}
