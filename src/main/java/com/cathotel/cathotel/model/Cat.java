package com.cathotel.cathotel.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable= false,updatable = false )
    private Long id;
    private String name;
    private String colour;

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    private boolean registered;
    @OneToOne
    private Registration reg;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    private  Long user_id;


    public Cat( String name, String colour, Long user_id) {
        this.name = name;
        this.colour = colour;
        this.registered =false;
        this.reg=null;
        this.user_id = user_id;
    }

    public Cat() { }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Registration getReg(){ return  reg;}
    public void setReg(Registration reg){
        this.reg=reg;
        if(reg!=null) registered =true;
        else registered =false;
    }
}
