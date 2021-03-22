/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.UserDTO;
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
public class UserDAO {
private static final Logger LOGGER = Logger.getLogger(UserDAO.class);
    public UserDTO checkUser(String userID, String password) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserDTO user = null;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "USE Assignment1_TranBaSon\n"
                        + "SELECT *\n"
                        + "FROM dbo.tblUsers a\n"
                        + "WHERE a.userID = '" + userID + "' and a.password = '" + password + "'";
                pst = cn.prepareCall(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("userID");
                    String pw = rs.getString("password");
                    String roleID = rs.getString("roleID");
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    user = new UserDTO(id, pw, fullName, email, roleID);
                }

            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
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
        return user;
    }

    public int createUser(String userID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
       
        int check = -1;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
               String sql = "	INSERT dbo.tblUsers\n"
                        + "	        ( userID ,\n"
                        + "	          roleID ,\n"
                        + "	          password ,\n"
                        + "	          fullName ,\n"
                        + "	          email\n"
                        + "	        )\n"
                        + "	VALUES  ( '"+userID+"' , -- userID - varchar(20)\n"
                        + "	          'user' , -- roleID - varchar(20)\n"
                        + "	          ' ' , -- password - varchar(20)\n"
                        + "	          '"+userID+"' , -- fullName - varchar(20)\n"
                        + "	          '"+userID+"'  -- email - varchar(30)\n"
                        + "	        )";
               pst=cn.prepareCall(sql);
//                System.out.println(sql);
                check =  pst.executeUpdate();
              
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally{
            if(cn!=null) cn.close();
            if(pst!=null) pst.close();
            
        }
        return check;
    }


}
