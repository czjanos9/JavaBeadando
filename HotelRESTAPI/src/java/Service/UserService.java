package Service;

import Modell.User;
import Repository.UserRepo;
import java.util.List;



public class UserService {
    public static String addNewUser(User user){
        if(user.getName().length() > 0 && user.getName().length() <= 30 && user.getPhoneNumber().length() > 0 && user.getPhoneNumber().length() <= 15 && user.getEmail().length() > 0 && user.getEmail().length() <= 100 && user.getPassword().length() > 0 && user.getPassword().length() <= 100){
            if(UserRepo.addNewUser(user) ){
                return "Sikeresen hozzáadtad egy új felhasználót!";
            }
            else{
                return "Az adatok helyesek, de a rögzités nem sikerült!";
            }
        }
        else{
            return "A felhasználónév 1-30 karakter, a telefonszám 1-15 karakter és az Email és Jelszó 1-100 karakter  hosszunak kell lennie!";
        }
    }
    
    public static List<User> getAlllActiveUser(){
        return UserRepo.getAllActiveUser();
    }
    
    public static String logicalDeleteRoom(User user){
        if(UserRepo.logicalDeleteUser(user)){
                return "A user törlése sikeresen megtörtént!";
            }
            else{
                return "A törlés nem sikerült";
            }
    }
    
    public static String updateUser(User user){
        if(user.getName().length() > 0 && user.getName().length() <= 30 && user.getPhoneNumber().length() > 0 && user.getPhoneNumber().length() <= 15 && user.getEmail().length() > 0 && user.getEmail().length() <= 100 && user.getPassword().length() > 0 && user.getPassword().length() <= 100){
            if(UserRepo.updateUser(user)){
                return "Sikeres update!";
            }
            else{
                return "Sikertelen update!";
            }
        }
        else{
            return "A felhasználónév 1-30 karakter, a telefonszám 1-15 karakter és az Email és Jelszó 1-100 karakter  hosszunak kell lennie!";
        }
    }
    
    public static String changeUserPassword(User user){
        if(UserRepo.changeUserPassword(user)){
            return "Sikeres jelszó megváltoztatás!";
        }
        else{
            return "Sikertelen jelszó megváltoztatás!";
        }
    }
    
   
}
