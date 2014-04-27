package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.GameServerLoginPacket;

public class GameServerLoginHandler extends AbstractRequestHandler<GameServerLoginPacket> {

  @Override
  public GameServerLoginPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      int key = getIntFromInputStream(is);
      String username = getFixedSizeStringFromInputStream(is, 30);
      String password = getFixedSizeStringFromInputStream(is, 30);
      return new GameServerLoginPacket(key, username, password);
    });

  }

}
