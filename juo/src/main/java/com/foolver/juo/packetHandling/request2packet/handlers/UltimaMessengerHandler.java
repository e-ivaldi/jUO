package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.UltimaMessengerPacket;
import com.foolver.juo.util.DataReader;

public class UltimaMessengerHandler extends AbstractRequestHandler<UltimaMessengerPacket> {

  @Override
  public UltimaMessengerPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      int id1 = dataReader.readInt();
      int id2 = dataReader.readInt();
      return new UltimaMessengerPacket(id1, id2);
    });
  }
}
