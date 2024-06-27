package com.socialnetwork.socialnetwork.infrastructure.entities.enums;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    private String getRole() {
        return role;
    }
}
