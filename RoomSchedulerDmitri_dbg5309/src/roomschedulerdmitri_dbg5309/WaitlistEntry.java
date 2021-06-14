/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomschedulerdmitri_dbg5309;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class WaitlistEntry {
    
    private String faculty;
    private Date date;
    private int seats;
    private Timestamp timestamp;
    
   
    //Constructors
    public WaitlistEntry(){}
    
    public WaitlistEntry(String faculty, Date date, int seats, Timestamp timestamp) {
        
        this();
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;

    }

    //Setters
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
    
    public void setTimestamp(Timestamp timestamp){
        this.timestamp = timestamp;
    }
    
    //Getters
    public String getFaculty() {
        return faculty;
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
