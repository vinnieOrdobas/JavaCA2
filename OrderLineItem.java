/*
*Cart.java
*@author Vinnie Ordobas
*09/12/2023
*/

public class OrderLineItem {
    private int lineItemId;
    private int orderId;
    private int productId;

    public OrderLineItem(int lineItemId, int orderId, int productId) {
        this.lineItemId = lineItemId;
        this.orderId = orderId;
        this.productId = productId;
    }

    public int getLineItemId() {
        return lineItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }
}
