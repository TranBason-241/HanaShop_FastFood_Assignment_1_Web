/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CartDTO;
import dtos.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import utils.MyConnection;

/**
 *
 * @author ADMIN
 */

public class CartDAO {
private static final Logger LOGGER = Logger.getLogger(CartDAO.class);

    public int checkOut(CartDTO cart, float totalMoney) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = -1;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                //call back
                cn.setAutoCommit(false);
                String userName = cart.getCustumer();
                String sqlOrder = "USE Assignment1_TranBaSon INSERT into tblOrders(userID, orderDate, totalMoney) values('" + userName + "', CURRENT_TIMESTAMP, " + totalMoney + ")";
                pst = cn.prepareStatement(sqlOrder);
                result = pst.executeUpdate();
                if (result != -1) {
                    //Get orderID
                    String sqlDetail = "USE Assignment1_TranBaSon Select max(orderID) as orderID"
                            + " from tblOrders";
                    pst = cn.prepareStatement(sqlDetail);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        int orderID = rs.getInt("orderID");
                        for (ProductDTO product : cart.getCart().values()) {
                            String productID = product.getProductID();
//                            String productName = product.getProductName();
                            Float price = product.getPrice();
                            int quantity = product.getQuantity();
                            String sqlDetail2 = " If(select quantity from tblProduct where productID = '" + productID + "') - " + quantity + " >= 0\n"
                                    + "Begin"
                                    + "  USE Assignment1_TranBaSon INSERT into tblOrderDetail(productID, price, quantity, orderID) "
                                    + "     values('" + productID + "', " + price + ", " + quantity + ", '" + orderID + "')"
                                    + "   update tblProduct\n"
                                    + "         set quantity = quantity - "+quantity+""
                                    + "         where productID = '"+productID+"'"
                                    + " End";
//                            System.out.println(sqlDetail2);
                            pst = cn.prepareStatement(sqlDetail2);
                            result = pst.executeUpdate();
                            //
                        }
                    }
                } else {
                    result = -1;
                }
                cn.commit();
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
            cn.rollback();
            
            
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return result;
    }

}
