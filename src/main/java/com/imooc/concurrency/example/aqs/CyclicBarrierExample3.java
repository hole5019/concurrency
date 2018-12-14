package com.imooc.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created by helei on 2018-12-13.
 */
@Slf4j
public class CyclicBarrierExample3 {

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0;i < 10;i ++){
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                }catch (Exception e){
                    log.error("exception",e);
                }
            });
        }
        log.info("finish");
        executorService.shutdown();
    }

    private static void race(int threadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.warn("BarrierException", e);
        }
        log.info("{} continue", threadNum);
    }
}
