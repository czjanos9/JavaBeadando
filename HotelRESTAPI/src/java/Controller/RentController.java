package Controller;

import Modell.Rent;
import Service.RentService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class RentController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //ADD NEW RENT
            if(request.getParameter("task").equals("addNewRent")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("start").isEmpty() && !request.getParameter("end").isEmpty() && !request.getParameter("userid").isEmpty() && !request.getParameter("roomid").isEmpty()){
                    
                   SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
                   Date start = dateformat.parse(request.getParameter("start"));
                   Date end = dateformat.parse(request.getParameter("end"));
                    
                    Integer userid = Integer.parseInt(request.getParameter("userid"));
                    Integer roomid = Integer.parseInt(request.getParameter("roomid"));
                    
                    Rent rent = new Rent(0, start, end, userid, roomid, 1);
                    
                    String result = RentService.addNewRent(rent);
                        returnValue.put("result", result);                            
                }
                else{
                    returnValue.put("result", "A mező(k) nincsenek megfelelően kitöltve!");
                }
                out.print(returnValue.toString());
            }
            //END OF ADD NEW USER
            
            //GET ALL (LOGICAL)ACTIVE USER
            if(request.getParameter("task").equals("getAllActiveRent")){
                JSONArray returnValue = new JSONArray();
                List<Rent> rents = RentService.getAllActiveRent();
                
                if(rents.isEmpty()){
                    JSONObject obj = new JSONObject();
                    obj.put("Result", "Nincs aktív foglalások a rendszerben!");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                }
                else{
                    for(Rent r : rents){
                        returnValue.put(r.toJson());
                    }
                    out.print(returnValue.toString());
                }
            }
            //END OF GET ALL ACTIVE USER
            
            //LOGICAL DELETE USER
            if(request.getParameter("task").equals("logicalDeleteRentById")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("id").isEmpty()){
                    
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    
                    //Rent rent = Rent.getRentById(id); Commented for test results
                    
                    SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
                    Date semmi = dateformat.parse("2000-01-01 00:00:00");
                    System.out.print("asd");
                    Rent rent = new Rent(id, semmi, semmi, 0, 0, 1);
                    
                    String result = RentService.logicalDeleteRent(rent);
                    returnValue.put("result", result);                            
                }
                else{
                    returnValue.put("result", "A mező(k) nincsenek megfelelően kitöltve!");
                }
                out.print(returnValue.toString());
            }
            //END OF LOGICAL DELETE USER
            
            //UPDATE USER
            if(request.getParameter("task").equals("updateRent")){
                JSONObject returnValue = new JSONObject();
                if(!request.getParameter("id").isEmpty() && !request.getParameter("start").isEmpty() && !request.getParameter("end").isEmpty() && !request.getParameter("userid").isEmpty() && !request.getParameter("roomid").isEmpty() ){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
                    
                    Date semmi = dateformat.parse("2000-01-01 00:00:00");
                    
                    String result;
                    Rent rent = new Rent(id,semmi,semmi,0,0,1);
                    
                    Date start = dateformat.parse(request.getParameter("start"));
                    rent.setRentDateStart(start);
                    Date end = dateformat.parse(request.getParameter("end"));
                    rent.setRentDateEnd(end);

                    Integer userid = Integer.parseInt(request.getParameter("userid"));
                    rent.setUserId(userid);
                    
                    Integer roomid = Integer.parseInt(request.getParameter("roomid"));
                    rent.setRoomId(roomid);
                    
                    result = RentService.updateRent(rent);
                        
                    returnValue.put("result", result);                            
                }
                else{
                    returnValue.put("result", "Az mező(k) nincsen megfelelően kitöltve!");
                }
                out.print(returnValue.toString());
            }
            //END OF UPDATE USER
            
            //GET ALL RENT BYDATE
            if(request.getParameter("task").equals("getAllRentByDate")){
                JSONArray returnValue = new JSONArray(); 
                if(!request.getParameter("start").isEmpty() && !request.getParameter("end").isEmpty()){
                    
                    SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
                    Date start = dateformat.parse(request.getParameter("start"));
                    Date end = dateformat.parse(request.getParameter("end"));
                    
                    Rent rent = new Rent(0, start, end, 0, 0, 0);
                    
                    List<Rent> rents = RentService.getAllRentByDate(rent);
                    
                    if(rents.isEmpty()){
                        JSONObject obj = new JSONObject();
                        obj.put("result", "Nincs az intervallumban foglalás");
                        returnValue.put(obj);
                        out.print(returnValue.toString());
                    }
                    else{
                        out.print(returnValue.toString());
                    }
                } 
            }
            //ENDOF GET ALL RENT BY DATE 
            
            //GET ALL RENT BY USER
            if(request.getParameter("task").equals("getAllRentByUserId")){
                JSONArray returnValue = new JSONArray(); 
                if(!request.getParameter("id").isEmpty()){
                    
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    
                    SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
                    Date semmi = dateformat.parse("2000-01-01 00:00:00");
                    
                    Rent rent = new Rent(0, semmi, semmi, id, 0, 0);
                    
                    List<Rent> rents = RentService.getAllRentByUserId(rent);
                    
                    if(rents.isEmpty()){
                        JSONObject obj = new JSONObject();
                        obj.put("result", "Nincs ilyen User által foglalás");
                        returnValue.put(obj);
                        out.print(returnValue.toString());
                    }
                    else{
                        out.print(returnValue.toString());
                    }
                } 
            }
            //ENDOF GET ALL RENT BY USER
            
             //GET ALL RENT BY ROOM
            if(request.getParameter("task").equals("getAllRentByRoomId")){
                JSONArray returnValue = new JSONArray(); 
                if(!request.getParameter("id").isEmpty()){
                    
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    
                    SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
                    Date semmi = dateformat.parse("2000-01-01 00:00:00");
                    
                    Rent rent = new Rent(0, semmi, semmi, 0, id, 0);
                    
                    List<Rent> rents = RentService.getAllRentByRoomId(rent);
                    
                    if(rents.isEmpty()){
                        JSONObject obj = new JSONObject();
                        obj.put("result", "Nincs ilyen szobát tartalmazó foglalás");
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
            System.out.print("JSON EXCEPTION VAN!");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RentController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RentController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

