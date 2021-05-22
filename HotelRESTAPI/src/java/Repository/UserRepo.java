package Repository;

import Modell.Database;
import Modell.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;


public class UserRepo {
    public static boolean addNewUser(User user){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addNewUser");
            
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("phoneIN", String.class, ParameterMode.IN);
           
            spq.setParameter("emailIN", user.getEmail());
            spq.setParameter("passwordIN", user.getPassword());
            spq.setParameter("nameIN", user.getName());
            spq.setParameter("phoneIN", user.getPhoneNumber());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static List<User> getAllActiveUser(){
        List<User> result = new ArrayList();
        try{
            EntityManager em = Database.getDbConn();
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllActiveUser");
            
            List<Object[]> users = spq.getResultList();
            for(Object[] user : users){
                int id = Integer.parseInt(user[0].toString());
                User u = em.find(User.class, id);
                result.add(u);
            }
        }
        catch(Exception ex){
        }
        finally{
            return result;
        }  
    }
    
    public static boolean logicalDeleteUser(User user){
        try{
            EntityManager em = Database.getDbConn();
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("logicalDeleteUserById");
        
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", user.getUserId());
            
            spq.execute();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateUser(User input){
        try{
            EntityManager em = Database.getDbConn();
            
            em.getTransaction().begin();
                User uj = em.find(User.class, input.getUserId());
                uj.setEmail(input.getEmail());
                uj.setName(input.getName());
                uj.setPhoneNumber(input.getPhoneNumber());
            em.getTransaction().commit();
            return true;
           
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean changeUserPassword(User user){
        try{
            EntityManager em = Database.getDbConn();
        
            //em.getTransaction().begin();
            
                StoredProcedureQuery spq = em.createStoredProcedureQuery("changeUserPassword");

                spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
                spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
                
                spq.setParameter("idIN", user.getUserId());
                spq.setParameter("passwordIN", user.getPassword());

                spq.execute();
            //em.getTransaction().commit();
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.toString());
            return false;
        }
        
    }
}
