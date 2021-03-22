/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.ProductDAO;
import dtos.CartDTO;
import dtos.ProductDTO;
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
public class UpDownController extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(AddController.class);
    public static final String ERROR = "invalid.jsp";
    public static final String VIEWCART = "cart.jsp";

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
            String operator = request.getParameter("txtOpetator");
            String productID = request.getParameter("productUpdateID");
            HttpSession ss = request.getSession();
            ProductDAO dao = new ProductDAO();
            CartDTO cart = (CartDTO) ss.getAttribute("CART");

            //Lấy sản phẩm trong kho để lấy quantiry check
            ProductDTO product = dao.getOneProduct(productID);
            int productStoreQuantity = product.getQuantity();

            //Lay quantity sản phẩm muốn giảm số lượng  trong cart
            int productCartQuantiy = cart.getCart().get(productID).getQuantity();
            
            String errorAtCart = (String)ss.getAttribute("MESSAGE_CART");
            if(errorAtCart==null){
                ss.setAttribute("MESSAGE_CART", null);
            }
            
            if (productCartQuantiy == productStoreQuantity & operator.equals("+")) {
                //Lưu trong ss để hiển thị combobox
                String error = "Product " + product.getProductName()+ " has "+product.getQuantity() + " products ";
               ss.setAttribute("MESSAGE_CART", error);
              
            } else {

                if (cart != null) {
                    cart.update(productID, operator);
                }
                ss.setAttribute("CART", cart);
                
                //Check lại những thằng nào đã hết max quantity và xóa khỏi list ERRORMAX
               ss.setAttribute("MESSAGE_CART", null);
                
            }
            url = VIEWCART;

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
