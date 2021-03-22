package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.paypal.base.rest.PayPalRESTException;

import daos.CartDAO;
import dtos.CartDTO;
import dtos.ProductDTO;

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
 * @author truon
 */
@WebServlet("/authorize_payment")
public class AuthorizePaymentServlet extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(AddController.class);
    public static final String CART = "cart.jsp";

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
       
//		String subtotal = request.getParameter("subtotal");
//		String shipping = request.getParameter("shipping");
//		String tax = request.getParameter("tax");
        String total = request.getParameter("total");

//		OrderDetail orderDetail = new OrderDetail(product, subtotal, shipping, tax, total);
       
        try {
            HttpSession ss = request.getSession();
//            UserDTO user = (UserDTO) ss.getAttribute("USER");
            CartDTO cart = (CartDTO) ss.getAttribute("CART");
            float total2 = 0;
            for (ProductDTO product2 : cart.getCart().values()) {
                total2 += product2.getPrice() * product2.getQuantity();
            }
            CartDAO dao = new CartDAO();
            int rs = dao.checkOut(cart, total2);
            if (rs != -1) {

                try {
                    PaymentServices paymentServices = new PaymentServices();
                    String approvalLink = paymentServices.authorizePayment(total);
                    response.sendRedirect(approvalLink);

                    //Xoa session, giam quantity
                    ss.setAttribute("CART", null);
                    ss.setAttribute("MESSAGE_ORDER", "You have successfully ordered");

                } catch (PayPalRESTException ex) {
                 
                    ss.setAttribute("MESSAGE_ORDER", "Error at Paypal server");
                    request.getRequestDispatcher(CART).forward(request, response);
                     LOGGER.error("error: ", ex);
                    //sai
                    
                }

//                ss.setAttribute("MESSAGE_ORDER", "You have successfully ordered");
//                 ss.setAttribute("CART", null);
            } else {
                ss.setAttribute("MESSAGE_ORDER", "Paypal Error, product is sold");
                request.getRequestDispatcher(CART).forward(request, response);
            }

        } catch (Exception e) {
  
            request.getRequestDispatcher(CART).forward(request, response);
             LOGGER.error("error: ", e);
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
