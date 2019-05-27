package com.peng.test.thread;

/**
 * @Auther: daipeng
 * @Date: 2019/5/27 09:21
 * @Description:
 */
public class SaleTicket {

    public static void main(String[] args) {
        // 创建一个初始化线程,若干个买票线程
        // 要等初始化线程初始化好了之后,才能买票

        // 创建一个Ticket类的对象,分别传给不同的线程
        Ticket ticket = new Ticket();

        // 创建四个买票线程,开始卖票
        Thread t2 = new SaleTicketThread("卖票窗口1", ticket);
        Thread t3 = new SaleTicketThread("卖票窗口2", ticket);
        Thread t4 = new SaleTicketThread("卖票窗口3", ticket);
        Thread t5 = new SaleTicketThread("卖票窗口4", ticket);

        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
/**
 * 创建车票类、初始化车票
 * @author haokui
 *
 */
class Ticket {
    // 初始化200张票
    private int index = 200;

    public Ticket() {

    }


    public synchronized String saleTicket() throws NoTicketException {
        // 判断是否有票,有票的情况下再卖票,没有票呢,抛出异常,
        // 考虑是否需要同步
        if (index >= 0) {

            String s = "卖出去 1 张票 剩余"+ index +"张票";

            // 故意制造了一个问题,出现多个线程共卖一张车票
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index--;
            return s;
        } else {
            throw new NoTicketException("没有车票了");
        }

    }
}
/**
 * 创建卖票线程类
 * @author haokui
 *
 */
class SaleTicketThread extends Thread {

    private Ticket ticket;

    public SaleTicketThread(String name, Ticket ticket) {
        super(name);
        this.ticket = ticket;
    }

    // 在run方法中卖车票
    public void run() {
        for (int i = 0; i < 60; i++) {
            try {

                //买票
                String s = ticket.saleTicket();

                System.out.println(this.getName() + "卖票成功========>" + s);
            } catch (NoTicketException e) {
                System.out.println(this.getName() + " 卖票时发生异常!");
                e.printStackTrace();

                // 如果发生异常,说明没有车票了,就中断循环,不要在卖票了
                break;
            }
        }
    }
}

/**
 * 自定义异常
 * @author haokui
 *
 */
class NoTicketException extends Exception {
    public NoTicketException() {

    }

    public NoTicketException(String msg) {
        super(msg);
    }
}