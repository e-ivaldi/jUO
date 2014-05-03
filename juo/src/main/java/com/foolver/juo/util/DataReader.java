package com.foolver.juo.util;

import java.io.IOException;

public interface DataReader {

  void skipByte() throws IOException;
  
  void skipShort() throws IOException;
  
  void skipInt() throws IOException;
  
  void skip(int length) throws IOException;

  String readString(int length) throws IOException;

  int readInt() throws IOException;

  short readShort() throws IOException;

  byte readByte() throws IOException;  

}
