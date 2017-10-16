package com.nicordesigns.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String password;
    public String username;

    @Id
    public String id;

    @OneToMany(mappedBy = "accounts")
    private Set<Category> categories = new HashSet<>();

    public Account(String name, String password) {
        this.username = name;
        this.password = password;
    }

    Account() {
        //required
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
