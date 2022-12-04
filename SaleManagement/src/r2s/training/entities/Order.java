package r2s.training.entities;

import java.util.Date;

public class Order {
    private String number;

    private Date date;

    private int quantity;

    private double price;

    public Order() {}

    public Order(String number, Date date, int quantity, double price) {
        this.number = number;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        try {
            if(number.length() == 10) {
                this.number = number;
            }
            else {
                throw new Exception("Order number has a length of 10!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
