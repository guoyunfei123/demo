package com.mianshi.queue.zuse;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用LinkedList
 * 阻塞队列实现（使用ReentrantLock、Condition）
 * 阻塞队列是一种特殊的先进先出队列,它有以下几个特点
 * 1.入队和出队线程安全
 * 2.当队列满时,入队线程会被阻塞;当队列为空时,出队线程会被阻塞。
 */
public class MyBlockingQueue<E> {

    /** 阻塞队列长度 */
    int size;

    final ReentrantLock takelock = new ReentrantLock();

    final ReentrantLock putlock = new ReentrantLock();

    private volatile LinkedList<E> list = new LinkedList<>();

    /** 队列空时的等待条件 */
    private final Condition take_notEmpty = takelock.newCondition();

    /** 队列满时的等待条件 */
    private final Condition put_notFull = putlock.newCondition();

    public MyBlockingQueue(int size) {
        this.size = size;
    }

    public void put(E e){
        putlock.lock();
        try {
            while (list.size() == size){
                put_notFull.await();
            }
            list.add(e); //入队:加入链表末尾
            System.out.println("入队操作："+list.getLast().toString()+"--当前队列存有【"+list.size()+"】数据");
            System.out.println("通知消费者");
            take_notEmpty.signal(); //通知在notEmpty条件上等待的线程

        }catch (Exception ex){

        } finally {
            putlock.unlock();
        }
    }

    public void take(){
        takelock.lock();
        try {
            while (list.size() == 0){
                System.out.println("没有数据");
                take_notEmpty.await();
            }
            E e = list.getFirst();
            list.removeFirst();//出队:移除链表首元素
            System.out.println("出队操作:"+ e.toString()+"：--当前队列存有【"+list.size()+"】条数据");
//            put_notFull.signal(); //通知在notEmpty条件上等待的线程
        }catch (Exception ex){
            System.out.println("异常");
        } finally {
            System.out.println("执行完成");
            takelock.unlock();
        }
    }

    /**
     * 10个进栈线程，10个出栈线程
     * @param args
     */
    public static void main(String[] args) {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(3);
        for (int i = 0;i<10 ; i++){
            int data = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myBlockingQueue.put(data);
                }
            }).start();
        }

        for (int i = 0;i<1 ; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myBlockingQueue.take();
                }
            }).start();
        }

        try {
//            Thread.sleep(5000);

        }catch (Exception e){

        }
    }
}
