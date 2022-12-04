package r2s.training.main;

import r2s.training.entities.Customer;
import r2s.training.entities.Order;
import r2s.training.services.CustomerService;
import r2s.training.services.OrderService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true;
        while (isExit) {
            System.out.println("Choose function:");
            System.out.println("1. Add a new customer");
            System.out.println("2. Show  all customers");
            System.out.println("3. Search customer's order by phone number");
            System.out.println("4. Remove customer by phone number");
            System.out.println("Press any key to exit!");
            System.out.print("You choose: ");
            String number = scanner.nextLine();
            System.out.println();
            switch (number){
                case "1": {
                    customerService.createCustomer();
                    break;
                }
                case "2": {
                    System.out.println("--List of customer--");
                    List<Customer> customers = customerService.findAll();
                    customerService.display(customers);
                    break;
                }
                case "3": {
                    System.out.println("--Search customer's order by phone number--");
                    String phone = scanner.nextLine();
                    List<Order> orders = orderService.search(phone);
                    orderService.display(orders);
                    break;
                }
                case "4": {
                    System.out.println("--Remove customer--");
                    String phone = scanner.nextLine();
                    if (customerService.remove(phone)) {
                        System.out.println("Remove successfully");
                    } else {
                        System.out.println("Remove failed");
                    }
                    break;
                }
                default: {
                    isExit = false;
                    break;
                }
            }
        }
    }
}