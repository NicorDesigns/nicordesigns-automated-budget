package com.nicordesigns.finance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
@Entity
public class Categorization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    public String id;

    protected String merchant;

    private String category;

    @OneToMany
    private List<BankingTransaction> bankingTransactions;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }


    //protected List<Category> categories;

    private Categorization() {
    }

    public List<BankingTransaction> getBankingTransactions() {
        return bankingTransactions;
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


    public Categorization(String merchant, String category) {
        this.merchant = merchant;
        this.category = category;
    }
}

