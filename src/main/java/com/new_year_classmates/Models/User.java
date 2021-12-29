package com.new_year_classmates.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "usr")
@Setter
@Getter
public class User {
    @Id
    private Integer id;

    @Column
    private String name;
    private String username;
    private String password;
    private String photo;

    public User(Integer id,
            String name,
         String username,
         String password,
         String photo){
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.photo = photo;
    }

    public User(){}
}
