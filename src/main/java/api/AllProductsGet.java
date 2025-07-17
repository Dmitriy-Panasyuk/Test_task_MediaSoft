package api;


public class AllProductsGet {
    private String name;
    private String article;
    private String id;
    private String category;
    private Double price;
    private Integer qty;
    private String insertedAt;
    private String last_qty_changed;
    private String currency;

    public AllProductsGet(String name, String article, String id, String category, Double price, Integer qty, String insertedAt, String last_qty_changed, String currency) {
        this.name = name;
        this.article = article;
        this.id = id;
        this.category = category;
        this.price = price;
        this.qty = qty;
        this.insertedAt = insertedAt;
        this.last_qty_changed = last_qty_changed;
        this.currency = currency;
    }

    public AllProductsGet() {
    }

    public String toString() {
        String s = "            name : " + name
                + "         article : " + article
                + "              id : " + id
                + "        category : " + category
                + "           price : " + price
                + "             qty : " + qty
                + "      insertedAt : " + insertedAt
                + "last_qty_changed : " + last_qty_changed
                + "        currency : " + currency;
        return s;
    }

    public String getName() {
        return name;
    }

    public String getArticle() {
        return article;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQty() {
        return qty;
    }

    public String getInsertedAt() {
        return insertedAt;
    }

    public String getLast_qty_changed() {
        return last_qty_changed;
    }

    public String getCurrency() {
        return currency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setInsertedAt(String insertedAt) {
        this.insertedAt = insertedAt;
    }

    public void setLast_qty_changed(String last_qty_changed) {
        this.last_qty_changed = last_qty_changed;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}