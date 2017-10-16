package com.nicordesigns.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class SubCategory extends Category implements Serializable {

    @JsonIgnore
    @ManyToOne
    private MainCategory mainCategory;

    public SubCategory(Account account, MainCategory mainCategory, String name, String id) {
        super(account, name, id);
        this.mainCategory = mainCategory;
    }

    public SubCategory(MainCategory mainCategory) {
        super();
        this.mainCategory = mainCategory;
    }

    public SubCategory() {

    }
}
