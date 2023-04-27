package com.Class_.Program;

/**
 * @author wang
 * @create 2022-2022-16-16:55
 */
public class ShareTest {
    public static void main(String[] args) {
        ShareTest pt = new ShareTest();

        User hollis = new User();
        hollis.setName("Hollis");
        hollis.setGender("Male");
        pt.pass(hollis);
        System.out.println("print in main , user is " + hollis);
    }

    public void pass(User user) {
        user.setName("hollischuang");
        System.out.println("print in pass , user is " + user);
    }
/**<strong>wahahh</strong>娃哈哈*/
}
class User{
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    private String gender;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
