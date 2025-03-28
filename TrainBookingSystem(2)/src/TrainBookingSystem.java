/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Hai
 */
import java.util.Scanner;

public class TrainBookingSystem {
    private static Scanner sc = new Scanner(System.in);
    private static TrainBST trainTree = new TrainBST();
    private static CustomerList customerList = new CustomerList();
    private static BookingList bookingList = new BookingList();
    
    public static void main(String[] args) {
        int choice;
        do {
            displayMainMenu();
            choice = Integer.parseInt(sc.nextLine());
            
            switch(choice) {
                case 11:
                case 1:
                    processTrainMenu();
                    break;
                case 21:
                case 2:
                    processCustomerMenu();
                    break;
                case 31:
                case 3:
                    processBookingMenu();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while(choice != 0);
    }
    
    private static void displayMainMenu() {
        System.out.println("\n--- Train Booking System Menu ---");
        System.out.println("Trains:");
        System.out.println("1 Load Train Data from file");
        System.out.println("2 Input & Insert Train data");
        System.out.println("3. In-order Traverse");
        System.out.println("4. Breadth-first Traverse");
        System.out.println("5. In-order Traverse to File");
        System.out.println("6. Search Train by tcode");
        System.out.println("7. Delete Train by tcode");
        System.out.println("8. Simply Balancing the BST");
        System.out.println("9. Count Number of Trains");
        System.out.println("\nCustomer List:");
        System.out.println("1. Load Customer Data from file");
        System.out.println("2. Input & Add Customer");
        System.out.println("3. Display Customer Data");
        System.out.println("4. Save Customer Data to file");
        System.out.println("5. Search Customer by ccode");
        System.out.println("6. Delete Customer by ccode");
        System.out.println("\nBooking List:");
        System.out.println("1. Input Booking Data");
        System.out.println("2. Display Booking Data");
        System.out.println("3. Sort Booking List");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void processTrainMenu() {
        System.out.println("\n--- Train Menu ---");
        System.out.println("1. Load data from file (train.txt)");
        System.out.println("2. Input & insert data");
        System.out.println("3. In-order traverse");
        System.out.println("4. Breadth-first traverse");
        System.out.println("5. In-order traverse to file (train_out.txt)");
        System.out.println("6. Search by tcode");
        System.out.println("7. Delete by tcode");
        System.out.println("8. Simply balancing");
        System.out.println("9. Count number of trains");
        System.out.print("Select option (1 to 9): ");
        String opt = sc.nextLine();
        
        switch(opt) {
            case "1":
                trainTree.loadData("train.txt");
                break;
            case "2":
                inputAndInsertTrain();
                break;
            case "3":
                System.out.println("In-order traverse:");
                trainTree.inorder();
                break;
            case "4":
                System.out.println("Breadth-first traverse:");
                trainTree.breadthFirst();
                break;
            case "5":
                trainTree.inorderToFile("train_out.txt");
                break;
            case "6":
                searchTrain();
                break;
            case "7":
                deleteTrain();
                break;
            case "8":
                trainTree.balance();
                break;
            case "9":
                System.out.println("Total number of trains: " + trainTree.count());
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    
    private static void inputAndInsertTrain() {
        System.out.print("Enter train code: ");
        String tcode = sc.nextLine();
        System.out.print("Enter train name: ");
        String trainName = sc.nextLine();
        System.out.print("Enter number of seats: ");
        int seat = Integer.parseInt(sc.nextLine());
        System.out.print("Enter number of booked seats: ");
        int booked = Integer.parseInt(sc.nextLine());
        System.out.print("Enter departure time: ");
        double departTime = Double.parseDouble(sc.nextLine());
        System.out.print("Enter departure place: ");
        String departPlace = sc.nextLine();
        Train train = new Train(tcode, trainName, seat, booked, departTime, departPlace);
        trainTree.insert(train);
    }
    
    private static void searchTrain() {
        System.out.print("Enter train code to search: ");
        String codeSearch = sc.nextLine();
        Train found = trainTree.search(codeSearch);
        if(found != null) {
            System.out.println("Train found: " + found);
        } else {
            System.out.println("Train not found.");
        }
    }
    
    private static void deleteTrain() {
        System.out.print("Enter train code to delete: ");
        String codeDelete = sc.nextLine();
        trainTree.delete(codeDelete);
    }
    
    private static void processCustomerMenu() {
        System.out.println("\n--- Customer Menu ---");
        System.out.println("1. Load data from file (customer.txt)");
        System.out.println("2. Input & add customer");
        System.out.println("3. Display customer data");
        System.out.println("4. Save customer data to file (customer_out.txt)");
        System.out.println("5. Search customer by ccode");
        System.out.println("6. Delete customer by ccode");
        System.out.print("Select option (1 to 6): ");
        String opt = sc.nextLine();
        
        switch(opt) {
            case "1":
                customerList.loadData("customer.txt");
                break;
            case "2":
                inputAndAddCustomer();
                break;
            case "3":
                System.out.println("Customer List:");
                customerList.display();
                break;
            case "4":
                customerList.saveToFile("customer_out.txt");
                break;
            case "5":
                searchCustomer();
                break;
            case "6":
                deleteCustomer();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    
    private static void inputAndAddCustomer() {
        System.out.print("Enter customer code: ");
        String ccode = sc.nextLine();
        System.out.print("Enter customer name: ");
        String cusName = sc.nextLine();
        System.out.print("Enter phone (digits only): ");
        String phone = sc.nextLine();
        Customer customer = new Customer(ccode, cusName, phone);
        customerList.add(customer);
    }
    
    private static void searchCustomer() {
        System.out.print("Enter customer code to search: ");
        String searchCcode = sc.nextLine();
        Customer found = customerList.search(searchCcode);
        if(found != null) {
            System.out.println("Customer found: " + found);
        } else {
            System.out.println("Customer not found.");
        }
    }
    
    private static void deleteCustomer() {
        System.out.print("Enter customer code to delete: ");
        String deleteCcode = sc.nextLine();
        customerList.delete(deleteCcode);
    }
    
    private static void processBookingMenu() {
        System.out.println("\n--- Booking Menu ---");
        System.out.println("1. Input booking data");
        System.out.println("2. Display booking data");
        System.out.println("3. Sort booking list (by train code and customer code)");
        System.out.print("Select option (1 to 3): ");
        String opt = sc.nextLine();
        
        switch(opt) {
            case "1":
                inputBooking();
                break;
            case "2":
                System.out.println("Booking List:");
                bookingList.display();
                break;
            case "3":
                bookingList.sort();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    
    private static void inputBooking() {
        System.out.print("Enter train code for booking: ");
        String tcode = sc.nextLine();
        System.out.print("Enter customer code for booking: ");
        String ccode = sc.nextLine();
        System.out.print("Enter number of seats to book: ");
        int seats = Integer.parseInt(sc.nextLine());
        Booking booking = new Booking(tcode, ccode, seats);
        bookingList.add(booking);
    }
}
