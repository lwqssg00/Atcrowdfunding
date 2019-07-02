package com.atguigu.atcrowdfunding.bean;


import javax.persistence.*;

@Table(name = "t_icon")
public class TIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "icon")
    private String icon;

    public TIcon() {
    }

    public TIcon(Integer id, String icon) {
        this.id = id;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
