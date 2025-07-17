package api;

public class CreateProductPost {
    private String name;
    private String article;
    private String category;
    private String dictionary;
    private Double price;
    private Integer qty;

    public CreateProductPost(String name, String article, String category, String dictionary, Double price, Integer qty) {
        this.name = name;
        this.article = article;
        this.category = category;
        this.dictionary = dictionary;
        this.price = price;
        this.qty = qty;
    }
    public CreateProductPost() {
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

    public Double getPrice() {
        return price;
    }

    public Integer getQty() {
        return qty;
    }
}
