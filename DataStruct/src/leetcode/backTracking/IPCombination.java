package leetcode.backTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-24-13:20
 */
public class IPCombination {

    public static void main(String[] args) {
        String str = "25525511135";
        IPCombination ipCombination = new IPCombination();
        ipCombination.restoreIpAddresses(str);
    }

    List<String> result = new ArrayList();
    public List<String> restoreIpAddresses(String s) {
        if(s==null || s.length()==0){
            return result;
        }
        dfs(s,"",0);
        return result;
    }

    public void dfs(String s,String target,int index){
        if(index>=s.length()){
            if(isReasonable(target)){
                result.add(target);
            }
            return ;
        }
        char ch = s.charAt(index);
        int lastIndexOfPoint = target.lastIndexOf('.');
//        if(lastIndexOfPoint!=-1 && lastIndexOfPoint==target.length()-1 && s.charAt(index)=='0'){
//            dfs(s,target+ch+".",index+1);
//        }else if(lastIndexOfPoint!=-1 && lastIndexOfPoint== target.length()-4){
//            dfs(s,target+"."+ch,index+1);
//        }else if(lastIndexOfPoint !=-1 && lastIndexOfPoint == target.length()-1){
//            dfs(s,target+ch,index+1);
//        }else if(lastIndexOfPoint!=-1 && lastIndexOfPoint==target.length()-3){
//            String subStr = target.substring(target.length()-2,target.length());
//            if(subStr.compareTo("25")<0){
//                dfs(s,target+"."+ch,index+1);
//            }else{
//                dfs(s,target+ch,index+1);
//            }
//        }else{
            dfs(s,target+ch,index+1);
            dfs(s,target+"."+ch,index+1);
//        }
    }

    public boolean isReasonable(String target){
        String[] subIp = target.split("\\.");
        if(subIp.length>4){
            return false;
        }
        for(String ip:subIp){
            if(ip.length()>3){
                return false;
            }else if(ip.length()>1 && ip.charAt(0)=='0'){
                return false;
            }else if(ip.compareTo("255")>0){
                return false;
            }
        }
        return true;
    }
}
