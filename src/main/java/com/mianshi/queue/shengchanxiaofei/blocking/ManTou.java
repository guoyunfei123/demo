package com.mianshi.queue.shengchanxiaofei.blocking;

/**
 * 生产消费实物
 */
public class ManTou {

    private final int count;

    public ManTou(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ManTou{" +
                "count=" + count +
                '}';
    }
}
