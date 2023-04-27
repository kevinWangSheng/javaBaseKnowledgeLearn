package com.threadBaseKnowledge;

/**
 * @author wang
 * @create 2022-2022-23-21:59
 */
public class Account {
    private int money  = 10000;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public synchronized void withdrawl(){
        System.out.println(Thread.currentThread().getName()+"get money");
        money-=1000;
    }

    public synchronized void  save(){
        System.out.println(Thread.currentThread().getName()+"save money");
        money+=1000;
    }
}
