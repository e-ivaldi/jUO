package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.SelectServerPacket;
import com.foolver.juo.util.DataReader;

public class SelectServerHandler extends AbstractRequestHandler<SelectServerPacket> {

  @Override
  public SelectServerPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      short serverIndex = dataReader.readShort();
      return new SelectServerPacket(serverIndex);
    });
  }

}
