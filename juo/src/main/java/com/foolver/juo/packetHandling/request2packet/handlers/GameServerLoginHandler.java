package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.GameServerLoginPacket;

public class GameServerLoginHandler extends AbstractRequestHandler<GameServerLoginPacket> {

  @Override
  public GameServerLoginPacket handle(InputStream is) throws PacketHandlingException {

    int key;
    String username;
    String password;    
  
    try {
      key = getIntFromInputStream(is);
      username = getFixedSizeStringFromInputStream(is, 30);
      password = getFixedSizeStringFromInputStream(is, 30);
    } catch (IOException e) {
      throw new PacketHandlingException(String.format("unable to handler packet %s", this.getClass().getSimpleName()),
          e);
    }

    return new GameServerLoginPacket(key, username, password);

  }

}
