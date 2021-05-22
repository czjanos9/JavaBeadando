/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modell;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jani
 */
public class RunByCode {
     public static void RunCode() {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("hotelRESTAPIPU");
       System.out.print("Before createEntityManager!");
       EntityManager em = emf.createEntityManager();
       System.out.print("After createEntityManager!");
        
       // Room.addNewRoom(em, "RunByCode", 110, "Java2 kodból adtuk hozzá", 0);
         
         
       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("hotelRESTAPIPU");
       // EntityManager em = emf.createEntityManager();
        
       /*Room.addNewRoom(em, "JPA", 10,"Java kodból adtuk hozzá", 1);
       List<Room> activeRooms = Room.getAllActiveRoom(em);
       for (Room m : activeRooms){
           System.out.println(m.toString());
       }
    
        Room logDel = Room.findById(em, 3);
        //logDel.logicalDeleteTr(em);

        System.out.println("A törllés után: ");

        List<Room> activeRooms2 = Room.getAllActiveRoom(em);
           for (Room m : activeRooms2){
               System.out.println(m.toString());
           }
       
        Room roomToUpdate = Room.findById(em, 6);
        roomToUpdate.updateRoom(em, "A módosítás", 3, "Lorep Ipsum Update", 1);
           
        System.out.println("A módosítás után: ");

        List<Room> activeRoom3 = Room.getAllActiveRoom(em);
           for (Room m : activeRoom3){
               System.out.println(m.toString());
           }*/
       
       //Room.addNewRoom(em, "JPA", 10,"Java kodból adtuk hozzá", 1);
    }
}
