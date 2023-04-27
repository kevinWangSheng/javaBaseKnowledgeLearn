package Algorithm.DeadLock.impl;

import Algorithm.DeadLock.interfaces.BankAlg;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author wang
 * @create 2022-2022-16-14:01
 */
public class BankerAlgorImpl implements BankAlg {
    public static final int PCB_NUMBER = 5;
    public static final int RES_NUMBER = 3;

    private static int max[][] = new int[PCB_NUMBER][RES_NUMBER];
    private static int request[][] = new int[PCB_NUMBER][RES_NUMBER];
    private static int need[][] = new int[PCB_NUMBER][RES_NUMBER];
    private static int avaialbe[] = new int[RES_NUMBER];
    private static int allocationRes[][] = new int[PCB_NUMBER][RES_NUMBER];

    static int [] safeQueue = new int[PCB_NUMBER];

    public  boolean initData(){
        Scanner sc = new Scanner(System.in);
        for(int i = 0;i<PCB_NUMBER;i++){
            System.out.println("input the max resource");
            for(int j = 0;j<RES_NUMBER;j++){
                max[i][j] = sc.nextInt();
            }
        }
        for(int i = 0;i<PCB_NUMBER;i++){
            System.out.println("input the resource");
            for(int j = 0;j<RES_NUMBER;j++){
                allocationRes[i][j] = sc.nextInt();
            }
        }

        for(int i =0;i<PCB_NUMBER;i++){
            for(int j = 0;j<RES_NUMBER;j++) {
                need[i][j] = max[i][j] - allocationRes[i][j];
            }
        }
        System.out.println("input the avaiable resource");
        for(int i = 0;i<RES_NUMBER;i++){
           avaialbe[i] = sc.nextInt();
        }

        boolean isSafe = isSafe();
        System.out.println("judge the initialization is safe?:"+isSafe);

        return isSafe;


    }

    public boolean isSafe(){
        int work[] = new int[RES_NUMBER];
        boolean finish[] = new boolean[PCB_NUMBER];
        for(int i = 0;i<RES_NUMBER;i++)
        {
            work[i] = avaialbe[i];
        }
        int finshNum = 0;
        for(int i = 0;i<PCB_NUMBER;i++){

            if(finshNum == PCB_NUMBER)
                break;
            for(int j = 0;j<PCB_NUMBER;j++){
                if(finish[j]){
                    continue;
                }
                if(isAvaiavle(work,need[j]))
                {
                    finish[j] = true;
                    safeQueue[finshNum++] = j;
                    for(int k = 0;k<RES_NUMBER;k++)
                        work[k] += allocationRes[j][k];
                    //            output the allocation
                    for(int k = 0;k<PCB_NUMBER;k++){
                        System.out.println("the banker " +k+ " allocation is :");
                        for(int l = 0;l<RES_NUMBER;l++){

                            if(finish[k]){
                                System.out.print(0+" ");
                            }
                            else
                                System.out.print(allocationRes[k][l]+" ");
                        }
                        System.out.println();
                    }
                    //            output the avaliable resource of work queue
                    System.out.println("the work of banker ");
                    for(int k = 0;k<RES_NUMBER;k++){
                        System.out.print(work[k]+" ");
                    }
                    System.out.println();

                }
            }
            if(finshNum==0) {
                System.out.println("can not allocate");
                return false;
            }


        }

        for(int i = 0;i<PCB_NUMBER;i++) {
            if (!finish[i])
                return false;
        }
        System.out.println("the safe queue is :"+Arrays.toString(safeQueue));
        return true;

    }

    public void judgeSafe(int pcbIndex){

        if(isAvaiavle(avaialbe,request[pcbIndex])&& isAvaiavle(need[pcbIndex],request[pcbIndex])){
            allocate(pcbIndex);
            if(isSafe()){
                System.out.println("is safe");

            }
            else{
                System.out.println("is no safe,reallocate!");
                reAllocate(pcbIndex);
            }
        }
        else{
            System.out.println("request error!");
        }
    }

    public boolean isAvaiavle(int avi[],int need[]){
        for(int i = 0;i<avi.length;i++){
            if(avi[i]<need[i])
                return false;
        }
        return true;
    }

    public void allocate(int pcbIndex){
        for(int i = 0;i<RES_NUMBER;i++){
           allocationRes[pcbIndex][i] +=request[pcbIndex][i];
           need[pcbIndex][i] -=request[pcbIndex][i];
           avaialbe[i] -=request[pcbIndex][i];
        }
    }

    public void reAllocate(int pcbIndex){
        for(int i = 0;i<RES_NUMBER;i++){
            allocationRes[pcbIndex][i] -= request[pcbIndex][i];
            need[pcbIndex][i] += request[pcbIndex][i];
            avaialbe[i] +=request[pcbIndex][i];
        }
    }


    public static void main(String[] args) {
        BankerAlgorImpl bankerAlgor = new BankerAlgorImpl();

        boolean isSafe = bankerAlgor.initData();
        if(!isSafe){
            System.out.println("the initialization is no safe ! ending.....");
            return;
        }

        System.out.println("input the request thread index");
        Scanner scanner = new Scanner(System.in);
        int indexThread=scanner.nextInt();

        System.out.println("input the request resource");
        for(int i = 0;i<RES_NUMBER;i++){
            request[indexThread][i] = scanner.nextInt();
        }
//        judege the request index thread request resource is safe?
        bankerAlgor.judgeSafe(indexThread);
    }
}
