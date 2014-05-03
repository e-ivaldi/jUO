package com.foolver.juo.packetHandling.request2packet.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.DoubleClickPacket;
import com.foolver.juo.util.DataReader;

public class DoubleClickHandler extends AbstractRequestHandler<DoubleClickPacket> {

  private static final Logger log = LoggerFactory.getLogger(DoubleClickHandler.class);

  @Override
  public DoubleClickPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      int objectId = dataReader.readInt();
      log.info(String.format("user double clicked on object id: %s", objectId));
      return new DoubleClickPacket(objectId);
    });
  }

}
