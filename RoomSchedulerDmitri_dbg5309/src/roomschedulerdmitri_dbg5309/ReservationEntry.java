/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomschedulerdmitri_dbg5309;

//import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
//import java.util.List;

public class ReservationEntry {
    
    private String faculty;
    private String room;
    private Date date;
    private int seats;
    private Timestamp timestamp;
    
    //Constructors
    public ReservationEntry(){}
    
    public ReservationEntry(String faculty,String room, Date date, int seats, Timestamp timestamp) {
        
        this();
        this.faculty = faculty;
        this.date = date;
        this.room = room;
        this.seats = seats;
        this.timestamp = timestamp;
        
    }

    //Setters
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    //Getters
    public String getFaculty() {
        return faculty;
    }

    public String getRoom() {
        return room;
    }

    public Date getDate() {
        return date;
    }

    public int getSeats() {
        return seats;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

}
