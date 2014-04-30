package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.shared.PingMessagePacket;
import com.foolver.juo.util.DataReader;

public class PingMessageHandler extends AbstractRequestHandler<PingMessagePacket> {

  @Override
  public PingMessagePacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      byte sequence = dataReader.readByte();
      return new PingMessagePacket(sequence);
    });
  }
}
