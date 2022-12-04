package r2s.training.entities;

import java.util.Date;

public class Order {
    private String number;

    private Date date;

    private int quantity;

    private int price;

    public Order() {}

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
