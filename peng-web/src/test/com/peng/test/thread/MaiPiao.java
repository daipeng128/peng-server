package com.peng.test.thread;

/**
 * @Auther: daipeng
 * @Date: 2019/5/28 09:18
 * @Description:
 */
public class MaiPiao {

    //余票200张
    private Integer piao = 200;


    public synchronized int salePiao(){
        //假定一次只卖一张票
        if(piao > 0){

            piao -- ;
            System.out.println("窗口:"+Thread.currentThread().getName()+"售出 1张票 剩余"+piao);

            // 故意制造了一个问题,出现多个线程共卖一张车票
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 1;
        }
        System.out.println("窗口:"+Thread.currentThread().getName()+" 购票异常 余票不足");
        return 0;
    }

}

/**
 * 卖票线程
 */
class PiaoThread extends Thread{

    private MaiPiao maiPiao;

    public PiaoThread(MaiPiao maiPiao){
        this.maiPiao = maiPiao;
    }

    public void run(){

        for (int i=0;i<60;i++) {

           int x =  maiPiao.salePiao();

            if(x == 0){
                break;
            }
        }

    }
}


class PiaoDemo{

    public static void main(String[] args) {

        MaiPiao piao = new MaiPiao();


        Thread thread = new PiaoThread(piao);
        Thread thread2 = new PiaoThread(piao);
        Thread thread3 = new PiaoThread(piao);
        Thread thread4 = new PiaoThread(piao);

        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}