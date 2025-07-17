package api;

import java.util.ArrayList;

public class CreateOrderPost {
    public String deliveryAddress;
    public ArrayList<Product> products;

    public CreateOrderPost() {
    }

    public CreateOrderPost(String deliveryAddress, ArrayList<Product> products) {
        this.deliveryAddress = deliveryAddress;
        this.products = products;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }


}
