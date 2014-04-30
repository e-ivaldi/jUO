package com.foolver.juo.packetHandling.request2packet.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.SpeechRequestPacket;
import com.foolver.juo.util.DataReader;

public class SpeechRequestHandler extends AbstractRequestHandler<SpeechRequestPacket> {

  private static final Logger log = LoggerFactory.getLogger(SpeechRequestHandler.class);

  @Override
  public SpeechRequestPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      short packetLength = dataReader.readShort();
      dataReader.skip(1); //type
      dataReader.skip(2); // color
      dataReader.skip(2); // font
      dataReader.skip(4); // language - string
      String text = dataReader.readString(packetLength - 12);
      log.info(String.format("text: %s", text));
      return new SpeechRequestPacket(text);
    });

  }

}
