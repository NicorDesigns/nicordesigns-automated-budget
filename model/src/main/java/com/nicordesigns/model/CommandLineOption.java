package com.nicordesigns.model;

public enum CommandLineOption {

    MAIN_CATEGORIES("m", "main", "populate main categories in mongodb via rest hateos"),

    SUB_CATEGORIES("s", "sub", "populate sub categories in mongodb via rest hateos");

    private String shortName;
    private String longName;
    private String description;

    CommandLineOption(String shortName, String longName, String description) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
    }

    public String shortName() {
        return shortName;
    }

    public String longName() {
        return longName;
    }

    public String description() {
        return description;
    }
}
