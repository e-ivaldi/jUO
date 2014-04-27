package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.ServerListRemoveEntryPacket;

public class ServerListRemoveEntryHandler extends AbstractRequestHandler<ServerListRemoveEntryPacket> {

  private static final Logger log = LoggerFactory.getLogger(ServerListRemoveEntryHandler.class);

  @Override
  public ServerListRemoveEntryPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      int serverIndex = getIntFromInputStream(is);
      log.debug(String.format("server index: %s", serverIndex));
      return new ServerListRemoveEntryPacket();
    });
  }

}
