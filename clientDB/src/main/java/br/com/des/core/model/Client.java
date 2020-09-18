package br.com.des.core.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;


public class Client {

    @Id
    private String phone;
    private String name;
    private String voice;
    private Integer age;
    private Date visitedDate;
    private boolean isAdmin;


    public Client(){

    }

    public Client(String phone,String voice, String name, Integer age, Date visitedDate,boolean isAdmin) {
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.voice = voice;
        this.isAdmin = isAdmin;
        this.visitedDate = visitedDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(){
        this.isAdmin = isAdmin;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getVisitedDate() {
        return visitedDate;
    }

    public void setVisitedDate(Date visitedDate) {
        this.visitedDate = visitedDate;
    }

}
