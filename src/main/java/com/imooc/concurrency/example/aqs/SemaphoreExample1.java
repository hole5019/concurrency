package com.imooc.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by helei on 2018-12-13.
 */
@Slf4j
public class SemaphoreExample1 {

    private final static int threadCount  = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0;i < threadCount;i ++){
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test(threadNum);
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        log.info("finish");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
