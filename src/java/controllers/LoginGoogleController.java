/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UserDAO;
import dtos.UserDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import utils.GooglePojo;
import utils.GoogleUtils;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(AddController.class);
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

        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);

            request.setAttribute("id", googlePojo.getId());
            request.setAttribute("email", googlePojo.getEmail());
            request.setAttribute("link", googlePojo.getLink());
            request.setAttribute("nameF", googlePojo.getFamily_name());
            request.setAttribute("nameG", googlePojo.getGiven_name());
            HttpSession ss = request.getSession();
//            String id = googlePojo.getId();
            String email = googlePojo.getEmail();

            //Check co chua
            //neu chua thi add
            UserDAO dao = new UserDAO();
            UserDTO user = null;
            //check xem user email co chua
            try {
                user = dao.checkUser(email, "");
            } catch (Exception e) {
                 LOGGER.error("error: ", e);
            }
            //chua co thi add
            try {
                if (user == null) {
                    dao.createUser(email);
                }
            } catch (Exception e) {
                 LOGGER.error("error: ", e);
            }
            if (user != null) {
                ss.setAttribute("USER", user);
            }

            RequestDispatcher dis = request.getRequestDispatcher("SearchController");
            dis.forward(request, response);
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
