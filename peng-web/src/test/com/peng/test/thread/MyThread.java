package com.peng.test.thread;

/**
 * @Auther: daipeng
 * @Date: 2019/5/24 11:52
 * @Description:
 *
 * 1 继承Thread类，重写该类的run()方法。
 */
public class MyThread extends Thread {

    private int i = 0;
    @Override
    public void run() {
        for (i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
