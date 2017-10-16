package com.nicordesigns.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String id;

    @JsonIgnore
    @ManyToMany
    private Set<Account> accounts = new HashSet<>();

    protected String categoryName;

    protected String contextType;

    protected String scheduleC;


    public Category() {
    }

    public Category(Account account, String name, String id) {
        this.id = id;
        this.categoryName = name;
        this.accounts.add(account);
    }

    public Category(String name, String id) {
        this.categoryName = name;
        this.id = id;
    }
    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }
    public String getScheduleC() {
        return scheduleC;
    }
    public void setScheduleC(String scheduleC) {
        this.scheduleC = scheduleC;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
