package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.LoginRequestPacket;

public class LoginRequestHandler extends AbstractRequestHandler<LoginRequestPacket> {

  @Override
  public LoginRequestPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      String username = getFixedSizeStringFromInputStream(is, 30);
      String password = getFixedSizeStringFromInputStream(is, 30);
      skypBytes(is, 1); // next login key
        return new LoginRequestPacket(username, password);
      });
  }
}
