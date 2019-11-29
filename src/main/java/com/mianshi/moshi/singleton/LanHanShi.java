package com.mianshi.moshi.singleton;

/**
 * @see  https://blog.csdn.net/baolingye/article/details/101106783
 * 懒汉式单例
 */
public class LanHanShi {

    private static LanHanShi lanHanShi = new LanHanShi();

    private LanHanShi() {

    }

    public static LanHanShi getInstance(){
        return lanHanShi;
    }
}
