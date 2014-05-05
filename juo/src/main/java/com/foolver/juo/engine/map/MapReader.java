package com.foolver.juo.engine.map;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapReader {

  private static final Logger log = LoggerFactory.getLogger(MapReader.class);

  private static final String MAP0_PATH = "/map0.mul";
  private static final int MAP_WIDTH_IN_CELL = 6144;
  private static final int MAP_HEIGHT_IN_CELL = 4096;
  private static final int NUM_OF_CELLS_PER_BLOCK_IN_A_ROW = 8;
  private static final int NUM_OF_CELLS_PER_BLOCK_IN_A_COLUMN = 8;
  private static final int NUM_OF_HORIZONTAL_BLOCKS = MAP_WIDTH_IN_CELL / NUM_OF_CELLS_PER_BLOCK_IN_A_ROW;
  private static final int NUM_OF_VERTICAL_BLOCKS = MAP_HEIGHT_IN_CELL / NUM_OF_CELLS_PER_BLOCK_IN_A_COLUMN;
  private static final int CELLS_PER_BLOCK = NUM_OF_CELLS_PER_BLOCK_IN_A_ROW * NUM_OF_CELLS_PER_BLOCK_IN_A_COLUMN;
  private static final int BLOCK_HEADER_SIZE_IN_BYTES = Integer.BYTES;
  private static final int CELL_TILE_ID_SIZE_IN_BYTES = Short.BYTES;
  private static final int CELL_LOCATION_SIZE_IN_BYTES = Byte.BYTES;
  private static final int CELL_SIZE_IN_BYTES = CELL_TILE_ID_SIZE_IN_BYTES + CELL_LOCATION_SIZE_IN_BYTES;
  private static final int BLOCK_SIZE_IN_BYTES = BLOCK_HEADER_SIZE_IN_BYTES + CELLS_PER_BLOCK * CELL_SIZE_IN_BYTES;

  byte[][] zMatrix = new byte[MAP_WIDTH_IN_CELL][MAP_HEIGHT_IN_CELL];

  public MapReader() throws IOException {
    try {
      log.info(String.format("Loading file %s", MAP0_PATH));
      InputStream is = new BufferedInputStream(this.getClass().getResourceAsStream(MAP0_PATH));
      byte[] bytes = new byte[NUM_OF_HORIZONTAL_BLOCKS * NUM_OF_VERTICAL_BLOCKS * BLOCK_SIZE_IN_BYTES];
      is.read(bytes, 0, bytes.length);
      fillTheZMatrix(bytes);
      bytes = null;
      is.close();
    } catch (IOException e) {
      log.info(String.format("unable to load file %s", MAP0_PATH));
      throw e;
    }
  }

  public byte getCellAltitude(short posX, short posY) {
    if (coordsAreWithinBoundaries(posX, posY)) {
      return zMatrix[posX][posY];
    } else {
      log.error(String.format("requested z info for out of boundaries coordinates: %s,%s",posX, posY));
      return 0;
    }
  }

  private boolean coordsAreWithinBoundaries(short posX, short posY) {
    return posX >= 0 && posX < MAP_WIDTH_IN_CELL && posY >= 0 && posY < MAP_HEIGHT_IN_CELL;
  }

  private void fillTheZMatrix(byte[] bytes) {
    log.info("Filling the z matrix");
    for (int x = 0; x < MAP_WIDTH_IN_CELL; x++) {
      for (int y = 0; y < MAP_HEIGHT_IN_CELL; y++) {
        zMatrix[x][y] = calculateAltitude(x, y, bytes);
      }
    }
  }

  private byte calculateAltitude(int x, int y, byte[] bytes) {
    int blockLockup = calculateBlockLockup(x, y);
    int cellLockup = calculateCellLockup(x, y);
    return bytes[blockLockup + cellLockup + CELL_TILE_ID_SIZE_IN_BYTES];
  }

  private int calculateCellLockup(int x, int y) {
    return BLOCK_HEADER_SIZE_IN_BYTES
        + (((y % NUM_OF_CELLS_PER_BLOCK_IN_A_ROW) * NUM_OF_CELLS_PER_BLOCK_IN_A_ROW) + (x % NUM_OF_CELLS_PER_BLOCK_IN_A_ROW))
        * CELL_SIZE_IN_BYTES;
  }

  private int calculateBlockLockup(int x, int y) {
    int blockX = x / NUM_OF_CELLS_PER_BLOCK_IN_A_ROW;
    int blockY = y / NUM_OF_CELLS_PER_BLOCK_IN_A_COLUMN;
    return ((blockX * NUM_OF_VERTICAL_BLOCKS) + blockY) * BLOCK_SIZE_IN_BYTES;
  }

}
