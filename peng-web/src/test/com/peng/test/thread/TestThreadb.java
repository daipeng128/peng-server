package com.peng.test.thread;

/**
 * @Auther: daipeng
 * @Date: 2019/5/24 17:09
 * @Description:
 */
public class TestThreadb {

        public static void main(String[] args) {
            Thread myThread1 = new MyThreada();
            Thread myThread2 = new MyThreadb();
            myThread1.setPriority(Thread.MAX_PRIORITY);
            myThread2.setPriority(Thread.MIN_PRIORITY);
            for (int i = 0; i < 100; i++) {
                System.out.println("main thread i = " + i);
                if (i == 20) {
                    myThread1.start();
                    myThread2.start();
                    Thread.yield();
                }
            }
        }

    }

    class MyThreada extends Thread {

        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("myThread 1 --  i = " + i);
            }
        }
    }

    class MyThreadb extends Thread {

        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("myThread 2 --  i = " + i);
            }
        }
    }