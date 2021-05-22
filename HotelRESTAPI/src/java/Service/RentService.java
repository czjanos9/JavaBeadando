package Service;

import Modell.Rent;
import Repository.RentRepo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class RentService {
    public static String addNewRent(Rent rent){
        
        //Hiányzó validcáió:
        //Létezik e az user/szoba és státuszuk aktív
        //A kezdődátum a jövöben van-e
        //A vége dátum késöbb van mint a kezdő dátum
        //foglalt e az időpontra a szoba ( szerepel e már rá az intervallumban foglalás)
        
        Date start =rent.getRentDateStart();
        Date end = rent.getRentDateEnd();

        if(start.compareTo(end) < 0 ){
            if(RentRepo.addNewRent(rent)){
                return "A foglalás rögzítése sikeres";
            }
            else{
                return "sikertelen rögzítés";
            }
        }
        else{
            return "A kezdő időpontnak korábban kell elnnie minta vége időpontnak!";
        }
        
        
    }
    
    public static List<Rent> getAllActiveRent(){
        return RentRepo.getAllActiveRent();
    }
    
    public static String logicalDeleteRent(Rent rent){
        if(RentRepo.logicalDeleteRent(rent)){
                return "A foglalás törlése sikeresen megtörtént!";
            }
            else{
                return "A törlés nem sikerült";
            }
    }
    
    public static String updateRent(Rent rent){
        //validálás úgyanúy mint new Rentnél!
        Date start =rent.getRentDateStart();
        Date end = rent.getRentDateEnd();
        if(start.compareTo(end) < 0 ){
            if(RentRepo.updateRent(rent)){
                return "Sikeres update!";
            }
            else{
                return "Sikertelen update!";
            }
        }
        else{
            return "A kezdő időpontnak korábban kell elnnie minta vége időpontnak!";
        }
    }
    
    public static List<Rent> getAllRentByDate(Rent rent){
            return RentRepo.getAllRentByDate(rent);
    }
    
    public static List<Rent> getAllRentByUserId(Rent rent){
        return RentRepo.getAllRentByUserId(rent);
    }
    
     public static List<Rent> getAllRentByRoomId(Rent rent){
        return RentRepo.getAllRentByRoomId(rent);
    }
}
