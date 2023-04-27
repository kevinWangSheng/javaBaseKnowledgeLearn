package com.threadBaseKnowledge;

/**
 * @author wang
 * @create 2022-2022-23-22:01
 */
public class AccountThreadTest {
    public static void main(String[] args) {
        Account account = new Account();

        new Thread(()->{
            System.out.println("wanglaowu");
            while(true){
                if(account.getMoney()>0) {
                    System.out.println("wanglaowu");
                    account.withdrawl();
                }
                else{
                    System.out.println(Thread.currentThread().getName()+"no more deposit");
                    break;
                }
            }
        },"t1").start();

        new Thread(()->{
            while(true){
                if(account.getMoney()>0)
                    account.withdrawl();
                else
                {
                    System.out.println(Thread.currentThread().getName()+" no more deposit");
                    break;
                }
            }
        },"t2").start();


//        try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}

        new Thread(()->{
            while(true){
                if(account.getMoney()>100000)
                    break;
                else{
                    account.save();
                }
            }
        },"t3").start();
    }
}
