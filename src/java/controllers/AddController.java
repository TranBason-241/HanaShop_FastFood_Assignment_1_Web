/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.ProductDAO;
import dtos.CartDTO;
import dtos.ProductDTO;
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
public class AddController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(AddController.class);
    private static final String ERROR = "invalid.jsp";
    private static final String SEARCH = "SearchController";
    private static final String LOGIN = "login.jsp";
    private static final String ADMIN = "admin.jsp";

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

        String url = ERROR;
        try {

            HttpSession ss = request.getSession();
            UserDTO user = (UserDTO) ss.getAttribute("USER");
            if (user == null) {
                url = LOGIN;
            } else if(user.getRoleID().equals("user")){

                CartDTO cart = (CartDTO) ss.getAttribute("CART");
                if (cart == null) {
                    cart = new CartDTO(user.getUserID(), null);
                }

                String productID = request.getParameter("productID");
                ProductDAO dao = new ProductDAO();
                ProductDTO product = dao.getOneProduct(productID);
                //Neu co san pham nay ton tai thi tien hanh add vao gio
                if (product != null) {
                    cart.add(product);
                }

                ss.setAttribute("CART", cart);
                url = SEARCH;
            } else if(user.getRoleID().equals("admin")){
                url = ADMIN;
            }

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
