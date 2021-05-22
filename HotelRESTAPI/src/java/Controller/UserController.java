package Controller;

import Modell.User;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("applicant/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
             //ADD NEW USER
            if(request.getParameter("task").equals("addNewUser")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("email").isEmpty() && !request.getParameter("password").isEmpty() && !request.getParameter("name").isEmpty() && !request.getParameter("phone").isEmpty() && !request.getParameter("status").isEmpty()) {
                    
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String name = request.getParameter("name");
                    String phone = request.getParameter("phone");
                    Integer status = Integer.parseInt(request.getParameter("status"));
                    
                    User user = new User(0, email, password, name, phone, status);
                    
                    String result = UserService.addNewUser(user);
                        returnValue.put("result", result);                            
                }
                else{
                    returnValue.put("result", "A mező(k) nincsenek megfelelően kitöltve!");
                }
                out.print(returnValue.toString());
            }
            //END OF ADD NEW USER
            
            //GET ALL (LOGICAL)ACTIVE USER
            if(request.getParameter("task").equals("getAllActiveUser")){
                JSONArray returnValue = new JSONArray();
                List<User> users = UserService.getAlllActiveUser();
                
                if(users.isEmpty()){
                    JSONObject obj = new JSONObject();
                    obj.put("Result", "Nincs aktív userek a rendszerben!");
                    returnValue.put(obj);
                    out.print(returnValue.toString());
                }
                else{
                    for(User u : users){
                        returnValue.put(u.toJson());
                    }
                    out.print(returnValue.toString());
                }
            }
            //END OF GET ALL ACTIVE USER
            
            //LOGICAL DELETE USER
            if(request.getParameter("task").equals("logicalDeleteUserById")){
                JSONObject returnValue = new JSONObject();
                
                if(!request.getParameter("id").isEmpty()){
                    
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    
                    //User user = User.getUserById(0);
                    User user = new User(id,"","","", "", 0);
                    
                    String result = UserService.logicalDeleteRoom(user);
                    returnValue.put("result", result);                            
                }
                else{
                    returnValue.put("result", "A mező(k) nincsenek megfelelően kitöltve!");
                }
                out.print(returnValue.toString());
            }
            //END OF LOGICAL DELETE USER
            
            //UPDATE USER
            if(request.getParameter("task").equals("updateUser")){
                JSONObject returnValue = new JSONObject();
                if(!request.getParameter("id").isEmpty() && !request.getParameter("email").isEmpty() && !request.getParameter("name").isEmpty() && !request.getParameter("phone").isEmpty() && !request.getParameter("status").isEmpty() ){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    String result;
                    User user = new User(id,"","","","",0);
                        
                    String email = request.getParameter("email");
                    user.setEmail(email);

                    String name = request.getParameter("name");
                    user.setName(name);

                    String phone = request.getParameter("phone");
                    user.setPhoneNumber(phone);

                    Integer status = Integer.parseInt(request.getParameter("status"));
                    user.setUserStatus(status);
                    
                    result = UserService.updateUser(user);
                        
                    returnValue.put("result", result);                            
                }
                else{
                    returnValue.put("result", "Az mező(k) nincsen megfelelően kitöltve!");
                }
                out.print(returnValue.toString());
            }
            //END OF UPDATE USER
            
            //CHANGE PASSWORD
            if(request.getParameter("task").equals("changeUserPassword")){
                JSONObject returnValue = new JSONObject();
                if(!request.getParameter("id").isEmpty() && !request.getParameter("oldPW").isEmpty() && !request.getParameter("newPW").isEmpty() && !request.getParameter("newPW2").isEmpty()){
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    String oldPW = request.getParameter("oldPW");
                    String newPW = request.getParameter("newPW");
                    String newPW2 = request.getParameter("newPW2");
                    
                    if(newPW.equals(newPW2)){ 
                        //Get user by id
                        //-->User user = User.getUserById(id); 
                        User user = new User(id,"","","","",0);

                        //Missing steps!!! -->
                        //Validate old pw: "if(SHA1(oldPW) == user.getPassword())"
                            //validation true:    
                            user.setPassword(newPW);
                            String result = UserService.changeUserPassword(user);

                            returnValue.put("result", result); 
                        //else
                            //returnValue.put("result","Helytelen régi jelszót írt be!");
                    }
                    else{
                        returnValue.put("result", "A két jelszó nem egyezzik!");
                    }                
                }
                else{
                    returnValue.put("result", "Az mező(k) nincsen megfelelően kitöltve!");
                }
                out.print(returnValue.toString());
            }
            //ENDOF CHANGEPASSWORD
            
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
