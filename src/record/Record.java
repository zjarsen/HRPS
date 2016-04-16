package record;

import orderinfo.Order;
import roominfo.Room;
import guestinfo.Guest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zjarsen on 8/4/16.
 */
public class Record implements Serializable{
    private Long ID;
    private Guest guest;
    private Room room;
    private ArrayList<Order> orders;
    private boolean isCheckedIn;
    private Long checkInDate;
    private Long checkOutDate;
    private double totalPrice;
    private boolean hasDiscount;
    private boolean valid;

    public Record() {
        this.ID= System.currentTimeMillis();
        orders = null;
        isCheckedIn = false;
        checkInDate = null;
        checkOutDate = null;
        totalPrice = 0.0;
        hasDiscount = false;
        valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    public void setIsCheckedIn(boolean isCheckedIn) {
        this.isCheckedIn = isCheckedIn;
    }

    public Long getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Long checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Long getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Long checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean hasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + ID + "\n" + "\t" +
                "Has discount: " + hasDiscount + "\n" + "\t" +
                "Is the guest checked in? : " + isCheckedIn + "\n" +
                guest.toString() + "\n" +
                room.toString();
    }
}


