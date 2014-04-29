package com.foolver.juo;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.packets.utils.Direction;

public class PlayerInfo {

  private static final Logger log = LoggerFactory.getLogger(PlayerInfo.class);

  private short posX;
  private short posY;
  private byte  posZ;
  private Direction dir;
  
  private PlayerInfo(int serialId) {
    this.serialId = serialId;
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
      PlayerInfo playerInfo = new PlayerInfo(randomSerial);
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

}
