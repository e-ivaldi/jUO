package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.shared.RequestTipNoticePacket;
import com.foolver.juo.util.DataReader;

public class RequestTipNoticeHandler extends AbstractRequestHandler<RequestTipNoticePacket> {

  @Override
  public RequestTipNoticePacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      short lastTip = dataReader.readShort();
      byte flag = dataReader.readByte();
      return new RequestTipNoticePacket(lastTip, flag);
    });
  }
}
