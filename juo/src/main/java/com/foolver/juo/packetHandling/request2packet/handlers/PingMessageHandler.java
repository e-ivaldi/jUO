package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.shared.PingMessagePacket;

public class PingMessageHandler extends AbstractRequestHandler<PingMessagePacket> {

  @Override
  public PingMessagePacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      byte sequence = getByteFromInputStream(is);
      return new PingMessagePacket(sequence);
    });
  }
}
