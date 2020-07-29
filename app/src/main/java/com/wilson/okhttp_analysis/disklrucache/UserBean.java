package com.wilson.okhttp_analysis.disklrucache;

import java.io.Serializable;

public class UserBean implements Serializable {
    String Name;
    int age;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserBean(String name, int age) {
        Name = name;
        this.age = age;
    }


}

