/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.ProductDAO;
import dtos.ProductDTO;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateProductAdmin", urlPatterns = {"/CreateProductAdmin"})
public class CreateProductAdmin extends HttpServlet {
private static final Logger LOGGER = Logger.getLogger(CreateProductAdmin.class);
    public static final String ERROR = "invalid.jsp";
    public static final String CREATE_PAGE = "createPage.jsp";

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

            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {
            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                items = upload.parseRequest(request);
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String filename = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            filename = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            String realPath = getServletContext().getRealPath("/") + "images\\" + filename;
//                            System.out.println("realPath: " + realPath);
                            File saveFile = new File(realPath);
                            item.write(saveFile);
                        } catch (Exception e) {
                             LOGGER.error("error: ", e);
                        }
                    }
                }

                String id = (String) params.get("txtIDCreate");
                String name = (String) params.get("txtNameCreate");
                String category = (String) params.get("txtCategoryCreate");
                String description = (String) params.get("txtDescriptionCreate");
                Float price = Float.parseFloat((String) params.get("txtPriceCreate"));
                int quantity = Integer.parseInt((String) params.get("txtQuantityCreate"));

                HttpSession ss = request.getSession();
                request.setAttribute("ID_CREATE", id);
                request.setAttribute("NAME_CREATE", name);
                request.setAttribute("CATEGORY_CREATE", category);
                request.setAttribute("DESCRIPTION_CREATE", description);
                request.setAttribute("PRICE_CREATE", price);
                request.setAttribute("QUANITY_CREATE", quantity);
                String message = "";
                boolean flag = false;
                //check name 
                if (name.trim().isEmpty()) {
                    flag = true;
                    message += "name can not empty \n";
                }
                //check Description
                if (description.trim().isEmpty()) {
                    flag = true;
                    message += "description can not empty \n";
                }
                //Check ID;
                ProductDAO dao = new ProductDAO();
                ProductDTO productCheck = dao.getOneProduct(id);
                if (productCheck != null) {
                    flag = true;
                    message += "ID already exists";
                }

                if (flag) {
                    ss.setAttribute("MESSAGE_CREATE", message);

                } else {
                    ProductDTO product = new ProductDTO(id, name, description, "images/"+filename, price, quantity, null, true, category);
                    int rs = dao.createProduct(product);
                    if (rs != -1) {
                        message = "Create successfully";
                        ss.setAttribute("MESSAGE_CREATE", message);
                    }
                }

            }

            url = CREATE_PAGE;
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
