package com.foolver.juo.engine.map;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapReader {

  private static final Logger log = LoggerFactory.getLogger(MapReader.class);

  private static final String MAP0_PATH = "/map0.mul";
  private static final int BLOCK_WIDTH = 768;
  private static final int BLOCK_HEIGHT = 512;
  private static final int CELLS_PER_BLOCK = 64;
  private static final int MAP_WIDTH = 6144;
  private static final int MAP_HEIGHT = 4096;
  private static final int CELL_SIZE = Short.BYTES + Byte.BYTES;
  private static final int BLOCK_SIZE = Integer.BYTES + CELLS_PER_BLOCK * CELL_SIZE;
  private static final int CELL_PER_BLOCK_IN_A_COLUMN = 8;
  private static final int BLOCK_PER_MAP_IN_A_COLUMN = 64;

  private byte[] bytes = new byte[BLOCK_WIDTH * BLOCK_HEIGHT * BLOCK_SIZE];

  public MapReader() throws IOException {
    try {
      log.info(String.format("Loading file %s", MAP0_PATH));
      InputStream is = new BufferedInputStream(this.getClass().getResourceAsStream(MAP0_PATH));
      is.read(bytes, 0, bytes.length);
      is.close();
    } catch (IOException e) {
      log.info(String.format("unable to load file %s", MAP0_PATH));
      throw e;
    }
  }

  public byte getMapCoordinateInfo(int x, int y) {
    byte z = 0x00;
    if (x >= 0 && x < MAP_WIDTH && y >= 0 && y < MAP_HEIGHT) {
      int blockLockup = calculateBlockLockup(x, y);
      int cellLockup = ((y * CELL_PER_BLOCK_IN_A_COLUMN) + x) * CELL_SIZE;
      z = bytes[blockLockup + cellLockup + 2];
    } else {
      log.error(String.format("unable to get the altitude for coordinates %s,%s, returning 0x00", x, y));
    }
    return z;
  }

  private int calculateBlockLockup(int x, int y) {
    int blockX = x / CELL_PER_BLOCK_IN_A_COLUMN;
    int blockY = y / CELL_PER_BLOCK_IN_A_COLUMN;
    return ((blockX * BLOCK_HEIGHT) + blockY) * BLOCK_SIZE;
  }

}
