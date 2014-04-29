package com.foolver.juo.packetHandling.packets.response;

public class UltimaMessengerPacket extends AbstractResponsePacket {

  private int id1;
  private int id2;

  public UltimaMessengerPacket(int id1, int id2) {
    super(false);
    this.id1 = id1;
    this.id2 = id2;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0xa7);
    buffer.putInt(id1);
    buffer.putInt(id2);
  }

  @Override
  public int getBufferSize() {
    return 9;
  }

  public int getId1() {
    return id1;
  }

  public int getId2() {
    return id2;
  }

}
