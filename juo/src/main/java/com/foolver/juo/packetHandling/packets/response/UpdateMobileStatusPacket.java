package com.foolver.juo.packetHandling.packets.response;

public class UpdateMobileStatusPacket extends AbstractResponsePacket {

  private int playerSerial;
  private byte status;
  private int attackerSerial = -1;

  public UpdateMobileStatusPacket(int playerSerial, byte status) {
    this(playerSerial, status, -1);
  }

  public UpdateMobileStatusPacket(int playerSerial, byte status, int attackerSerial) {
    super(false);
    this.playerSerial = playerSerial;
    this.status = status;
    this.attackerSerial = attackerSerial;
    allocateAndSetupBuffer();
  }

  public boolean hasAttacherSerial() {
    return attackerSerial != -1;
  }

  public int getPlayerSerial() {
    return playerSerial;
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
    buffer.putInt(playerSerial);
    buffer.put(status);
    if (status == 0x01) {
      buffer.putInt(attackerSerial);
    }
  }

  @Override
  public int getBufferSize() {
    return (status == 0x01) ? 8 : 12;
  }

}
