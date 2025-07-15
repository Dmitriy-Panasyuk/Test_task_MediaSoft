package api;

public class CreateProduct {
    private String name;
    private String article;
    private String category;
    private String dictionary;
    private Integer price;
    private Integer qty;

    public CreateProduct(String name, String article, String category, String dictionary, Integer price, Integer qty) {
        this.name = name;
        this.article = article;
        this.category = category;
        this.dictionary = dictionary;
        this.price = price;
        this.qty = qty;
    }


    public String getName() {
        return name;
    }

    public String getArticle() {
        return article;
    }

    public String getCategory() {
        return category;
    }

    public String getDictionary() {
        return dictionary;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQty() {
        return qty;
    }
}
