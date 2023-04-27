package Algorithm.DeadLock.Reousrce;

import Algorithm.DeadLock.BankerAlgoirthmImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2022-2022-15-20:37
 */
public class AllocationReousrce {
    private Map<String,Integer> allocation = new HashMap();

    public AllocationReousrce(){

    }

    public void initializeAllocation(String[] types,Integer[] numbers){
        for(int i = 0;i<types.length;i++)
            allocation.put(types[i],numbers[i]);
    }

    public Map<String, Integer> getAllocation() {
        return allocation;
    }

    public void setAllocation(Map<String, Integer> allocation) {
        this.allocation = allocation;
    }
}
