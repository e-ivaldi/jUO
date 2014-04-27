package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.LoginRequestPacket;

public class LoginRequestHandler extends AbstractRequestHandler<LoginRequestPacket> {

  @Override
  public LoginRequestPacket handle(InputStream is) throws PacketHandlingException {

    String username;
    String password;

    try {
      username = getFixedSizeStringFromInputStream(is, 30);
      password = getFixedSizeStringFromInputStream(is, 30);
      //next login key is not used
      getByteFromInputStream(is);
    } catch (IOException e) {
      throw new PacketHandlingException(String.format("unable to handler packet %s", this.getClass().getSimpleName()),
          e);
    }

    return new LoginRequestPacket(username, password);

  }

}
