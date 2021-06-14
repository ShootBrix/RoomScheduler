/*
Project name: CMPSC 221 Final Project - Room Scheduler
Author: Dmitri Gordienko
Date and status: 12/15/20 COMPLETE

Notes:
-When updating any of the Tables and the result needs to be clear
it takes a few seconds to show a clear table. I don't know why this
particular case takes longer to load.(Moving one of the colmuns updates it faster for some reason)
*/
package roomschedulerdmitri_dbg5309;

public class RoomSchedulerDmitri_dbg5309 {

    public static void main(String[] args) {
        
        DBConnection.getConnection();
        RoomSchedulerJFrame.main(args);        
        
    }
    
}
