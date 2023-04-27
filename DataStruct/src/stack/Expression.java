package stack;

import java.text.ParseException;

/**
 * @author wang
 * @create 2023-2023-26-12:08
 */
public class Expression {
    private ListStack charStack = new ListStack(10);
    private ListStack numStack = new ListStack(10);
    private String expression = "10*(20+30)*40/20+(30+20)*10+((20+30)*3+20)";


    public int getPritory(char ch){
        if(ch == '+'||ch=='-') {
            return 1;
        }else if(ch=='('){
            return 0;
        }else{
            return 2;
        }
    }
    public boolean judegeChar(char ch){
        return ch == '*' ||ch=='/' ||ch=='+' ||ch=='-';
    }

    public boolean judgeNum(char num){
        String s = ""+num;
        try {
            int numValue = Integer.parseInt(s);
        }catch(NumberFormatException e)
        {
            e.getMessage();
            return false;
        }
        return true;
    }


    public int caculate(String expression){
        int index = 0;
        while(index<expression.length())
        {
            char charA = expression.charAt(index);
            if(judegeChar(charA)){
                char oper = charA;
                if(charStack.isEmpty())
                {
                    charStack.add(oper);
                    index++;
                }else{
                    int pritory = getPritory(oper);
                    if(pritory>getPritory((char) charStack.peek())){
                        charStack.add(oper);
                        index++;
                    }
                    else{
                        int number1 = numStack.pop();
                        int number2 = numStack.pop();
                        int calRes = caculateTwoNumber(number1, number2, charStack.pop());
                        numStack.add(calRes);
                        charStack.add(oper);
                        index++;
                    }
                }
            }else if(judgeNum(charA)){
                String str = "";
                str +=charA;
                index++;

                while(index<expression.length()&&judgeNum(expression.charAt(index))){
                    str+=expression.charAt(index++);
                }
                int numValue = Integer.parseInt(str);
                numStack.add(numValue);
            }else if(charA=='(')
            {
                charStack.add(charA);
                index++;
            }else if(charA==')'){
                index++;
                char temp = ' ';
                while((temp= (char) charStack.pop())!='('){

                    int res = caculateTwoNumber(numStack.pop(), numStack.pop(), temp);
                    numStack.add(res);

                }
            }
        }
       while(!charStack.isEmpty()){
           int res = caculateTwoNumber(numStack.pop(), numStack.pop(), charStack.pop());
           numStack.add(res);
       }
        return numStack.pop();
    }

    private int caculateTwoNumber(int number1, int number2, int peek) {
        int res = 0;
        switch(peek){
            case '+':  res = number2+number1;
                        break;
            case '-': res = number2 - number1;
            break;

            case '*': res = number2 * number1;
            break;

            case '/': res = number2 / number1;
            break;

            default: break;
        }
        return res;
    }

    public static void main(String[] args) {
        Expression e = new Expression();
        System.out.println(e.caculate(e.expression));
    }
}
