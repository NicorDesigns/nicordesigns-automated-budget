package com.nicordesigns.finance.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String id;


    protected String categoryName;

    protected String contextType;

    protected String scheduleC;


    private Category() {
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
