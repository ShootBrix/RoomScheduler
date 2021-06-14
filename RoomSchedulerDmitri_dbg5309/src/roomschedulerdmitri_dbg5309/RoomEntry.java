/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomschedulerdmitri_dbg5309;

public class RoomEntry {
    
    private String roomName;
    private int seats;
    
    //Constructors
    public RoomEntry(){}
    
    public RoomEntry(String roomName, int seats) {
        this.roomName = roomName;
        this.seats = seats;
    }
    
    //Getters
    public String getRoomName() {
        return roomName;
    }

    public int getSeats() {
        return seats;
    }
    
    //Setters
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

}
