package com.foolver.juo.packetHandling.request2packet.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.util.DataReader;

public class RequestCharProfileHandler extends AbstractRequestHandler<EmptyPacket> {

  private static final Logger log = LoggerFactory.getLogger(RequestCharProfileHandler.class);

  @Override
  public EmptyPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      short packetLength = dataReader.readShort();
      byte mode = dataReader.readByte();
      int id = dataReader.readInt();
      if (packetLength > 8) {
        log.info("update request");
        short cmdType = dataReader.readShort();
        short msgLength = dataReader.readShort();
        String msg = dataReader.readString(msgLength);
      }

      return new EmptyPacket();
    });

  }
}
