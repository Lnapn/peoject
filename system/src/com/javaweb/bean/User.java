package com.javaweb.bean;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Objects;

public class User implements HttpSessionBindingListener{

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        ServletContext application = event.getSession().getServletContext();
        Object online = application.getAttribute("Online");
        if (online == null){
            application.setAttribute("Online",1);
        }else {
            int count = ((Integer)online);
            count ++;
            application.setAttribute("Online",count);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        ServletContext application = event.getSession().getServletContext();
        Object online = application.getAttribute("Online");
        int count = (Integer)online;
        count--;
        application.setAttribute("Online",count);
    }

    int no;
    String fname;
    String password;
    String phone;
    String age;

    public User(int no, String fname, String password, String phone, String age) {
        this.no = no;
        this.fname = fname;
        this.password = password;
        this.phone = phone;
        this.age = age;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return no == user.no && Objects.equals(fname, user.fname) && Objects.equals(password, user.password) && Objects.equals(phone, user.phone) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, fname, password, phone, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "no=" + no +
                ", fname='" + fname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

}
