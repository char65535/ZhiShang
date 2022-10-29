package cn.itcast.zhishang.bean;

import java.io.Serializable;

public class Person implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String pwd;
    private String rePwd;

    public Person(Integer id, String name, String email, String pwd, String rePwd) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.rePwd = rePwd;
    }

    public Person(String name, String email, String pwd, String rePwd) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.rePwd = rePwd;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRePwd() {
        return rePwd;
    }

    public void setRePwd(String rePwd) {
        this.rePwd = rePwd;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", rePwd='" + rePwd + '\'' +
                '}';
    }
}
