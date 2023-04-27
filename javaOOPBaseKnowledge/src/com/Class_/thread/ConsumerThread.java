package com.Class_.thread;

/**
 * @author wang
 * @create 2022-2022-17-14:05
 */
public class ConsumerThread implements Runnable{
    private CommonThread commonThread;

    public ConsumerThread(CommonThread commonThread) {
        this.commonThread = commonThread;
    }

    @Override
    public void run() {
        System.out.println("consumer start consuming...");
        consumer();
    }

    public  void consumer(){

        for(int i = 0;i<10;i++){
            commonThread.get();
            try{ Thread.sleep(100);} catch(InterruptedException e){ e.printStackTrace();}
        }

    }
}
