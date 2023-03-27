package utilityClasses.json.project_reference;

public class ProductPackage {
    private String productPackage;
    private String basePrice;
    private String year1Price;
    private String year2Price;

    ProductPackage(String productPackage){
        this.productPackage=productPackage;
    }

    public String getProductPackage() {
        return productPackage;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getYear1Price() {
        return year1Price;
    }

    public void setYear1Price(String year1Price) {
        this.year1Price = year1Price;
    }

    public String getYear2Price() {
        return year2Price;
    }

    public void setYear2Price(String year2Price) {
        this.year2Price = year2Price;
    }
}
