package com.example.zhenhuan.DB;

public class Rule {
    private int id;
    private String name;
    private int sex;
    private int age;
    private int family;
    private int isDead;

    public Rule(){}

    public Rule(int id, String name, int sex, int age, int family, int isDead) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.family = family;
        this.isDead = isDead;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    public int getIsDead() {
        return isDead;
    }

    public void setIsDead(int isDead) {
        this.isDead = isDead;
    }
}
