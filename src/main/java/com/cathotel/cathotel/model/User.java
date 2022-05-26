package com.cathotel.cathotel.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable= false,updatable = false )
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(nullable= false,updatable = false )
    private String roles;
    @OneToMany
    private List<Cat> cats;
    @OneToMany
    private List<Registration> regs;

    public User(String name, String email, String password, List<Cat> cats, List<Registration> regs) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = "user";
        this.cats = cats;
        this.regs = regs;
    }

    public User() {
        this.roles="";
    }

    public Long getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public void addCat(Cat newCat){
        this.cats.add(newCat);
    }

    public void setRoles(String roles) {
        this.roles=roles;
    }

    public List<Registration> getRegs() {
        return regs;
    }

    public void setRegs(List<Registration> regs) {
        this.regs = regs;
    }

    public void addReg(Registration reg){
        regs.add(reg);
    }

    public void removeReg(Long reg_id){
        for(int i=0; i<regs.size(); i++){
            if(regs.get(i).getId()==reg_id){
                regs.remove(i);
            }
        }
    }
}
