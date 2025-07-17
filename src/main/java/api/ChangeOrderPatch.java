package api;

import java.util.ArrayList;

public class ChangeOrderPatch {
    public ArrayList<Product> products;

    public ChangeOrderPatch() {
    }

    public ChangeOrderPatch(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
