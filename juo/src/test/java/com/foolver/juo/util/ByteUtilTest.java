package com.foolver.juo.util;

import org.junit.Assert;
import org.junit.Test;

public class ByteUtilTest {

  @Test
  public void getPaddedBytesOfString(){
    byte[] result = ByteUtil.getPaddedBytesOfString("emanuele", 10);
    Assert.assertNotNull(result);
    Assert.assertEquals(10, result.length);
    Assert.assertEquals('e', result[0]);
    Assert.assertEquals('m', result[1]);
    Assert.assertEquals('a', result[2]);
    Assert.assertEquals('n', result[3]);
    Assert.assertEquals('u', result[4]);
    Assert.assertEquals('e', result[5]);
    Assert.assertEquals('l', result[6]);
    Assert.assertEquals('e', result[7]);
    Assert.assertEquals('\0', result[8]);
    Assert.assertEquals(' ', result[9]);
  }
}
