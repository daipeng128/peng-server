package com.peng.test.thread;

/**
 * @Auther: daipeng
 * @Date: 2019/5/24 11:58
 * @Description:
 */
public class testThreada {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
            if (i == 3) {
                Runnable myRunnable = new MyRunnable2();
                Thread thread = new MyThread2(myRunnable);
                thread.start();

            }
        }
    }
}

/**
 *
 */
class MyRunnable2 implements Runnable {
    private int i = 0;

    @Override
    public void run() {
        System.out.println("in MyRunnable run");
        for (i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

class MyThread2 extends Thread {

    private int i = 0;

    public MyThread2(Runnable runnable){
        super(runnable);
    }

    @Override
    public void run() {
        System.out.println("in MyThread run");
        for (i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}