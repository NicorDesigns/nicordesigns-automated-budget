package com.nicordesigns.model;

import java.io.Serializable;

public class CategoryIDB implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String categoryName;

    protected String contextType;

    protected String scheduleC;

    public CategoryIDB() {
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    public String getContextType() {
        return this.contextType;
    }

    public void setContextType(String value) {
        this.contextType = value;
    }

    public String getScheduleC() {
        return this.scheduleC;
    }

    public void setScheduleC(String value) {
        scheduleC = value;
    }
}
