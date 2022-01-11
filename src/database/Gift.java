package database;

import enums.Category;
import org.json.simple.JSONObject;

public class Gift {

    public Gift() { }

    private String productName;
    private Double price;
    private Category category;

    /**
     * @return
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(final String productName) {
        this.productName = productName;
    }

    /**
     * @return
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(final Double price) {
        this.price = price;
    }

    /**
     * @return
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

    /**
     * @return
     */
    public JSONObject toJSONObject() {
        JSONObject output = new JSONObject();

        output.put("productName", this.productName);
        output.put("price", this.price);
        output.put("category", this.category.getValue());

        return output;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Gift{"
                +
                "productName='" + productName + '\''
                +
                ", price=" + price
                +
                ", category=" + category
                +
                '}';
    }
}
