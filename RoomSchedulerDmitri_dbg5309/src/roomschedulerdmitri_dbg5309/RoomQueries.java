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
import java.util.List;

/**
 *
 * @author diman
 */
public class RoomQueries {
    
    private static Connection connection;
    private static PreparedStatement RoomQueries;
    private static PreparedStatement getAllRooms;
    private static PreparedStatement getSeats;
    private static PreparedStatement getBestFitRoom;
    private static ResultSet resultSet;

    public RoomQueries(){}
    
    public static void addRoom(RoomEntry entry) {
        
        connection = DBConnection.getConnection();
        
        try{
            RoomQueries = connection.prepareStatement("INSERT into rooms (name,seats) values (?,?)"); 
            RoomQueries.setString(1, entry.getRoomName());
            RoomQueries.setInt(2, entry.getSeats());
            RoomQueries.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<RoomEntry> getAllPossibleRooms() {
        
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> entries = new ArrayList<RoomEntry>();
        
        try{
            getAllRooms = connection.prepareStatement("SELECT * from rooms ORDER BY seats asc");
            resultSet = getAllRooms.executeQuery();
            
            while(resultSet.next()){
                RoomEntry entry = new RoomEntry();
                entry.setRoomName(resultSet.getString(1));
                entry.setSeats(resultSet.getInt(2));
                entries.add(entry);
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return entries;
    }
    
    public static List<String> getAllRooms() {
        connection = DBConnection.getConnection();
        ArrayList<String> strRooms = new ArrayList<String>();
        
        try{
            getAllRooms = connection.prepareStatement("SELECT name from rooms");
            resultSet = getAllRooms.executeQuery();
            
            while(resultSet.next()){
                strRooms.add(resultSet.getString(1));
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return strRooms;
    }

    public static int getSeats(String room) {
        
        connection = DBConnection.getConnection();
        int seats = 0;
        
        try{
            getSeats = connection.prepareStatement("SELECT seats from rooms WHERE name = ?");
            getSeats.setString(1, room);
            resultSet = getSeats.executeQuery();
            while(resultSet.next()){
                seats = resultSet.getInt(1);
            }
           
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return seats;
    }

    public static String getBestFitRoom(int seats) {
        
        connection = DBConnection.getConnection();
        String room = null;
        
        try{
            getBestFitRoom = connection.prepareStatement("SELECT name from rooms WHERE seats >= ? ORDER BY seats asc");
            getBestFitRoom.setInt(1, seats);
            resultSet = getBestFitRoom.executeQuery();
            
            if(resultSet.next()){
                room = resultSet.getString(1);   
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return room;
        
    }
    
    public static void deleteRoom(String roomName) throws SQLException  {
        
        connection = DBConnection.getConnection();
        
        try{
            RoomQueries = connection.prepareStatement("DELETE from rooms WHERE name = ? "); 
            RoomQueries.setString(1, roomName);
            RoomQueries.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
            throw sqlException;
        }
    }
    
}
