package com.Class_.thread;

/**
 * @author wang
 * @create 2022-2022-17-13:59
 */
public class ProducterThread implements Runnable{
    private CommonThread commonThread;

    public ProducterThread(CommonThread commonThread) {
        this.commonThread = commonThread;
    }

    @Override
    public void run() {
        System.out.println("start to producing....");
        produce();

        System.out.println("producing ending....");
    }

    public  void produce(){
        for(int i = 0;i<10;i++){
            commonThread.set(i);
            try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
        }
    }
}
