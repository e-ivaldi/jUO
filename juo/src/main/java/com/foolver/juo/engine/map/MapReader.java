package com.foolver.juo.engine.map;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapReader {

  private static final Logger log = LoggerFactory.getLogger(MapReader.class);

  private static final String MAP0_PATH = "/map0.mul";
  private static final int MAP_WIDTH = 6144;
  private static final int MAP_HEIGHT = 4096;
  private static final int BLOCKS_PER_MAP_IN_A_COLUMN = 8;
  private static final int BLOCKS_PER_MAP_IN_A_ROW = 8;
  private static final int BLOCK_WIDTH = MAP_WIDTH / BLOCKS_PER_MAP_IN_A_COLUMN;
  private static final int BLOCK_HEIGHT = MAP_HEIGHT / BLOCKS_PER_MAP_IN_A_COLUMN;
  private static final int CELLS_PER_BLOCK_IN_A_COLUMN = 8;
  private static final int CELLS_PER_BLOCK_IN_A_ROW = 8;
  private static final int CELLS_PER_BLOCK = CELLS_PER_BLOCK_IN_A_COLUMN * CELLS_PER_BLOCK_IN_A_ROW;
  private static final int BLOCK_HEADER_SIZE = Integer.BYTES;
  private static final int CELL_TILE_ID_SIZE = Short.BYTES;
  private static final int CELL_LOCATION_SIZE = Byte.BYTES;
  private static final int CELL_SIZE = CELL_TILE_ID_SIZE + CELL_LOCATION_SIZE;
  private static final int BLOCK_SIZE = BLOCK_HEADER_SIZE + CELLS_PER_BLOCK * CELL_SIZE;

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
    byte z = 0;
    if (x >= 0 && x < MAP_WIDTH && y >= 0 && y < MAP_HEIGHT) {
      int blockLockup = calculateBlockLockup(x, y);
      int cellLockup = calculateCellLokup(x, y);
      z = bytes[blockLockup + cellLockup + CELL_TILE_ID_SIZE];
    } else {
      log.error(String.format("unable to get the altitude for coordinates %s,%s, returning 0x00", x, y));
    }
    return z;
  }

  private int calculateCellLokup(int x, int y) {
    return BLOCK_HEADER_SIZE
        + (((y % CELLS_PER_BLOCK_IN_A_COLUMN) * CELLS_PER_BLOCK_IN_A_COLUMN) + (x % CELLS_PER_BLOCK_IN_A_COLUMN))
        * CELL_SIZE;
  }

  private int calculateBlockLockup(int x, int y) {
    int blockX = x / BLOCKS_PER_MAP_IN_A_COLUMN;
    int blockY = y / BLOCKS_PER_MAP_IN_A_ROW;
    return ((blockX * BLOCK_HEIGHT) + blockY) * BLOCK_SIZE;
  }

}
