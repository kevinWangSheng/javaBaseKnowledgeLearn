package Algorithm.DeadLock;

import Algorithm.DeadLock.Reousrce.AllocationReousrce;
import Algorithm.DeadLock.Reousrce.NeedResource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2022-2022-15-20:07
 */
public class BankerAlgoirthmImpl implements BankAlgorithm {
    public static final int RESOURCE_TYPE = 4;
    String [] resource = {"A","B","C","D"};

    Map<String,Integer> work = new HashMap<>();

    Map<String,Integer> request = new HashMap<>();

    NeedResource[] need = new NeedResource[RESOURCE_TYPE];

    AllocationReousrce[] allocation = new AllocationReousrce[RESOURCE_TYPE];

    Map<String,Integer> max = new HashMap<>();

    boolean [] finishs = new boolean[RESOURCE_TYPE];


    @Override
    public void init() {
//        initilize the work
        work.put(resource[0],1);
        work.put(resource[1],6);
        work.put(resource[2],5);
        work.put(resource[3],4);

//        initilize the Need
        for(int i = 0;i<resource.length;i++){
            need[i] = new NeedResource();
            allocation[i] = new AllocationReousrce();
        }
//        initialize the need resource
        need[0].calculateNeed(resource,new Integer[]{2,3,5,6});
        need[1].calculateNeed(resource,new Integer[]{0,6,5,2});
        need[2].calculateNeed(resource,new Integer[]{0,6,5,6});
        need[3].calculateNeed(resource,new Integer[]{1,7,5,0});

//        initialize the allocation
        allocation[0].initializeAllocation(resource,new Integer[]{0,3,3,2});
        allocation[1].initializeAllocation(resource,new Integer[]{0,0,1,4});
        allocation[2].initializeAllocation(resource,new Integer[]{1,0,0,0});
        allocation[3].initializeAllocation(resource,new Integer[]{1,3,5,4});
    }

    @Override
    public void setNeedZero(String[] types,NeedResource need) {
        Map<String, Integer> needMap = need.getNeed();
        for(int i =0;i<needMap.size();i++){
            needMap.put(types[i],0);
        }
    }

    @Override
    public void requestResource() {

    }

    @Override
    public boolean judgeSafe() {
        boolean flag = false;
        for(int i = 0;i<need.length;i++){
            for(int j = 0;j<need[i].getNeed().size();j++){
                if(work.get(resource[j])<need[i].getNeed().get(resource[j]))
                {
                    break;
                }
//                is the last one
                if(j == allocation[i].getAllocation().size()-1) {
//                    is allocatable
                    allocation(resource,work,allocation[i],i);
                    setNeedZero(resource,need[i]);

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void allocation(String[] types,Map<String,Integer>work,AllocationReousrce need,int threadNum) {
        for(int i = 0;i<types.length;i++){
            work.put(types[i],(work.get(types[i])+need.getAllocation().get(types[i])));
            need.getAllocation().put(types[i],0);
        }
        finishs[threadNum] = true;
    }

    @Override
    public boolean allOfIsSafe() {
        for(int i = 0;i<finishs.length;i++){
            if(!judgeSafe())
                return false;
        }
        return true;
    }
}
