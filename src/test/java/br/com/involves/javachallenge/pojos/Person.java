package br.com.involves.javachallenge.pojos;

/**
 *
 * @author adriano
 */
public class Person {

    private String fullName;
    private int age;
    private String[] skills;

    public Person() {
    }

    public Person(String fullName, int age, String[] skills) {
        this.fullName = fullName;
        this.age = age;
        this.skills = skills;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

}
