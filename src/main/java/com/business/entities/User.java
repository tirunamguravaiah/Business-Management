package com.business.entities;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "users")
public class User {

    @Id
    private String u_id; // MongoDB IDs are typically strings

    private String uname;
    private String uemail;
    private String upassword;
    private Long unumber;

    // MongoDB reference to Orders (similar to OneToMany)
    @DBRef
    private List<Orders> orders;

    // --- Getters and Setters ---
    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public Long getUnumber() {
        return unumber;
    }

    public void setUnumber(Long unumber) {
        this.unumber = unumber;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User [u_id=" + u_id + ", uname=" + uname + ", uemail=" + uemail +
                ", upassword=" + upassword + ", unumber=" + unumber + ", orders=" + orders + "]";
    }
}
