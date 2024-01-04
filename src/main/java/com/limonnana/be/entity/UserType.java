package com.limonnana.be.entity;

public enum UserType {

    ADMIN("ADMIN"), USER("USER");

    private final String type;

    UserType(String string) {
        type = string;
    }

    @Override
    public String toString() {
        return type;
    }
}
