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

  private PlayerInfo(int serialId) {
    this.serialId = serialId;
    this.skills = new HashMap<>();
    setupSkills();
  }

  private void setupSkills() {
    for (Skill skill : Skill.values()) {
      skills.put(skill, new SkillModel());
    }
  }

  private static final ThreadLocal<PlayerInfo> instance = new ThreadLocal<PlayerInfo>() {
    @Override
    protected PlayerInfo initialValue() {
      // TODO load from persistence system
      PlayerInfo playerInfo = createAndSetupPlayerInfo();
      return playerInfo;
    }

    private PlayerInfo createAndSetupPlayerInfo() {
      int randomSerial = new Random().nextInt(Integer.MAX_VALUE);
      log.info(String.format("rnd created for the player: %s", randomSerial));
      // TODO: mmm need to check the packet that's not handling the serial
      // correctly
      PlayerInfo playerInfo = new PlayerInfo(0);
      playerInfo.posX = 1496;
      playerInfo.posY = 1628;
      playerInfo.posZ = 0x10;
      playerInfo.dir = Direction.EAST;
      return playerInfo;
    }
  };

  public static PlayerInfo getInstance() {
    return instance.get();
  }

  private int serialId;

  public int getSerialId() {
    return serialId;
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

  public void setName(String name){
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

}
