package Repository;

import Modell.Database;
import Modell.Room;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;



public class RoomRepo {
    public static boolean addNewRoom(Room room){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewRoom");
            
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("bedIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("descIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("statusIN", Integer.class, ParameterMode.IN);
           
            spq.setParameter("nameIN", room.getRoomName());
            spq.setParameter("bedIN", room.getRoomBed());
            spq.setParameter("descIN", room.getRoomDescription());
            spq.setParameter("statusIN", room.getRoomStatus());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static List<Room> getAllActiveRoom(){
        List<Room> result = new ArrayList();
        try{
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActiveRoom");
            
            List<Object[]> rooms = spq.getResultList();
            for(Object[] room : rooms){
                int id = Integer.parseInt(room[0].toString());
                Room r = em.find(Room.class, id);
                result.add(r);
            }
        }
        catch(Exception ex){
            System.out.print(ex);
        }
        finally{
            return result;
        }  
    }
    
    public static boolean logicalDeleteRoom(Room room){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteMovieById");
        
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", room.getRoomId());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateRoom(Room input){
        try{
            EntityManager em = Database.getDbConn();
            
            em.getTransaction().begin();
                Room uj = em.find(Room.class, input.getRoomId());
                uj.setRoomName(input.getRoomName());
                uj.setRoomBed(input.getRoomBed());
                uj.setRoomDescription(input.getRoomDescription());
                uj.setRoomStatus(input.getRoomStatus());
            em.getTransaction().commit();
            return true;
           
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
   
}
