package com.Class_.interface_lambda_innerClass.dynamicProxy;

import com.Class_.interface_lambda_innerClass.Interface.Employee;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wang
 * @create 2022-2022-19-22:17
 */
public class EmployeeDynamicTest {
    public static void main(String[] args) {
        Class[] interfaces = new Class[]{DynamicInterface.class};

        Employee employee  = new Employee("wangwu",2999);

        EmployeeHandler handler = new EmployeeHandler(new DynamicInterfaceImpl());

        DynamicInterface dynamicInterfaceImpl = (DynamicInterface) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), interfaces, handler);
        Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{DynamicInterface.class},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return "wahah";
                    }
                });

        System.out.println(proxy.toString());
        dynamicInterfaceImpl.play();


    }
}

class EmployeeHandler implements InvocationHandler{
    private DynamicInterfaceImpl employee;

    public EmployeeHandler(DynamicInterfaceImpl employee) {
        this.employee = employee;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dynamic proxy");

//        System.out.println(employee.getName());
        return "wahah";

//        return (Employee) method.invoke(employee,args);
    }
}
