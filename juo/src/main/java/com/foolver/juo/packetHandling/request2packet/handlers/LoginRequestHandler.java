package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.LoginRequestPacket;
import com.foolver.juo.util.DataReader;

public class LoginRequestHandler extends AbstractRequestHandler<LoginRequestPacket> {

  @Override
  public LoginRequestPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      String username = dataReader.readString(30);
      String password = dataReader.readString(30);
      dataReader.skip(1); // next login key
        return new LoginRequestPacket(username, password);
      });
  }
}
