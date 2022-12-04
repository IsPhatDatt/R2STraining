package r2s.training.entities;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private String name;

    private String phone;

    private String address;

    private List<Order> orders;

    public Customer() {}

    public Customer(String name, String phone, String address, List<Order> orders) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        try {
            String regex = "^0[98]{1}\\d{8}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(phone);
            if(matcher.find()) {
                this.phone = phone;
            }else {
                throw new IllegalArgumentException("Enter the wrong phone number format! Recommend: 0912345678");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
