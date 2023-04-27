package com.threadBaseKnowledge;

import java.util.concurrent.CountDownLatch;

/**
 * @author wang
 * @create 2022-2022-24-15:50
 */
public class TwoAccountThread {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Bank bank = new Bank();
//        try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 400000; i++) {
                bank.transfer(0, 1, 1000);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            countDownLatch.countDown();
        }, "t1");
        t1.start();


        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 400010; i++) {
                bank.transfer(1, 0, 100);
                try{ Thread.sleep(1);} catch(InterruptedException e){ e.printStackTrace();}
            }
            countDownLatch.countDown();
        }, "t2");

        t2.start();


        countDownLatch.await();
        System.out.println(bank.sum());

    }
}
class Bank{

    Account1 account[] = new Account1[2];

    public void transfer(int from,int to,int money){
        if(account[from].getMoney()>money){
//            System.out.println(Thread.currentThread().getName()+"account"+from+" put "+money+"money to"+to+" account");
            account[from].putMoney(money);
            account[to].saveMoney(money);
//            System.out.println("end.......");
        }

    }
    public Bank(){
        for(int i = 0;i<account.length;i++)
        {
            account[i] = new Account1();
        }
    }

    public int sum(){
        int sum = 0;
        for(Account1 a:account){
            sum+=a.getMoney();
        }
        return sum;
    }


}

class Account1{
    private int money = 10000;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void putMoney(int money) {
//        System.out.println(Thread.currentThread().getName()+" put money");
        this.money-=money;
    }

    public void saveMoney(int money) {
//        System.out.println(Thread.currentThread().getName()+" save money");
        this.money+=money;
    }
}
