package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.GameServerLoginPacket;
import com.foolver.juo.util.DataReader;

public class GameServerLoginHandler extends AbstractRequestHandler<GameServerLoginPacket> {

  @Override
  public GameServerLoginPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      int key = dataReader.readInt();
      String username = dataReader.readString(30);
      String password = dataReader.readString(30);
      return new GameServerLoginPacket(key, username, password);
    });

  }

}
