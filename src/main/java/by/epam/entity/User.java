package by.epam.entity;

public class User extends Entity {

    private String name;
    private String pass;
    private int id;
    private double balance;
    private String role;
    private String status;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(int id, String name, String pass, double balance, String role, String status, String email) {
        this.id = id;
        this.pass = pass;
        this.balance = balance;
        this.role = role;
        this.status = status;
        this.name = name;
        this.email = email;
    }

}
