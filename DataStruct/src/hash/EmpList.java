package hash;

/**
 * @author wang
 * @create 2023-2023-30-21:00
 */
public class EmpList {
    private Emp emp;
    private EmpList next;
    private int hashId;

    @Override
    public String toString() {
        return "EmpList{" +
                "emp=" + emp +
                ", next=" + next +
                ", hashId=" + hashId +
                '}';
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public EmpList getNext() {
        return next;
    }

    public void setNext(EmpList next) {
        this.next = next;
    }

    public int getHashId() {
        return hashId;
    }

    public void setHashId(int hashId) {
        this.hashId = hashId;
    }

    public EmpList() {
    }

    public EmpList(Emp emp) {
        this.hashId = emp.getId();
        this.emp = emp;
    }

    public void addNameAndAge(String name,int age){
        emp.setName(name);
        emp.setAge(age);
    }

    public void add(Emp emp){
        EmpList temp = this;
        while(temp!=null){
            if(temp.next!=null &&temp.next.emp.getId()>emp.getId()){
                EmpList empList = new EmpList(emp);
                empList.next = temp.next;
                temp.next = empList;
                break;

            }else if(temp.next == null){
                EmpList empList = new EmpList(emp);
                temp.next = empList;
                break;
            }
            temp = temp.next;
        }
    }

    public void update(Emp emp){
        EmpList temp = next;
        while(temp!=null){
            if(temp.emp.getId() == emp.getId()){
                temp.emp.setName(emp.getName());
                temp.emp.setAge(emp.getAge());
                break;
            }
            temp = temp.next;
        }
    }

    public void list(){
        EmpList temp = next;
        while(temp!=null){
            System.out.print(temp+" ");
            temp = temp.next;
        }
    }

    public void delete(int id){
        EmpList temp = this;

        while(temp.next !=null){
            if(temp.next.hashId == id){
                EmpList tempNext = temp.next;
                temp.next = tempNext.next;
                tempNext = null;
                System.out.println("find it");
                return;
            }
            temp = temp.next;
        }
        System.out.println("no have!!!!");
    }

    public EmpList find(int id){
        EmpList temp = next;
        while(temp!=null){
            if(temp.emp.getId() == id){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}
