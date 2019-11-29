package com.mianshi.queue.shengchanxiaofei.blocking;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 */
public class Consumer implements Runnable {

    /** 内存缓冲区*/
    private BlockingQueue<ManTou> queue;

    private static final int sleeptime = 1000;

    public Consumer(BlockingQueue<ManTou> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start consumer id:"+Thread.currentThread().getId());
        Random r = new Random();

        try {
            while (true){
                ManTou manTou = queue.take();
                if(manTou != null){
                    System.out.println("任务提取成功："+manTou.toString());
                }else{
                    System.out.println("任务提取失败！");
                }
            }
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }
    }
}
