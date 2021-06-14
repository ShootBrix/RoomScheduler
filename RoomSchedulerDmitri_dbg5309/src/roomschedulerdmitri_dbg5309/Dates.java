/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomschedulerdmitri_dbg5309;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dates {
    
    private ArrayList<Date> date = new ArrayList<Date>();
    
    private static Connection connection;
    private static PreparedStatement addDate;
    private static PreparedStatement getAllDates;
    private static ResultSet resultSet;
    
    
    public static void addDate(Date date){
        
        connection = DBConnection.getConnection();
        
        try{
            addDate = connection.prepareStatement("INSERT into date (date) values (?)");
            addDate.setDate(1, date);
            addDate.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    
    
    public static ArrayList<String> getAllDates(){
        
        connection = DBConnection.getConnection();
        ArrayList<String> dates = new ArrayList<String>();
        
        try{
            getAllDates = connection.prepareStatement("SELECT date from date order by date");
            resultSet = getAllDates.executeQuery();
            
            while(resultSet.next()){
                dates.add(resultSet.getDate(1).toString());
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return dates;
        
    }
    
    public static ArrayList<Date> getAllDatesAsDate(){
        
        connection = DBConnection.getConnection();
        ArrayList<Date> date = new ArrayList<Date>();
        
        try{
            getAllDates = connection.prepareStatement("SELECT date from date order by date");
            resultSet = getAllDates.executeQuery();
            
            while(resultSet.next()){
                date.add(resultSet.getDate(1));
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return date;
    }
}
