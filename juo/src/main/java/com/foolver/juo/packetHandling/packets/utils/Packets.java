package com.foolver.juo.packetHandling.packets.utils;

import com.foolver.juo.util.ByteUtil;

public enum Packets {
  
  //REQUEST
  CREATE_CHARACTER((byte) 0x00), 
  LOGIN_REQUEST((byte) 0x80), 
  SERVER_LIST_REMOVE_ENTRY((byte) 0x60),
  RESTART_VERSION((byte) 0x5C),
  SELECT_SERVER((byte) 0xA0),
  CLIENT_SPY((byte) 0xA4),
  GAME_SERVER_LOGIN((byte) 0x91),
  LOGIN_CHARACTER((byte) 0x5D),
  DELETE_CHARACTER((byte) 0x83),
  MOVE_REQUEST((byte) 0x02),
  SPEECH_REQUEST((byte) 0xAD),
  SEND_HELP_TIP_REQUEST((byte) 0xB6),
  LOAD_AREA((byte) 0x63),
  LOAD_AREA_REQUEST((byte) 0x64),
  PING_MESSAGE((byte) 0x73),
  DOUBLE_CLICK((byte) 0x06),
  REQUEST_TIP_NOTICE((byte) 0xA7),
  REQUEST_SKILL((byte) 0x12),
  REQUEST_PLAYER_STATUS((byte) 0x34),
  SINGLE_CLICK((byte) 0x09),
  BOOKS((byte) 0x66),
  ULTIMA_MESSENGER((byte) 0xBB),
  CLIENT_VERSION((byte) 0xBD),
  RESYNC_REQUEST((byte) 0x22),
  REQUEST_CHAR_PROFILE((byte) 0xB8),
  SEND_SKILL((byte) 0x3A),
  WAR_MODE((byte) 0x72),
  REQUEST_ASSISTANCE((byte) 0x9B);
  
  Byte value;

  Packets(Byte value) {
    this.value = value;
  }

  public byte getValue() {
    return this.value;
  }

  public static Packets fromValue(byte b) {
    for (Packets d : values()) {
      if (d.getValue() == b) {
        return d;
      }
    }
    throw new IllegalArgumentException(String.format("unable to find the packet %s", ByteUtil.getPrintable(b)));
  }
}
