/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomschedulerdmitri_dbg5309;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diman
 */
public class WaitlistQueries {

    private WaitlistEntry entry;
    
    private static Connection connection;
    private static PreparedStatement WaitlistQueries;
    private static PreparedStatement getWaitingDate;
    private static PreparedStatement getWaitingSteats;
    private static PreparedStatement getWaitingTimestamp;
    private static ResultSet resultSet;
    
    public WaitlistQueries() {}
    
    public WaitlistQueries(WaitlistEntry entry) {
        this.entry = entry;
    }

    public void applyWaitlist() throws SQLException {
        
        connection = DBConnection.getConnection();
        
        try{
            WaitlistQueries = connection.prepareStatement("INSERT into waitlist (faculty,date,seats,timestamp) values (?,?,?,?)"); 
            WaitlistQueries.setString(1, entry.getFaculty());
            WaitlistQueries.setDate(2, entry.getDate());
            WaitlistQueries.setInt(3, entry.getSeats());
            WaitlistQueries.setTimestamp(4, entry.getTimestamp());
            WaitlistQueries.executeUpdate();
        }
        catch(SQLException sqlException){
//            sqlException.printStackTrace();
            throw sqlException;
        }
    }
    
    public Date getWaitingDate(String faculty){
        
        connection = DBConnection.getConnection();
        java.sql.Date date = null;
        
        try{
            getWaitingDate = connection.prepareStatement("SELECT date from waitlist WHERE faculty = ?");
            getWaitingDate.setString(1, faculty);
            resultSet = getWaitingDate.executeQuery();
            
            while(resultSet.next()){
                date = resultSet.getDate(1);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return date;
    }
    
    public int getWaitingSteats(String faculty){
        
        connection = DBConnection.getConnection();
        int seats = 0;
        
        try{
            getWaitingSteats = connection.prepareStatement("SELECT seats from waitlist WHERE faculty = ?");
            getWaitingSteats.setString(1, faculty);
            resultSet = getWaitingSteats.executeQuery();
            
            while(resultSet.next()){
                seats = resultSet.getInt(1);
            }
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return seats;
    }
    
    public Timestamp getWaitingTimestamp(String faculty){
        
        connection = DBConnection.getConnection();
        Timestamp timestamp = null;
        
        try{
            getWaitingTimestamp = connection.prepareStatement("SELECT timestamp from waitlist WHERE faculty = ? ");
            getWaitingTimestamp.setString(1, faculty);
            resultSet = getWaitingTimestamp.executeQuery();
            
            while(resultSet.next()){
                timestamp = resultSet.getTimestamp(1);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return timestamp;
    }
    
    public static List<WaitlistEntry> getWaitingListEntries(){
        
        List<WaitlistEntry> entries = new ArrayList<WaitlistEntry>();
        connection = DBConnection.getConnection();
                
        try{
            getWaitingTimestamp = connection.prepareStatement("SELECT * from waitlist");
            resultSet = getWaitingTimestamp.executeQuery();
            
            while(resultSet.next()){
                WaitlistEntry entry = new WaitlistEntry();
                entry.setFaculty(resultSet.getString(1));
                entry.setDate(resultSet.getDate(2));
                entry.setSeats(resultSet.getInt(3));
                entry.setTimestamp(resultSet.getTimestamp(4));
                entries.add(entry);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return entries;
    }
    
    public static List<WaitlistEntry> getWaitingListEntriesWithPriority() {
        List<WaitlistEntry> entries = new ArrayList<WaitlistEntry>();
        connection = DBConnection.getConnection();
                
        try{
            getWaitingTimestamp = connection.prepareStatement("SELECT * from waitlist ORDER BY timestamp asc");
            resultSet = getWaitingTimestamp.executeQuery();
            
            while(resultSet.next()){
                WaitlistEntry entry = new WaitlistEntry();
                entry.setFaculty(resultSet.getString(1));
                entry.setDate(resultSet.getDate(2));
                entry.setSeats(resultSet.getInt(3));
                entry.setTimestamp(resultSet.getTimestamp(4));
                entries.add(entry);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return entries;
    }
      
   
    public static List<WaitlistEntry> getWaitingListEntriesByFaculty(String faculty){
        
        List<WaitlistEntry> entries = new ArrayList<WaitlistEntry>();
        connection = DBConnection.getConnection();
                
        try{
            getWaitingTimestamp = connection.prepareStatement("SELECT * from waitlist WHERE faculty = ? ");
            getWaitingTimestamp.setString(1, faculty);
            resultSet = getWaitingTimestamp.executeQuery();
            
            while(resultSet.next()){
                WaitlistEntry entry = new WaitlistEntry();
                entry.setFaculty(resultSet.getString(1));
                entry.setDate(resultSet.getDate(2));
                entry.setSeats(resultSet.getInt(3));
                entry.setTimestamp(resultSet.getTimestamp(4));
                entries.add(entry);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return entries;
    }    
    
    public static WaitlistEntry getWaitingEntryByDateWithPriority(Date date) {
        WaitlistEntry entry = null;
        connection = DBConnection.getConnection();
                
        try{
            getWaitingTimestamp = connection.prepareStatement("SELECT * from waitlist WHERE date = ? ORDER BY timestamp asc");
            getWaitingTimestamp.setDate(1, date);
            resultSet = getWaitingTimestamp.executeQuery();
            
            if(resultSet.next()){
                entry = new WaitlistEntry();
                entry.setFaculty(resultSet.getString(1));
                entry.setDate(resultSet.getDate(2));
                entry.setSeats(resultSet.getInt(3));
                entry.setTimestamp(resultSet.getTimestamp(4));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return entry;
    }
    
    
    public static void deleteEntryFromWaitingList(WaitlistEntry entry) throws SQLException {
        connection = DBConnection.getConnection();
        
        try{
            getWaitingTimestamp = connection.prepareStatement("DELETE from waitlist WHERE date = ? AND faculty = ?");
            getWaitingTimestamp.setDate(1, entry.getDate());
            getWaitingTimestamp.setString(2, entry.getFaculty());
            getWaitingTimestamp.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            throw sqlException;
        }
    }
    
    
    public static WaitlistEntry getWaitingEntryByDate(String facultyName, Date date) {
        WaitlistEntry entry = null;
        connection = DBConnection.getConnection();
                
        try{
            getWaitingTimestamp = connection.prepareStatement("SELECT * from waitlist WHERE date = ? AND faculty = ?");
            getWaitingTimestamp.setDate(1, date);
            getWaitingTimestamp.setString(2, facultyName);
            resultSet = getWaitingTimestamp.executeQuery();
            
            if(resultSet.next()){
                entry = new WaitlistEntry();
                entry.setFaculty(resultSet.getString(1));
                entry.setDate(resultSet.getDate(2));
                entry.setSeats(resultSet.getInt(3));
                entry.setTimestamp(resultSet.getTimestamp(4));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return entry;
    }
}
