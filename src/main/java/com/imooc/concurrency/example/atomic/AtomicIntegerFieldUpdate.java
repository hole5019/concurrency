package com.imooc.concurrency.example.atomic;

import com.imooc.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by helei on 2018-12-12.
 */
@Slf4j
@ThreadSafe
public class AtomicIntegerFieldUpdate {

    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdate> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdate.class,"count");

    @Getter
    public volatile int count = 100;

    private static AtomicIntegerFieldUpdate update = new AtomicIntegerFieldUpdate();

    public static void main(String[] args) {
        if(updater.compareAndSet(update,100,120)){
            log.info("update success1,{}",update.getCount());
        }

        if(updater.compareAndSet(update,120,130)){
            log.info("update success2,{}",update.getCount());
        }else {
            log.info("update success3,{}",update.getCount());
        }

    }
}
