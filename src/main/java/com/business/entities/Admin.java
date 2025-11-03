package com.business.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.beans.factory.annotation.Value;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Document(collection = "admins") // MongoDB collection name
public class Admin {

    @Id
    private String adminId; // MongoDB uses String/ObjectId instead of int

    @NotNull
    @Size(min = 2, message = "Admin name should have at least 2 characters")
    private String adminName;

    @Email(message = "Please provide a valid email address")
    private String adminEmail;

    @Value("1234")
    private String adminPassword;

    private String adminNumber;

    // ------------------------------
    // Getters and Setters
    // ------------------------------
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + ", adminName=" + adminName +
                ", adminEmail=" + adminEmail + ", adminPassword=" + adminPassword +
                ", adminNumber=" + adminNumber + "]";
    }
}
