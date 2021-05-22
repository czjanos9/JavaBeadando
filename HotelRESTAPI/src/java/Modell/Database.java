package Modell;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class Database {
    public static EntityManager getDbConn(){
        System.out.println("DBconn START");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hotelRESTAPIPU");
        System.out.println("DBconn EMF DONE");
        EntityManager em = emf.createEntityManager();
        System.out.println("DBconn EM DONE");
        return em;
       //return null;
    }
}
