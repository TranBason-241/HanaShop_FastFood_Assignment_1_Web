/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.ProductDAO;
import dtos.ProductDTO;
import dtos.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateProducAdminAction", urlPatterns = {"/UpdateProducAdminAction"})
public class UpdateProducAdminAction extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(AddController.class);
    public static final String LOGIN = "login.jsp";
    public static final String UPDATE_PAGE = "updatePage.jsp";
    public static final String ADMIN_PAGE = "SearchControllerAdmin";

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
            HttpSession ss = request.getSession();
            UserDTO user = (UserDTO) ss.getAttribute("USER");
            if (user.getRoleID().equals("admin")) {
                String id = request.getParameter("txtIDUpdate");
                String name = request.getParameter("txtNameUpdate");
                String category = request.getParameter("txtCategoryUpdate");
                String description = request.getParameter("txtDescriptionUpdate");
                int quantity = Integer.parseInt(request.getParameter("txtQuantityUpdate"));
                float price = Float.parseFloat(request.getParameter("txtPriceUpdate"));
                boolean status = Boolean.parseBoolean(request.getParameter("txtStatusUpdate"));
                String error = null;
                boolean flag = false;
                if (name.trim().isEmpty()) {
                    error += "  Product name can not empty   ";
                    flag = true;
                }
                if (description.trim().isEmpty()) {
                    error += " Desciption can not empty";
                    flag = true;
                }
                ss.setAttribute("MESSAGE_UPDATE", error);
                if (flag) {
                    url = UPDATE_PAGE;
                } else {
                    ProductDAO dao = new ProductDAO();

                    ProductDTO product = new ProductDTO(id, name, description, null, price, quantity, null, status, category);
                    int rs = dao.updateProduct(product);
                    if (rs != -1) {

                        url = ADMIN_PAGE;

                    }
                }
            }else {
                url = LOGIN;
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
