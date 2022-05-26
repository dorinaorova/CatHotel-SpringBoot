package com.cathotel.cathotel.model;

import javax.persistence.*;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable= false,updatable = false )
    private Long id;
    private Long cat_id;
    private String start;

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    private String end;

    public Registration() {
    }

    public Registration(Long cat, String start, String end) {
        this.start = start;
        this.cat_id=cat;
        this.end=end;
    }

    public Long getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String date) {
        this.start = date;
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat(Long cat) {
        this.cat_id = cat;
    }
}
