package com.peng.test.thread;

/**
 * @Auther: daipeng
 * @Date: 2019/5/24 14:54
 * @Description:
 * 当调用线程的yield()方法时，线程从运行状态转换为就绪状态，但接下来CPU调度就绪状态中的哪个线程具有一定的随机性，因此，可能会出现A线程调用了yield()方法后，接下来CPU仍然调度了A线程的情况。
 * 由于实际的业务需要，常常会遇到需要在特定时机终止某一线程的运行，使其进入到死亡状态。目前最通用的做法是设置一boolean型的变量，当条件满足时，使线程执行体快速执行完毕
 */
public class StopThread {
    public static void main(String[] args) {
        MyRunnable3 myRunnable = new MyRunnable3();
        Thread thread = new Thread(myRunnable);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                thread.start();

//                try {
//                    thread.join();    // main线程需要等待thread线程执行完后才能继续执行
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            if(i == 40){
                //myRunnable.stopThread();
            }
        }
    }
}

class MyRunnable3 implements Runnable {
    private boolean stop;
    @Override
    public void run() {
        for (int i = 0; i < 100 && !stop; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
    public void stopThread() {
        this.stop = true;
    }

}