package algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author wang
 * @create 2023-2023-12-17:56
 */
public class GreedAlgo {
    public static void main(String[] args) {
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("你吗");
        hashSet1.add("你爸");
        hashSet1.add("你牢牢");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("你吗");
        hashSet2.add("你爸");
        hashSet2.add("你爷爷");
        hashSet2.add("你姥姥");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("你妈");
        hashSet3.add("你爸");


        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("你妈");
        hashSet4.add("你爸");
        hashSet4.add("你爷爷");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("你吗");
        hashSet5.add("你爸");
        hashSet5.add("你大爷");
        hashSet5.add("你儿子");

        HashSet<String> hashSet6 = new HashSet<>();
        hashSet6.add("我草你吗");
        hashSet6.add("滚啊");
        hashSet6.add("你大爷的");
        hashSet6.add("我是你爸爸");

        HashMap<String,HashSet<String>> pedigree = new HashMap<>();
        HashSet<String> allRela = new HashSet<>();

        pedigree.put("K1",hashSet1);
        pedigree.put("K2",hashSet2);
        pedigree.put("K3",hashSet3);
        pedigree.put("K4",hashSet4);
        pedigree.put("K5",hashSet5);
        pedigree.put("K6",hashSet6);

        allRela.addAll(hashSet1);
        allRela.addAll(hashSet2);
        allRela.addAll(hashSet3);
        allRela.addAll(hashSet4);
        allRela.addAll(hashSet5);;
        allRela.addAll(hashSet6);

//        System.out.println(allRela);

        ArrayList<String> codeName = new ArrayList<>();
        String maxKey = null;

        while(allRela.size()>0){
            maxKey = null;
            for(Map.Entry<String,HashSet<String>> entry : pedigree.entrySet()){
                HashSet<String> relat = entry.getValue();
                relat.retainAll(allRela);
                HashSet<String> maxKeySet = null;
                if(maxKey!=null){
                   maxKeySet =  pedigree.get(maxKey);
                   maxKeySet.retainAll(allRela);
                }
                if(relat.size()>0 &&(maxKey==null || maxKeySet.size()<relat.size())){
                    maxKey = entry.getKey();
                }

            }
            if(maxKey!=null) {
                codeName.add(maxKey);
                System.out.println("add the codeName:"+maxKey+" and the value is :"+pedigree.get(maxKey));
                HashSet<String> tempKey = pedigree.get(maxKey);
                for(String key:tempKey){
                    allRela.remove(key);
                }
            }else{
                break;
            }
        }

    }
}
