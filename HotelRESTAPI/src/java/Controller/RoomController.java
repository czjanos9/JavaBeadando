/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modell.Room;
import Service.RoomService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Jani
 */
public class RoomController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("applicant/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            //ADD NEW ROOM
            if(request.getParameter("task").equals("addNewRoom")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("name").isEmpty() && !request.getParameter("bed").isEmpty() && !request.getParameter("description").isEmpty() && !request.getParameter("status").isEmpty()){
                    
                    String name = request.getParameter("name");
                    Integer bed = Integer.parseInt(request.getParameter("bed"));
                    String description = request.getParameter("description");
                    Integer status = Integer.parseInt(request.getParameter("status"));
                    
                    Room room = new Room(0, name, bed, description, status);
                    
                    String result = RoomService.addNewRoom(room);
                        returnValue.put("result", result);                            
                }
                else{
                    returnValue.put("result", "A mező(k) nincsenek megfelelően kitöltve!");
                }
                out.print(returnValue.toString());
            }
            //END OF ADD NEW ROOM
            
            //GET ALL (LOGICAL)ACTIVE ROOM
            if(request.getParameter("task").equals("getAllActiveRoom")){
                JSONArray returnValue = new JSONArray();
                List<Room> rooms = RoomService.getAlllActiveRoom();
                
                if(rooms.isEmpty()){
                    JSONObject obj = new JSONObject();
                    obj.put("Result", "Nincs aktív szóba a rendszerben!");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                }
                else{
                    for(Room r : rooms){
                        returnValue.put(r.toJson());
                        
                    }
                    out.print(returnValue.toString());
                }
            }
            //END OF GET ALL ACTIVE ROOM
            
            //LOGICAL DELETE ROOM
            if(request.getParameter("task").equals("logicalDeleteRoomById")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("id").isEmpty()){
                    
                    Integer id = Integer.parseInt(request.getParameter("id"));
                  
                    Room room = new Room(id, "", 0, "", 0);
                    
                    String result = RoomService.logicalDeleteRoom(room);
                        returnValue.put("result", result);                            
                }
                else{
                    returnValue.put("result", "A mező(k) nincsenek megfelelően kitöltve!");
                }
                out.print(returnValue.toString());
            }
            //END OF LOGICAL DELETE ROOM
            
            //UPDATE ROOM
            if(request.getParameter("task").equals("updateRoom")){
                JSONObject returnValue = new JSONObject();
                if(!request.getParameter("id").isEmpty() && !request.getParameter("name").isEmpty() && !request.getParameter("bed").isEmpty() && !request.getParameter("description").isEmpty() && !request.getParameter("status").isEmpty() ){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    String result;
                    Room room = new Room(id,"",0,"",0);
                        
                    String name = request.getParameter("name");
                    room.setRoomName(name);

                    Integer bed = Integer.parseInt(request.getParameter("bed"));
                    room.setRoomBed(bed);

                    String description = request.getParameter("description");
                    room.setRoomDescription(description);

                    Integer status = Integer.parseInt(request.getParameter("status"));
                    room.setRoomStatus(status);
                    
                    result = RoomService.updateRoom(room);
                    
                         
                        returnValue.put("result", result);                            
                }
                else{
                    returnValue.put("result", "Az mező(k) nincsen megfelelően kitöltve!");
                }
                out.print(returnValue.toString());
            }
            //END OF UPDATE ROOM
            
            //GET ALL RENT BY ROOM
            if(request.getParameter("task").equals("getAllRoomByBed")){
                JSONArray returnValue = new JSONArray(); 
                if(!request.getParameter("bed").isEmpty()){
                    
                    Integer bed = Integer.parseInt(request.getParameter("bed"));
                    
                    Room room = new Room(0, "", bed, "", 0);
                    
                    List<Room> rents = RoomService.getAllRoomByBed(room);
                    
                    if(rents.isEmpty()){
                        JSONObject obj = new JSONObject();
                        obj.put("result", "Nincs ennyi ággyal rendelkező szoba");
                        returnValue.put(obj);
                        out.print(returnValue.toString());
                    }
                    else{
                        out.print(returnValue.toString());
                    }
                } 
            }
            //ENDOF GET ALL RENT BY ROOM
            
        }
        catch(Exception ex){
            System.out.print("JSON Exception van!");

        }  
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
