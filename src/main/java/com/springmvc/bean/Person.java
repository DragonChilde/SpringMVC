package com.springmvc.bean;

/**
 * @author Lee
 * @create 2019/11/12 16:55
 */
public class Person {

    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello()
    {
        System.out.println("My name is "+ name);
    }
}
