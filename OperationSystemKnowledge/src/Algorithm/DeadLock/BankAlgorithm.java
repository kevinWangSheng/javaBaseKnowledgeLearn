package Algorithm.DeadLock;

import Algorithm.DeadLock.Reousrce.AllocationReousrce;
import Algorithm.DeadLock.Reousrce.NeedResource;

import java.util.Map;

/**
 * @author wang
 * @create 2022-2022-15-20:07
 */
public interface BankAlgorithm {
    public void init();

    public void requestResource();

    public boolean judgeSafe();

    public void allocation(String[] types, Map<String,Integer> work, AllocationReousrce allocationReousrce, int threadNum);

    public boolean allOfIsSafe();

    public void setNeedZero(String[] types,NeedResource needZero);
}
