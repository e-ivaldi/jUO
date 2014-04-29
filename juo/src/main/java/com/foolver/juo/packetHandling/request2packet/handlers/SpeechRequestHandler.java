package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.SpeechRequestPacket;

public class SpeechRequestHandler extends AbstractRequestHandler<SpeechRequestPacket> {

  private static final Logger log = LoggerFactory.getLogger(SpeechRequestHandler.class);

  @Override
  public SpeechRequestPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      short packetLength = getShortFromInputStream(is);
      skypBytes(is, 1); //type
      skypBytes(is, 2); // color
      skypBytes(is, 2); // font
      skypBytes(is, 4); // language - string
      String text = getFixedSizeStringFromInputStream(is, (int) packetLength - 12);
      log.info(String.format("text: %s", text));
      return new SpeechRequestPacket(text);
    });

  }

}
