package com.mianshi.queue.shengchanxiaofei.blocking;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 */
public class Product implements Runnable{

    private volatile boolean isRunning = true;

    /** 内存缓冲区*/
    private BlockingQueue<ManTou> queue;

    // 总数 原子操作
    private static AtomicInteger count = new AtomicInteger();

    private static final int sleeptime = 1000;

    public Product(BlockingQueue<ManTou> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        ManTou manTou = null;
        Random r = new Random();
        System.out.println("start producter id:"+Thread.currentThread().getId());

        try {
            while (isRunning){
//                Thread.sleep(r.nextInt(sleeptime));

                manTou = new ManTou(count.incrementAndGet());

                System.out.println(manTou+" is put into queue");

                if(!queue.offer(manTou,2, TimeUnit.SECONDS)){
                    System.err.println("put into failed ");
                }
                if(count.get() == 20){
                    isRunning = false;
                }
            }
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }
    }

    public void stop(){
        isRunning =false;
    }

}
