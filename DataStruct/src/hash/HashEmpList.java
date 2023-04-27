package hash;

import java.util.Scanner;

/**
 * @author wang
 * @create 2023-2023-30-21:02
 */
public class HashEmpList {
    private EmpList[] hashEmpList;
    private int size;

    public int hashFun(int hashId){
        return hashId%13;
    }

    public HashEmpList(int size){
        hashEmpList = new EmpList[13];
        this.size = size;
        for(int i = 0;i<hashEmpList.length;i++){
            hashEmpList[i] = new EmpList();
        }
    }
    public void add(Emp emp){
        int hashFun = hashFun(emp.getId());
        hashEmpList[hashFun].add(emp);
    }

    public EmpList find(int empId){
        int hasFun = hashFun(empId);

        EmpList empList = hashEmpList[hasFun].find(empId);
        System.out.println("the find empList is"+empList);
        return empList;
    }

    public void list(){
        for(int i = 0;i<hashEmpList.length;i++){
            System.out.print("this is the "+i+" emplist:");
            hashEmpList[i].list();
            System.out.println();
        }
    }

    public void delete(int empId){
        int hashFun = hashFun(empId);
        hashEmpList[hashFun].delete(empId);
    }

    public void update(Emp emp){
        int hashFun = hashFun(emp.getId());

        hashEmpList[hashFun].update(emp);
    }

    public static void main(String[] args) {
        HashEmpList hashEmpList = new HashEmpList(13);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("l:list");
            System.out.println("d:delete");
            System.out.println("f:find");
            System.out.println("a:add");
            System.out.println("u:update");
            String command = sc.next();

            int empId;
            String name;
            int age;
            switch(command){
                case "l":
                    hashEmpList.list();
                    break;


                case "f":
                    System.out.println("input the emp's id");
                    empId = sc.nextInt();
                    EmpList empList = hashEmpList.find(empId);
                    break;

                case "d":
                    System.out.println("input the emp's that you want to delete");
                    empId = sc.nextInt();
                    hashEmpList.delete(empId);
                    break;

                case "a":
                    System.out.println("input the emp's name that you want to add");
                    name = sc.next();
                    System.out.println("the age");
                    age = sc.nextInt();
                    System.out.println("the id");
                    empId = sc.nextInt();
                    hashEmpList.add(new Emp(empId,name,age));
                    break;

                case "u":
                    System.out.println("input the emp's id that you want to update");
                    empId = sc.nextInt();
                    System.out.println("input the name");
                    name = sc.next();
                    System.out.println("input the age");
                    age = sc.nextInt();
                    hashEmpList.update(new Emp(empId,name,age));
                    break;
                default:
                    return;
            }
            System.out.println("input again");
        }
    }
}
