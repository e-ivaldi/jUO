package com.foolver.juo.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.packets.utils.Direction;
import com.foolver.juo.packetHandling.packets.utils.Skill;

public class PlayerInfo {

  private static final Logger log = LoggerFactory.getLogger(PlayerInfo.class);

  private short posX;
  private short posY;
  private byte posZ;
  private Direction dir;
  private String name;
  private boolean skillsRequested = false; // remove this solution asap pls,
  private short skinColor;
  private Map<Skill, SkillModel> skills;

  private int serialId;
  private int internalSerialId;

  public PlayerInfo() {
    this.skills = new HashMap<>();
    setupSkills();
    int randomSerial = new Random().nextInt(5000);
    log.info(String.format("rnd created for the player: %s", randomSerial));
    // TODO: mmm need to check the packet that's not handling the serial
    // correctly
    this.serialId = 0;
    this.internalSerialId = randomSerial;
    this.posX = 1496;
    this.posY = 1628;
    this.posZ = 10;
    this.dir = Direction.EAST;
  }

  private void setupSkills() {
    for (Skill skill : Skill.values()) {
      skills.put(skill, new SkillModel());
    }
  }

  public int getSerialId() {
    return serialId;
  }

  public int getInternalSerialId() {
    return internalSerialId;
  }

  public short getPosX() {
    return posX;
  }

  public void setPosX(short posX) {
    this.posX = posX;
  }

  public short getPosY() {
    return posY;
  }

  public void setPosY(short posY) {
    this.posY = posY;
  }

  public byte getPosZ() {
    return posZ;
  }

  public void setPosZ(byte posZ) {
    this.posZ = posZ;
  }

  public Direction getDir() {
    return dir;
  }

  public void setDir(Direction dir) {
    this.dir = dir;
  }

  public void incrementX() {
    posX++;
  }

  public void decrementX() {
    posX--;
  }

  public void incrementY() {
    posY++;

  }

  public void decrementY() {
    posY--;
  }

  public boolean isSkillsRequested() {
    return skillsRequested;
  }

  public void setSkillsRequested(boolean skillsRequested) {
    this.skillsRequested = skillsRequested;
  }

  public SkillModel getSkill(Skill skill) {
    return skills.get(skill);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public short getSkinColor() {
    return skinColor;
  }

  public void setSkinColor(short skinColor) {
    this.skinColor = skinColor;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + internalSerialId;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PlayerInfo other = (PlayerInfo) obj;
    if (internalSerialId != other.internalSerialId)
      return false;
    return true;
  }

}
