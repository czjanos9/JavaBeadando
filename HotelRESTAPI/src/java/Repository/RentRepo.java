package Repository;

import Modell.Database;
import Modell.Rent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;


public class RentRepo {
    public static boolean addNewRent(Rent rent){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewRent");
            
            spq.registerStoredProcedureParameter("startIN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("endIN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("useridIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("roomidIN", Integer.class, ParameterMode.IN);
           
            spq.setParameter("startIN", rent.getRentDateStart());
            spq.setParameter("endIN", rent.getRentDateEnd());
            spq.setParameter("useridIN", rent.getUserId());
            spq.setParameter("roomidIN", rent.getRoomId());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static List<Rent> getAllActiveRent(){
        List<Rent> result = new ArrayList();
        try{
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActiveRoom");
            
            List<Object[]> rents = spq.getResultList();
            for(Object[] rent : rents){
                int id = Integer.parseInt(rent[0].toString());
                Rent r = em.find(Rent.class, id);
                result.add(r);
            }
        }
        catch(Exception ex){
        }
        finally{
            return result;
        }  
    }
    
    public static boolean logicalDeleteRent(Rent rent){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteRentById");
        
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", rent.getRentId());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateRent(Rent input){
        try{
            EntityManager em = Database.getDbConn();
            
            em.getTransaction().begin();
                Rent uj = em.find(Rent.class, input.getRentId());
                uj.setRentDateStart(input.getRentDateStart());
                uj.setRentDateEnd(input.getRentDateEnd());
                uj.setUserId(input.getUserId());
                uj.setRoomId(input.getRoomId());
                uj.setRentStatus(input.getRentStatus());
            em.getTransaction().commit();
            return true;
           
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static List<Rent> getAllRentByDate(Rent input){
        List<Rent> result = new ArrayList();
        try{
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllRentByDate");
            
            spq.registerStoredProcedureParameter("timeFromIN", Date.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("timeToIN", Date.class, ParameterMode.IN);
            
            spq.setParameter("timeFromIN", input.getRentDateStart());
            spq.setParameter("timeToIN", input.getRentDateEnd());
            
            //spq.execute();
            
            List<Object[]> rents = spq.getResultList();
            for(Object[] rent : rents){
                int id = Integer.parseInt(rent[0].toString());
                Rent r = em.find(Rent.class, id);
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
    
    public static List<Rent> getAllRentByUserId(Rent input){
        List<Rent> result = new ArrayList();
        try{
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllRentByUserId");
            
            spq.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN);
           
            spq.setParameter("idIN", input.getUserId());

            //spq.execute();
            
            List<Object[]> rents = spq.getResultList();
            for(Object[] rent : rents){
                int id = Integer.parseInt(rent[0].toString());
                Rent r = em.find(Rent.class, id);
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
    
    public static List<Rent> getAllRentByRoomId(Rent input){
        List<Rent> result = new ArrayList();
        try{
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllRentByRoomId");
            
            spq.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN);
           
            spq.setParameter("idIN", input.getRoomId());

            //spq.execute();
            
            List<Object[]> rents = spq.getResultList();
            for(Object[] rent : rents){
                int id = Integer.parseInt(rent[0].toString());
                Rent r = em.find(Rent.class, id);
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
}
