package com.nicordesigns.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MainCategory extends Category implements Serializable {

    @JsonIgnore
    @OneToMany(mappedBy = "mainCategory")
    private Set<SubCategory> subCategories = new HashSet<>();

    /**
     * Required for MongoDB Repository
     */
    public MainCategory() {
        super();
    }

    public MainCategory(Account account, String name, String id) {
        super(account, name, id);
    }
}
