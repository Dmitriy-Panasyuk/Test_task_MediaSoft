package api;

public class ChangeProductPatch {
    private String id;
    private String name;
    private String category;
    private String dictionary;
    private Double price;
    private Integer qty;

    public ChangeProductPatch(String id, String name, String category, String dictionary, Double price, Integer qty) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.dictionary = dictionary;
        this.price = price;
        this.qty = qty;
    }

    public ChangeProductPatch() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDictionary() {
        return dictionary;
    }

    public void setDictionary(String dictionary) {
        this.dictionary = dictionary;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
