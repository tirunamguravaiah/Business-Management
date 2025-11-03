package com.business.entities;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "orders")
public class Orders {

    @Id
    private String oId; // MongoDB IDs are typically strings/ObjectIds

    private String oName;
    private double oPrice;
    private int oQuantity;
    private Date orderDate;
    private double totalAmount;

    // Reference to another document (User)
    @DBRef
    private User user;

    // --- Getters and Setters ---
    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public double getoPrice() {
        return oPrice;
    }

    public void setoPrice(double oPrice) {
        this.oPrice = oPrice;
    }

    public int getoQuantity() {
        return oQuantity;
    }

    public void setoQuantity(int oQuantity) {
        this.oQuantity = oQuantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Orders [oId=" + oId + ", oName=" + oName + ", oPrice=" + oPrice + ", oQuantity=" + oQuantity
                + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + ", user=" + user + "]";
    }
}
