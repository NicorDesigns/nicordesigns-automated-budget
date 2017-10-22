package com.nicordesigns.finanance.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@Entity
public class Categorization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String id;

    protected Merchant merchant;

    //protected List<Category> categories;

    private Categorization() {
    }

//    public List<Category> getCategories() {
//        if (this.categories == null) {
//            this.categories = new ArrayList();
//        }
//
//        return categories;
//    }

//    public void setCategories(List<Category> categories) {
//        this.categories = categories;
//    }

}

