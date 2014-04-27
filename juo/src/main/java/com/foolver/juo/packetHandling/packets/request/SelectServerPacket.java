package com.foolver.juo.packetHandling.packets.request;

public class SelectServerPacket extends AbstractRequestPacket {

  short serverId; 

  public SelectServerPacket(short serverId) {
    this.serverId = serverId;
  }

  public short getServerId() {
    return serverId;
  }

}
