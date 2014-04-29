package com.foolver.juo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.Test;

public class PlayerInfoTest {

  @Test
  public void createPlayerInfoInTheSameThread() {
    PlayerInfo playerInfo1 = PlayerInfo.getInstance();
    PlayerInfo playerInfo2 = PlayerInfo.getInstance();

    Assert.assertNotNull(playerInfo1);
    Assert.assertNotNull(playerInfo2);
    Assert.assertEquals(playerInfo1.getSerialId(), playerInfo2.getSerialId());
  }

  @Test
  public void createPlayerInfoInDifferentThreads() throws InterruptedException, ExecutionException {
    ExecutorService executor = Executors.newFixedThreadPool(2);

    Callable<PlayerInfo> callable = () -> PlayerInfo.getInstance();

    PlayerInfo playerInfo1 = executor.submit(callable).get();
    PlayerInfo playerInfo2 = executor.submit(callable).get();

    Assert.assertNotNull(playerInfo1);
    Assert.assertNotNull(playerInfo2);
    Assert.assertNotEquals(playerInfo1.getSerialId(), playerInfo2.getSerialId());

    executor.shutdown();
  }

}
