package api;

public class Product {

    public String id;
    public Integer qty;

    public Product() {
    }

    public Product(String id, Integer qty) {
        this.id = id;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

}
