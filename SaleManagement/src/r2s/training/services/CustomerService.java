package r2s.training.services;

import r2s.training.entities.Customer;
import r2s.training.entities.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CustomerService {
    public static List<Customer> customers;
    public void createCustomer(){
        customers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String charStopCustomer = "";
        String name, phone, address, number, date;
        int quantity;
        double price;

        while(true) {
            String charStopOrder = "";
            System.out.println("----Enter Customer information---");
            System.out.print("Enter name: ");
            name = sc.next();
            System.out.print("Enter phone: ");
            phone = sc.next();
            System.out.print("Enter address: ");
            address = sc.next();
            List<Order> orders = new ArrayList<>();
            while (true) {
                System.out.println("----Enter Order information---");
                System.out.print("Enter number: ");
                number = sc.next();
                System.out.print("Enter date: ");
                date = sc.next();
                System.out.print("Enter qunatity: ");
                quantity = sc.nextInt();
                System.out.print("Enter price: ");
                price = sc.nextDouble();
                sc.nextLine();
                System.out.println("Press any key to continue enter order or n/N to finish!");
                charStopOrder = sc.next();
                Date date1;
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Order order = new Order(number, date1, quantity, price);
                orders.add(order);
                if(charStopOrder.equals("n") || charStopOrder.equals("N")) {
                    break;
                }
            }
            Customer customer = new Customer(name, phone, address, orders);
            customers.add(customer);
            sc.nextLine();
            System.out.println("Press any key to continue enter customer or n/N to finish!");
            charStopCustomer = sc.next();
            if(charStopCustomer.equals("n") || charStopCustomer.equals("N")) {
                break;
            }
        }
    }

    public List<Customer> findAll(){
        return customers;
    }

    public void display(List<Customer> customers) {
        for (Customer customer: customers) {
            System.out.println("Name="+ customer.getName() +"\nPhone="+ customer.getPhone() +"\nAddress="+ customer.getAddress());
        }
    }

    public boolean remove(String phone) {
        for (Customer customer: customers) {
            if (customer.getPhone().equals(phone)) {
                customers.remove(customer);
                return true;
            }
        }
        return false;
    }
}
