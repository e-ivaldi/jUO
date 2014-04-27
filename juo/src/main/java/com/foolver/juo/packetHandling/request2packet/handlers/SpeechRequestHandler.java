package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.SpeechRequestPacket;

public class SpeechRequestHandler extends AbstractRequestHandler<SpeechRequestPacket> {

  @Override
  public SpeechRequestPacket handle(InputStream is) throws PacketHandlingException {

    String text;
    
    try {

      short packetLength = getShortFromInputStream(is);
      byte type = getByteFromInputStream(is);
      short color = getShortFromInputStream(is);
      short font = getShortFromInputStream(is);
      String language = getFixedSizeStringFromInputStream(is, 4);
      text = getFixedSizeStringFromInputStream(is, (int)packetLength - 11);

    } catch (IOException e) {
      throw new PacketHandlingException(String.format("unable to handler packet %s", this.getClass().getSimpleName()),
          e);
    }

    return new SpeechRequestPacket(text);

  }

}
