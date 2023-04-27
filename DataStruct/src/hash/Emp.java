package hash;

/**
 * @author wang
 * @create 2023-2023-30-20:59
 */
public class Emp {
    private int id;
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Emp(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
