package com.foolver.juo.packetHandling.packets.response;

import com.foolver.juo.util.ByteUtil;

public class ServersListPacket extends AbstractResponsePacket {

  private static final int NUMBER_OF_SERVERS = 1;
  private static final int SERVER_INFO_SIZE_IN_BYTES = 40;

  @Override
  public void fillBuffer() {
    buffer.put((byte) 0xa8); // packetId
    buffer.putShort((short) getBufferSize()); // length
    buffer.put((byte) 0x5d); // systemInfo
    buffer.putShort((short) 1); // # of server
    // foreachServer
    buffer.putShort((byte) 0); // server index
    buffer.put(ByteUtil.getPaddedBytesOfString("test serverz", 32)); // serverName
    buffer.put((byte) 20); // percent full
    buffer.put((byte) 0); // timezone
    buffer.putInt(127 << 24 | 0 << 16 | 0 << 8 | 1); // server ip
  }

  @Override
  public int getBufferSize() {
    return 6 + NUMBER_OF_SERVERS * SERVER_INFO_SIZE_IN_BYTES;
  }

}
