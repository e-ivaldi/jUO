package com.foolver.juo.packetHandling.request2packet.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.shared.ClientVersionPacket;
import com.foolver.juo.util.DataReader;

public class ClientVersionHandler extends AbstractRequestHandler<ClientVersionPacket> {

  private static final Logger log = LoggerFactory.getLogger(ClientVersionHandler.class);

  @Override
  public ClientVersionPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      short length = dataReader.readShort();
      String version = dataReader.readString(length - 3);
      log.info(String.format("client sent version: %s", version));
      return new ClientVersionPacket(version);
    });
  }
}
