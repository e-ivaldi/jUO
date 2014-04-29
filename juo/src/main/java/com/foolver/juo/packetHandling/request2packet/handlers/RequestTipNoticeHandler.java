package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.shared.RequestTipNoticePacket;

public class RequestTipNoticeHandler extends AbstractRequestHandler<RequestTipNoticePacket> {

  @Override
  public RequestTipNoticePacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      short lastTip = getShortFromInputStream(is);
      byte flag = getByteFromInputStream(is);
      return new RequestTipNoticePacket(lastTip, flag);
    });
  }
}
