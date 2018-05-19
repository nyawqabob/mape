package by.epam.entity;

public class Publication extends Entity {

    private String name;
    private String type;
    private double price;
    private String image;
    private String periodType;
    private int id;

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public Publication() {
    }

    public String getImage() {
        return image;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publication(int id, String name, String type, double price, String image, String periodType) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.image = image;
        this.periodType = periodType;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

}
