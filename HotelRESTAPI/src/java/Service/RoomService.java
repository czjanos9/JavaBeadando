package Service;

import Modell.Room;
import Repository.RoomRepo;
import java.util.List;



public class RoomService {
    public static String addNewRoom(Room room){
        if(room.getRoomName().length() > 0 && room.getRoomName().length() <= 100 && room.getRoomBed() > 0){
            if(RoomRepo.addNewRoom(room) ){
                return "Sikeresen hozzáadtad egy új szobát!";
            }
            else{
                return "Az adatok helyesek, de a rögzités nem sikerült!";
            }
        }
        else{
            return "A szoba neve 1-100 karakter hosszunak kell lennie és tartalmazania kell legalább 1 ágyat!";
        }
    }
    
    public static List<Room> getAlllActiveRoom(){
        return RoomRepo.getAllActiveRoom();
    }
    
    public static String logicalDeleteRoom(Room room){
        if(RoomRepo.logicalDeleteRoom(room)){
                return "A szoba törlése sikeresen megtörtént!";
            }
            else{
                return "A törlés nem sikerült";
            }
    }
    
    public static String updateRoom(Room room){
        if(RoomRepo.updateRoom(room)){
            return "Sikeres update!";
        }
        else{
            return "Sikertelen update!";
        }
    }
    
    public static List<Room> getAllRoomByBed(Room room){
        return RoomRepo.getAllRoomByBed(room);
    }
}
