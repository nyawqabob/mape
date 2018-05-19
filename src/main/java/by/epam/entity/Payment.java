package by.epam.entity;

public class Payment extends Entity {

    private double amount;
    private String date;
    private String publicationName;
    private int subscribtionPeriod;
    private int userId;
    private String finishDate;

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Payment() {
    }

    public Payment(double amount, String date, String publicationName, int subscribtionPeriod, int userId, String finishDate) {
        this.amount = amount;
        this.date = date;
        this.publicationName = publicationName;
        this.subscribtionPeriod = subscribtionPeriod;
        this.userId = userId;
        this.finishDate = finishDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public int getSubscribtionPeriod() {
        return subscribtionPeriod;
    }

    public void setSubscribtionPeriod(int subscribtionPeriod) {
        this.subscribtionPeriod = subscribtionPeriod;
    }

    @Override
    public int getId() {
        return userId;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int id) {
        userId = id;
    }
}
