/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UserDAO;
import dtos.ErrorDTO;
import dtos.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
public class LoginController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final String LOGIN = "login.jsp";
    private static final String SEARCH_USER = "SearchController";
    private static final String SEARCH_ADMIN = "SearchControllerAdmin";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN;
        try {
            String userID = request.getParameter("txtUserName");
            String password = request.getParameter("txtPassword");

            HttpSession ss = request.getSession();
            ErrorDTO error = new ErrorDTO("", "", "", "");
            ss.setAttribute("ERROR", error);

            //Check xem da login chua, neu roi k cho login nua
//            if (ss.getAttribute("USER") != null) {
//                //da login roi
//              
//                url = SEARCH;
//            } else {
            if (userID == null && password == null) {
                url = LOGIN;
            } else {
               
                UserDAO dao = new UserDAO();
                UserDTO user = dao.checkUser(userID, password);
                if (user != null) {
                    ss.setAttribute("USER", user);

                    url = SEARCH_USER;
                   

                   
                    if (user.getRoleID().equals("admin")) {
                        url = SEARCH_ADMIN;
                    }
                    
                    
                     error.setUserNameError("");
                    ss.setAttribute("ERROR", error);

                } else {
                    error.setUserNameError("sai userID or pass");
                    ss.setAttribute("ERROR", error);
                    ss.setAttribute("userIDERROR", userID);
                }
            }

//            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
