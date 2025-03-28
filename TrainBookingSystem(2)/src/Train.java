/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hai
 */
public class Train {
    String tcode;
    String trainName;
    int seat;
    int booked;
    double departTime;
    String departPlace;
    
    public Train(String tcode, String trainName, int seat, int booked, double departTime, String departPlace) {
        this.tcode = tcode;
        this.trainName = trainName;
        this.seat = seat;
        this.booked = booked;
        this.departTime = departTime;
        this.departPlace = departPlace;
    }
    
    @Override
    public String toString() {
        return tcode + " " + trainName + " " + seat + " " + booked + " " + departTime + " " + departPlace;
    }
}
