package com.CompletableExec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author wang
 * @create 2022-2022-28-16:51
 */
public class CompletationExecotorsServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorCompletionService service = new ExecutorCompletionService(executor);

        List<Callable<String>> callables = new ArrayList<>();

        for(int i = 0;i<10;i++){
            callables.add(()->{
                int random = new Random().nextInt(10);
//                try{ Thread.sleep(1000);} catch(InterruptedException e){ e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+" | id is "+Thread.currentThread().getId()+"|");
                return "wanglaowu"+random;
            });
        }

        doExecutorCompletionService(service,callables);

        for(int i = 0;i<callables.size();i++){
            System.out.println(Thread.currentThread().getName()+" | id is "+Thread.currentThread().getId()+"|"+service.take().get());
        }
        executor.shutdown();
    }


    public static void doExecutorCompletionService(ExecutorCompletionService service,List<Callable<String>> list)
    {
        for(Callable task:list)
        {
            service.submit(task);
        }
    }
}
