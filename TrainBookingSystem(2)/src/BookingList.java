/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hai
 */
import java.util.*;

public class BookingList {
    private BookingNode head;
    
    public BookingList() {
        head = null;
    }
    
    public void add(Booking booking) {
        BookingNode newNode = new BookingNode(booking);
        if(head == null) {
            head = newNode;
            return;
        }
        BookingNode curr = head;
        while(curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
    }
    
    public void display() {
        BookingNode curr = head;
        while(curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }
    
    public void sort() {
        List<Booking> list = new ArrayList<>();
        BookingNode curr = head;
        while(curr != null) {
            list.add(curr.data);
            curr = curr.next;
        }
        Collections.sort(list, (b1, b2) -> {
            int cmp = b1.tcode.compareTo(b2.tcode);
            return cmp == 0 ? b1.ccode.compareTo(b2.ccode) : cmp;
        });
        head = null;
        for(Booking b : list) {
            add(b);
        }
        System.out.println("Booking list has been sorted by train code and customer code.");
    }
}
