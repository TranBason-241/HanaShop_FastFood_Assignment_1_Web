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
import java.util.ArrayList;
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
@WebServlet(name = "SearchControllerAdmin", urlPatterns = {"/SearchControllerAdmin"})
public class SearchControllerAdmin extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(SearchControllerAdmin.class);
    private static final String LOGIN = "login.jsp";

    private static final String SEARCH = "adminPage.jsp";

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

                //set Cái MESSAGE_ORDER thành null để k hiện;
                ss.setAttribute("MESSAGE_ORDER", null);
                //set MESSAGE_CREATE thành null k để hiện
                ss.setAttribute("MESSAGE_CREATE", null);

                ProductDAO proDAO = new ProductDAO();
                ArrayList<String> listCategory = new ArrayList<String>();
                listCategory = proDAO.getCategory();
                ss.setAttribute("LIST_CATEGORY", listCategory);

                String listPrice[] = {"Price...", "2-5", "5-10", "10-20", "20-100"};
                ss.setAttribute("LIST_PRICE", listPrice);

                String price = request.getParameter("txtPrice");
                if (price == null || price.equals("Price...")) {
                    price = "";
                }
                ss.setAttribute("PRICE_SEARCH", price);
//            if(price!=null && !price.equals("Price...") ){
//                ss.setAttribute("PRICE_SEARH", price);
//            }else {
//                price = null;
//            }

                String category = request.getParameter("txtCategory");
                if (category == null || category.equals("Category")) {
                    category = "";
                }
                ss.setAttribute("CATEGORY_SEARCH", category);
//            if(category!=null && !category.equals("Category") ){
//                ss.setAttribute("CATEGORY_SEARCH", category);
//            }else {
//                category = null;
//            }

                String name = request.getParameter("txtSearch");
                if (name == null || name.equals("Search for products...") || name.trim().equals("")) {
                    name = "";
                }
                ss.setAttribute("NAME_SEARCH", name);
//            if(name!=null && !name.equals("Search for products...") && !name.equals("")){
//                ss.setAttribute("NAME_SEARCH", name);
//            }else {
//                name = null;
//            }

                String listStatus[] = {"false", "true"};
                ss.setAttribute("LIST_STATUS", listStatus);

                //-------------------Xu li phan trang-------------------
                int count = proDAO.getCountAdmin(name, price, category);
                ss.setAttribute("NUMBER_OF_PAGE", count);

                //xu li  hien thi pruduct
                String activePage;
                if (request.getParameter("activePage") == null) {
                    //Với vào chưa click
                    activePage = "1";

                    //Goi lay du lieu len
                } else {
                    //Click roi thi lay duoc trang dang click de xu li
                    activePage = Integer.parseInt(request.getParameter("activePage")) + "";
                    //goi lay du lieu len

                }
                ss.setAttribute("ACTIVE_PAGE", activePage);

                ArrayList<ProductDTO> listProduct = new ArrayList<>();
                listProduct = proDAO.getProductAdmin(name, price, category, activePage);

                if (listProduct != null) {
                    ss.setAttribute("LIST_PRODUCT", listProduct);
                }

                url = SEARCH;
            } else {
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
