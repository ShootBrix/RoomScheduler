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

public class ReservationQueries {

    private static Connection connection;
    private static PreparedStatement applyReservation;
    private static PreparedStatement getReservedFaculty;
    private static PreparedStatement getReservedEntries;
    private static PreparedStatement getReservedRooms;
    private static PreparedStatement getReservedSeats;
    private static PreparedStatement getTimestamp;
    private static PreparedStatement reservationExists;
    private static ResultSet resultSet;

    private ReservationEntry entry;

    public ReservationQueries() {
    }

    public ReservationQueries(ReservationEntry entry) {
        this.entry = entry;
    }

    public void applyReservation() throws SQLException {

        connection = DBConnection.getConnection();

        try {
            if (entry.getRoom() == null){
                throw new SQLException();
            }
            applyReservation = connection.prepareStatement("INSERT into reservations (facultyname,room,date,seats,timestamp) values (?,?,?,?,?)");
            applyReservation.setString(1, entry.getFaculty());
            applyReservation.setString(2, entry.getRoom());
            applyReservation.setDate(3, entry.getDate());
            applyReservation.setInt(4, entry.getSeats());
            applyReservation.setTimestamp(5, entry.getTimestamp());
            applyReservation.executeUpdate();
        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
            throw sqlException;
        }
    }
    static List<ReservationEntry> getReservationEntriesByRoom(String roomName) {
        List<ReservationEntry> entries = new ArrayList<ReservationEntry>();
        connection = DBConnection.getConnection();

        try {
            getReservedEntries = connection.prepareStatement("SELECT * from reservations WHERE room = ?");
            getReservedEntries.setString(1, roomName);
            resultSet = getReservedEntries.executeQuery();

            while (resultSet.next()) {
                ReservationEntry entry = new ReservationEntry();
                entry.setFaculty(resultSet.getString(1));
                entry.setRoom(resultSet.getString(2));
                entry.setDate(resultSet.getDate(3));
                entry.setSeats(resultSet.getInt(4));
                entry.setTimestamp(resultSet.getTimestamp(5));
                entries.add(entry);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return entries;
    }
    public static List<ReservationEntry> getReservationEntries(Date date) {

        List<ReservationEntry> entries = new ArrayList<ReservationEntry>();
        connection = DBConnection.getConnection();

        try {
            getReservedEntries = connection.prepareStatement("SELECT * from reservations WHERE date = ?");
            getReservedEntries.setDate(1, date);
            resultSet = getReservedEntries.executeQuery();

            while (resultSet.next()) {
                ReservationEntry entry = new ReservationEntry();
                entry.setFaculty(resultSet.getString(1));
                entry.setRoom(resultSet.getString(2));
                entry.setDate(resultSet.getDate(3));
                entry.setSeats(resultSet.getInt(4));
                entry.setTimestamp(resultSet.getTimestamp(5));
                entries.add(entry);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return entries;
    }

    public static List<ReservationEntry> getReservationEntriesByFaculty(String faculty) {

        List<ReservationEntry> entries = new ArrayList<ReservationEntry>();
        connection = DBConnection.getConnection();

        try {
            getReservedEntries = connection.prepareStatement("SELECT * from reservations WHERE facultyname = ?");
            getReservedEntries.setString(1, faculty);
            resultSet = getReservedEntries.executeQuery();

            while (resultSet.next()) {
                ReservationEntry entry = new ReservationEntry();
                entry.setFaculty(resultSet.getString(1));
                entry.setRoom(resultSet.getString(2));
                entry.setDate(resultSet.getDate(3));
                entry.setSeats(resultSet.getInt(4));
                entry.setTimestamp(resultSet.getTimestamp(5));
                entries.add(entry);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return entries;
    }

    public static String getReservedFaculty(Date date) {

        connection = DBConnection.getConnection();
        String faculty = null;

        try {
            getReservedFaculty = connection.prepareStatement("SELECT facultyname from reservations WHERE date = ?");
            getReservedFaculty.setDate(1, date);
            resultSet = getReservedFaculty.executeQuery();

            while (resultSet.next()) {
                faculty = resultSet.getString(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return faculty;
    }

    public static String getReservedRoom(Date date) {

        connection = DBConnection.getConnection();
        String room = null;

        try {
            getReservedRooms = connection.prepareStatement("SELECT room from reservations WHERE date = ?");
            getReservedRooms.setDate(1, date);
            resultSet = getReservedRooms.executeQuery();

            while (resultSet.next()) {
                room = resultSet.getString(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return room;
    }

    public static int getReservedSeats(Date date) {

        connection = DBConnection.getConnection();
        int seats = 0;

        try {
            getReservedSeats = connection.prepareStatement("SELECT seats from reservations WHERE date = ?");
            getReservedSeats.setDate(1, date);
            resultSet = getReservedSeats.executeQuery();

            while (resultSet.next()) {
                seats = resultSet.getInt(1);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return seats;
    }

    public static Timestamp getTimestamp(Date date) {

        connection = DBConnection.getConnection();
        Timestamp timestamp = null;

        try {
            getTimestamp = connection.prepareStatement("SELECT timestamp from reservations WHERE date = ?");
            getTimestamp.setDate(1, date);
            resultSet = getTimestamp.executeQuery();

            while (resultSet.next()) {
                timestamp = resultSet.getTimestamp(1);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return timestamp;
    }

    static public Boolean reservationExists(Date date, String room) throws SQLException {

        connection = DBConnection.getConnection();
        Boolean exist = false;

        try {
            reservationExists = connection.prepareStatement("SELECT date from reservations WHERE date = ? AND room = ?");
            reservationExists.setDate(1, date);
            reservationExists.setString(2, room);
            resultSet = reservationExists.executeQuery();

            if (resultSet.next()) {
                exist = true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        }

        return exist;
    }

    public static ReservationEntry getExistingReservationEntryByFacultyAndDate(String facultyName, Date date) throws SQLException {
        connection = DBConnection.getConnection();
        ReservationEntry entry = null;

        try {
            reservationExists = connection.prepareStatement("SELECT * from reservations WHERE date = ? AND facultyName = ?");
            reservationExists.setDate(1, date);
            reservationExists.setString(2, facultyName);
            resultSet = reservationExists.executeQuery();

            if (resultSet.next()) {
                entry = new ReservationEntry();
                entry.setFaculty(resultSet.getString(1));
                entry.setRoom(resultSet.getString(2));
                entry.setDate(resultSet.getDate(3));
                entry.setSeats(resultSet.getInt(4));
                entry.setTimestamp(resultSet.getTimestamp(5));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        }

        return entry;
    }

    public static void cancelReservationEntriesByFacultyAndDate(String facultyName, Date date) throws SQLException {
        connection = DBConnection.getConnection();

        try {
            reservationExists = connection.prepareStatement("DELETE from reservations WHERE date = ? AND facultyName = ?");
            reservationExists.setDate(1, date);
            reservationExists.setString(2, facultyName);
            reservationExists.executeUpdate();
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        }
    }
    
    static void deleteReservation(ReservationEntry reservation) throws SQLException {
        connection = DBConnection.getConnection();

        try {
            reservationExists = connection.prepareStatement("DELETE from reservations WHERE date = ? AND facultyName = ? AND room = ?");
            reservationExists.setDate(1, reservation.getDate());
            reservationExists.setString(2, reservation.getFaculty());
            reservationExists.setString(3, reservation.getRoom());
            reservationExists.executeUpdate();
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        }
    }
}
