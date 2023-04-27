package com.io.fileOperate;

/**
 * @author wang
 * @create 2022-2022-24-15:18
 */
public class ThreadException {
    public static void main(String[] args) {


        MyThreadGroup threadGroup = new MyThreadGroup("thread");
        Thread thread = new Thread(threadGroup,()->{
//            int i = 1/0;
            System.out.println("thread");
        });

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("wahaha");
            }
        });



        thread.start();

        Thread thread1 = new Thread(threadGroup,()->{
            System.out.println("wwuwuwu");
            int i = 1/0;
        });

        thread1.start();




    }

    private static class MyThreadGroup extends  ThreadGroup{

        public MyThreadGroup(String name) {
            super(name);
        }

        public MyThreadGroup(ThreadGroup parent, String name) {
            super(parent, name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("6666");
        }
    }
}
