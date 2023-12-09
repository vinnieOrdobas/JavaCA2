/*
*User.java
*@author Vinnie Ordobas
*09/12/2023
*/

// import necessary libraries
import java.util.Date;
import java.util.Map;
import java.util.ArrayList;

public class Customer extends User {
    private String customerName;
    private String address;
    private String phoneNumber;
    private String creditCardNumber;
    private String shippingInfo;
    private ArrayList<Cart> carts;

    public Customer(Integer id, String userName, String password, String customerName, String address, String phoneNumber, String creditCardNumber, String shippingInfo, String email, Date created_at) {
        super(id, userName, password, email, created_at);
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.creditCardNumber = creditCardNumber;
        this.shippingInfo = shippingInfo;
        this.carts = new ArrayList<>();
        // Create a new cart for the customer on creation
        this.carts.add(new Cart(1, this.getId(), Cart.CartStatus.OPEN));
    }

    public void login(String username, String password) {
        if (super.verifyLogin(username, password)) {
            // Pseudocode for logging the customer into the system
            System.out.println("Customer " + username + " is logged in.");
        }
    }

    // Getters

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getShippingInfo() {
        return shippingInfo;
    }

    // Update customer profile
    public void updateProfile(Map<String, Object> attributes) {
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            switch (entry.getKey()) {
                case "customerName":
                    this.customerName = (String) entry.getValue();
                    break;
                case "address":
                    this.address = (String) entry.getValue();
                    break;
                case "phoneNumber":
                    this.phoneNumber = (String) entry.getValue();
                    break;
                case "creditCardNumber":
                    this.creditCardNumber = (String) entry.getValue();
                    break;
                case "shippingInfo":
                    this.shippingInfo = (String) entry.getValue();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid attribute: " + entry.getKey());
            }
        }
    }

    // Get cart from carts with cartId
    public Cart getCart(int cartId) {
        for (Cart cart : this.carts) {
            if (cart.getCartId() == cartId) {
                return cart;
            }
        }
        throw new IllegalArgumentException("Cart with id " + cartId + " not found.");
    }

    // Get current cart (cart with status OPEN)
    public Cart getCurrentCart() {
        for (Cart cart : this.carts) {
            if (cart.getStatus() == Cart.CartStatus.OPEN) {
                return cart;
            }
        }
        throw new IllegalArgumentException("No open cart found.");
    }

    // Create a new cart for the customer
    public void createNewCart() {
        int newCartId = (this.carts.isEmpty()) ? 1 : this.carts.get(this.carts.size() - 1).getCartId() + 1;
        Cart newCart = new Cart(newCartId, this.getId(), Cart.CartStatus.OPEN);
        this.carts.add(newCart);
    }


    public void createOrder() {
        // Create a new order from the current cart
        Cart currentCart = this.getCurrentCart();
        ArrayList<OrderLineItem> lineItems = new ArrayList<>();
        float totalAmount = 0;
        
        // Create a new order with orderId = last order's orderId + 1
        int newOrderId = (this.getOrders().isEmpty()) ? 1 : this.getOrders().get(this.getOrders().size() - 1).getOrderId() + 1;
        Order newOrder = new Order(newOrderId, this.getId(), currentCart.getCartId(), Order.Status.OPEN, new Date());
        this.getOrders().add(newOrder);
        
        // Create line items for the order
        int lineItemCount = 0;
        for (Map.Entry<Product, Integer> entry : currentCart.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            lineItemCount += quantity;
            OrderLineItem lineItem = new OrderLineItem(lineItemCount, product.getProductId(), newOrder.getOrderId());
            lineItems.add(lineItem);
            totalAmount += product.getPrice() * quantity; // Assumes Product has a getPrice() method
        }

        // Update the order's line items and total amount
        newOrder.setLineItems(lineItems);
        newOrder.setTotalAmount(totalAmount);

        // Update the current cart's status to CLOSED
        currentCart.updateStatus(Cart.CartStatus.CLOSED);

        // Create a new cart for the customer
        this.createNewCart();
    }
}