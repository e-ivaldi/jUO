package com.foolver.juo.packetHandling.packets.response;

public class UpdateMobileStatusPacket extends AbstractResponsePacket {

  private int serialId;
  private byte status;
  private int attackerSerial = 0;

  public UpdateMobileStatusPacket(int playerSerial, byte status) {
    this(playerSerial, status, -1);
  }

  public UpdateMobileStatusPacket(int playerSerial, byte status, int attackerSerial) {
    super(false);
    this.serialId = playerSerial;
    this.status = status;
    this.attackerSerial = attackerSerial;
    allocateAndSetupBuffer();
  }

  public boolean hasAttacherSerial() {
    return attackerSerial != -1;
  }

  public int getPlayerSerial() {
    return serialId;
  }

  public byte getStatus() {
    return status;
  }

  public int getAttackerSerial() {
    return attackerSerial;
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0xDE);
    buffer.putShort((short) getBufferSize());
    buffer.putInt(serialId);
    buffer.put(status);
    buffer.putInt(attackerSerial);
  }

  @Override
  public int getBufferSize() {
    return 12;
  }

}
