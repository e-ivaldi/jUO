package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.shared.WarModePacket;
import com.foolver.juo.util.DataReader;

public class WarModeHandler extends AbstractRequestHandler<WarModePacket> {

  @Override
  public WarModePacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
        boolean warModeActive = dataReader.readBool();
        dataReader.skip(3); // unknown
        return new WarModePacket(warModeActive);
      });

  }

}
