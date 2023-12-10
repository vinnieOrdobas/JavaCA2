package com.garden_center;
/*
*User.java
*@author Vinnie Ordobas
*09/12/2023
*/

// import necessary libraries
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
    private Date created_at;
    private List<Order> orders;

    public User(Integer id, String userName, String password, String email, Date created_at) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.created_at = created_at;
        this.orders = new ArrayList<Order>();
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated_at() {
        return created_at;
    }

    // Verify whether username and login are correct
    public boolean verifyLogin(String userName, String password) {
        return this.userName.equals(userName) && this.password.equals(password);
    }

    // Get orders
    public List<Order> getOrders() {
        return orders;
    }

    public Order findOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }
}
