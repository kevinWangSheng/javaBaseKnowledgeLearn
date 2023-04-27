package leetcode.divded;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2023-2023-17-16:01
 */
public class ExpressionGetResult {


    public static void main(String[] args) {
        String expression = "23-12+78*32-3";
        char[] expressionChar = expression.toCharArray();
        ExpressionGetResult expressionGetResult = new ExpressionGetResult();
        List<Integer> list = expressionGetResult.dividedAndRule(expressionChar, 0, expressionChar.length );
        System.out.println(list.toString());
    }
    public List<Integer> dividedAndRule(char[] expression,int start, int end){

        List<Integer> list = new ArrayList<>();
        int num = 0;
        boolean isEnd = true;
        int numIndex = start;
        while(numIndex<expression.length && numIndex<=end){

            if(expression[numIndex]>=48 && expression[numIndex]<=57){
                num*=10;
                num+= expression[numIndex]-48;
            }else{
                isEnd = false;
                break;
            }
            numIndex++;
        }
        if(isEnd){
            list.add(num);
            return list;
        }
        List<Integer> left = null;
        List<Integer> right = null;
        int i = start;

        while(i<end){
            left = dividedAndRule(expression,start,numIndex(expression,i)-1);
            char ch = 0;
            i = numIndex(expression,i);
            ch = expression[i];
            i++;
            if(i>end){
                break;
            }

            right = dividedAndRule(expression,i,end);

            for(Integer le:left){
                for(Integer rig: right) {
                    int resul = 0;
                    if (ch == '*') {
                        resul = le * rig;
                    } else if (ch == '-') {
                        resul = le - rig;
                    } else if (ch == '+') {
                        resul = le + rig;
                    }
                    list.add(resul);
                }
            }
            if(numIndex(expression,i)>=end){
                break;
            }
        }
        return list;
    }

    public int numIndex(char[] expression,int start){
        while(start<expression.length&&expression[start]>=48&&expression[start]<=57){
            start++;
        }
        return start;
    }
    public List<Integer> rev(char[] expression,int start){
        List<Integer> list = new ArrayList();
        if(start==expression.length-2&&expression[start]>=48 && expression[start+1]==57){
            int num =expression[start]-48+expression[start+1]-48;
            list.add(num);
            return list;
        }else if(start==expression.length-1){
            int num = expression[start]-48;
            list.add(num);
            return list;
        }

        int i = start;
        while(i<expression.length-1){
            List<Integer> subSequence = null;
            int curNum = 0;
            if(isNum(expression,i)){
                subSequence = rev(expression,i+3);
                curNum = (expression[i]-48)*10+expression[i+1]-48;
            }else{
                subSequence = rev(expression,start+2);
                curNum = expression[i]-48;
            }
            char ch = 0;
            if(curNum>=10){
                ch = expression[i+2];
                i+=3;
            }else{
                ch = expression[i+1];
                i+=2;
            }

            switch(ch){
                case '*':mulit(subSequence,curNum);
                    break;
                case '+':add(subSequence,curNum);
                    break;
                case '-':sub(subSequence,curNum);
                    break;
            }
            list.addAll(subSequence);
        }
        return list;

    }

    public boolean isNum(char[] expression,int start){
        if(start<expression.length-2 && expression[start+1]>=48 && expression[start+1]<=57){
            return true;
        }
        return false;
    }

    public void mulit(List<Integer> list, int num){
        List<Integer> temp = new ArrayList(list);
        list.clear();
        for(Integer i1:temp){
            list.add(i1*num);
        }
    }

    public void add(List<Integer> list,int num){
        List<Integer> temp = new ArrayList(list);
        list.clear();
        for(Integer i1:temp){
            list.add(i1+num);
        }
    }

    public void sub(List<Integer> list,int num){
        List<Integer> temp = new ArrayList(list);
        list.clear();
        for(Integer i1:temp){
            list.add(num-i1);
        }
    }

    public int calcuNum(char[] expression,int end){
        int i = 0;
        int result = 0;
        int num1  = 0;

        if(isNum(expression,i)){
            num1 = (expression[i]-48)*10+expression[i+1]-48;
            i+=2;
        }else{
            num1 = expression[i]-48;
            i++;
        }
        result = num1;
        while(i<end){
            int num2 = 0;
            char ch = expression[i];
            i++;
            if(isNum(expression,i)){
                num2 = (expression[i]-48)*10+expression[i+1]-48;
                i+=2;
            }else{
                num2 = expression[i]-48;
                i++;
            }

            switch (ch) {
                case '*':
                    result += num1 * num2;
                    break;
                case '+':
                    result += num1 + num2;
                    break;
                case '-':
                    result += num1 - num2;
                    break;

            }
        }
        return result;
    }
}
