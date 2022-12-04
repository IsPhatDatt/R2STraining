package r2s.training.services;

import r2s.training.entities.Customer;
import r2s.training.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    public List<Order> search(String phone) {
        List<Order> result = new ArrayList<>();
        for (Customer customer: CustomerService.customers) {
            if (customer.getPhone().equals(phone)) {
                for (Order order: customer.getOrders()) {
                    result.add(order);
                }
            }
        }
        return result;
    }

    public void display(List<Order> orders) {
        for (Order order: orders) {
            System.out.println("Number: " + order.getNumber() + "\n"
                    + "Date: " + order.getDate() + "\n"
                    + "Quantity: " + order.getQuantity() + "\n"
                    + "Price: " + order.getPrice());
        }
    }
}
