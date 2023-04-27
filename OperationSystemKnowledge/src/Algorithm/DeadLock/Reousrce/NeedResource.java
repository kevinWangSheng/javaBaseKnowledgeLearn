package Algorithm.DeadLock.Reousrce;

import Algorithm.DeadLock.BankAlgorithm;
import Algorithm.DeadLock.BankerAlgoirthmImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2022-2022-15-20:28
 */
public class NeedResource {
    private Map<String,Integer> need = new HashMap();

    public NeedResource(){
    }

    public void calculateNeed(String[] types,Integer[] numbers){
        for(int i = 0;i<types.length;i++){
            need.put(types[i],numbers[i]);
        }
    }

    public Map<String, Integer> getNeed() {
        return need;
    }

    public void setNeed(Map<String, Integer> need) {
        this.need = need;
    }
}
