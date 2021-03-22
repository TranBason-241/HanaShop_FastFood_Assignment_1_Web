/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
public class MainController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(MainController.class);
    private final static String ERROR = "invalid.jsp";
    private final static String LOGIN = "LoginController";
    private final static String LOGOUT = "LogoutController";
    private final static String SEARCH = "SearchController";
    private final static String SEARCH_ADMIN = "SearchControllerAdmin";
    private final static String ADD = "AddController";
    private final static String CART = "CartController";
    private final static String UP_DOWN = "UpDownController";
    private final static String DELETE_ITEM_BY_US = "DeleteControllerByUser";
    private final static String DELETE_ITEM_BY_ADMIN = "DeleteControllerByAdmin";
    private final static String UPDATE_ITEM_BY_ADMIN = "UpdateProductAdmin";
    private final static String UPDATE_ITEM_BY_ADMIN_ACTION = "UpdateProducAdminAction";
    private final static String CREATE_PRODUCT_PAGE = "createPage.jsp";

    private final static String CHECK_OUT = "CheckOutController";
    private final static String VIEW_HISTORY = "HistoryController";
    private final static String BACK_TO_LOGIN = "login.jsp";

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
            String action = request.getParameter("btnAction");
            if (action != null) {
                if (action.equals("login")) {
                    url = LOGIN;
                } else if (action.equals("logout")) {
                    url = LOGOUT;
                } else if (action.equals("search")) {
                    url = SEARCH;
                } else if (action.equals("add")) {
                    url = ADD;
                } else if (action.equals("viewCart")) {
                    url = CART;
                } else if (action.equals("updateQuantity")) {
                    url = UP_DOWN;
                } else if (action.equals("DeleteProductByUser")) {
                    url = DELETE_ITEM_BY_US;
                } else if (action.equals("checkOut")) {
                    url = CHECK_OUT;
                } else if (action.equals("searchAdmin")) {   //Admin page
                    url = SEARCH_ADMIN;
                } else if (action.equals("adminDelete")) {
                    url = DELETE_ITEM_BY_ADMIN;
                } else if (action.equals("adminUpdate")) {  //Render trang Update 1 sp
                    url = UPDATE_ITEM_BY_ADMIN;
                } else if (action.equals("adminUpdateAction")) {//Xu li update
                    url = UPDATE_ITEM_BY_ADMIN_ACTION;
                } else if (action.equals("createProduct")) {
                    url = CREATE_PRODUCT_PAGE;
                } else if (action.equals("viewHistory")) {
                    url = VIEW_HISTORY;
                } else if (action.equals("backToLogin")) {
                    url = BACK_TO_LOGIN;
                }
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
