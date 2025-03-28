/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hai
 */
import java.io.*;

public class CustomerList {

    private CustomerNode head;

    public CustomerList() {
        head = null;
    }

    public void add(Customer customer) {
        CustomerNode newNode = new CustomerNode(customer);
        if (head == null) {
            head = newNode;
            return;
        }
        CustomerNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
    }

    public void display() {
        CustomerNode curr = head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    public void saveToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            CustomerNode curr = head;
            while (curr != null) {
                bw.write(curr.data.toString());
                bw.newLine();
                curr = curr.next;
            }
            System.out.println("Customer data saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing customer data to file: " + e.getMessage());
        }
    }

    public void loadData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 3) {
                    continue;
                }
                String ccode = parts[0].trim();
                String cusName = parts[1].trim();
                String phone = parts[2].trim();
                Customer customer = new Customer(ccode, cusName, phone);
                add(customer);
            }
            System.out.println("Customer data loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading customer data: " + e.getMessage());
        }
    }

    public Customer search(String ccode) {
        CustomerNode curr = head;
        while (curr != null) {
            if (curr.data.ccode.equals(ccode)) {
                return curr.data;
            }
            curr = curr.next;
        }
        return null;
    }

    public void delete(String ccode) {
        if (head == null) {
            return;
        }
        if (head.data.ccode.equals(ccode)) {
            head = head.next;
            System.out.println("Customer " + ccode + " deleted.");
            return;
        }
        CustomerNode curr = head;
        while (curr.next != null && !curr.next.data.ccode.equals(ccode)) {
            curr = curr.next;
        }
        if (curr.next != null) {
            curr.next = curr.next.next;
            System.out.println("Customer " + ccode + " deleted.");
        } else {
            System.out.println("Customer " + ccode + " not found.");
        }
    }
}
