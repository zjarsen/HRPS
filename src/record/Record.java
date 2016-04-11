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
    private long ID;
    private Guest guest;
    private Room room;
    private ArrayList<Order> orders;
    private boolean isCheckedIn;
    private Date checkInDate;
    private Date checkOutDate;
    private float totalPrice;
    private boolean hasDiscount;
}
