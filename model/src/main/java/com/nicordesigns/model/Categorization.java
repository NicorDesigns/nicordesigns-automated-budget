package com.nicordesigns.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Categorization implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Merchant merchant;
    protected List<Category> categories;

    public Categorization() {
    }

    public List<Category> getCategories() {
        if (this.categories == null) {
            this.categories = new ArrayList();
        }

        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}

