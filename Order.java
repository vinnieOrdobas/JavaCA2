/*
*Order.java
*@author Vinnie Ordobas
*09/12/2023
*/


import java.util.Date;
import java.util.ArrayList;

public class Order {
    private int orderId;
    private int customerId;
    private int cartId;
    private Status status;
    private Date orderDate;
    private float totalAmount;
    private ArrayList<OrderLineItem> lineItems;

    public Order(int orderId, int customerId, int cartId, Status status, Date orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.cartId = cartId;
        this.status = status;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getCartId() {
        return cartId;
    }

    public Status getStatus() {
        return status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    // Set totalAmount
    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    // Set lineItems
    public void setLineItems(ArrayList<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public ArrayList<OrderLineItem> getLineItems() {
        return this.lineItems;
    }

    public int getLineItemCount() {
        return this.lineItems.size();
    }

    public void updateStatus(Status newStatus) {
        this.status = newStatus;
    }

    public enum Status {
        OPEN,
        PAID,
        CANCELLED,
        SHIPPED,
        DELIVERED
    }
}
