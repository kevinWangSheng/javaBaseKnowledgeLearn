package com.threadBaseKnowledge;

import java.util.concurrent.CompletableFuture;
import org.junit.Assert;
/**
 * @author wang
 * @create 2022-2022-25-22:01
 */

public class ComletableFutureUtils {
    public static void completedFutureExample(){
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message");

        Assert.assertTrue(cf.isDone());

        Assert.assertEquals("message",cf.getNow(null));
    }


}
