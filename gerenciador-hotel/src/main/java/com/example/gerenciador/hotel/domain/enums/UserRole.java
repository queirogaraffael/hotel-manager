package com.example.gerenciador.hotel.domain.enums;

public enum UserRole {
    ADMIN("admin"),
    HOSPEDE("hospede"),
    FUNCIONARIO("funcionario");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
