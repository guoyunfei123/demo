package com.mianshi.queue.shengchanxiaofei.blocking;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        try {
            BlockingQueue<ManTou> queue = new LinkedBlockingQueue<>(10);
            Product product1 = new Product(queue);
//        Product product2 = new Product(queue);
            Consumer consumer1 = new Consumer(queue);

            ThreadPoolExecutor pool = new ThreadPoolExecutor(3,3,1000, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
            pool.execute(product1);
            pool.execute(consumer1);
//            Thread.sleep(1000);
//            product1.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
