package com.Class_.interface_lambda_innerClass.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * @author wang
 * @create 2022-2022-19-22:04
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[1000];

        for(int i = 0;i<elements.length;i++){
            Integer value = i+1;
            TraceHandle traceHandle = new TraceHandle(value);
            Class[] interfaces = new Class[]{Comparable.class};
            Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), interfaces, traceHandle);


            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length) + 1;

        int result = Arrays.binarySearch(elements, key);

        System.out.println(elements[result]);

    }

}

class TraceHandle implements InvocationHandler{

    private Object target;

    public TraceHandle(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("."+method.getName()+"(");

//        System.out.println();
        if(args!=null){
            for(int i =0;i<args.length;i++)
            {
                System.out.print(""+args[i].toString());
                if(i==args.length-1)
                    System.out.println(")");
            }
        }
//        System.out.println(")");
        return method.invoke(target,args );
    }
}
