package com.foolver.juo;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerInfo {

  private static final Logger log = LoggerFactory.getLogger(PlayerInfoTest.class);

  private PlayerInfo(int serialId) {
    this.serialId = serialId;
  }

  private static final ThreadLocal<PlayerInfo> instance = new ThreadLocal<PlayerInfo>() {
    @Override
    protected PlayerInfo initialValue() {
      // TODO load from persistence system
      int randomSerial = new Random().nextInt(Integer.MAX_VALUE);
      log.info(String.format("rnd created for the player: %s", randomSerial));
      return new PlayerInfo(randomSerial);
    }
  };

  public static PlayerInfo getInstance() {
    return instance.get();
  }

  private int serialId;

  public int getSerialId() {
    return serialId;
  }

}
