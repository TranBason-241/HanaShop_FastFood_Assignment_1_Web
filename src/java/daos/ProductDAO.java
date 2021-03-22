/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.OrderDTO;
import dtos.OrderDetailDTO;
import dtos.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {
private static final Logger LOGGER = Logger.getLogger(ProductDAO.class);
    public ArrayList<String> getCategory() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<String> listCategory = new ArrayList<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "USE Assignment1_TranBaSon\n"
                        + "SELECT categoryName\n"
                        + "FROM dbo.tblCategory ";
                pst = cn.prepareCall(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String category = rs.getString("categoryName");
                    listCategory.add(category);
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
        return listCategory;
    }

    public ArrayList<ProductDTO> getProduct(String search, String price, String category, String page) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
        //tách string lấy giá tiền từ đâu đến đâu
        String from = "";
        String to = "";
        if (!price.equals("")) {
            String[] arrayNumber = price.split("-");
            from = arrayNumber[0];
            to = arrayNumber[1];
        }
        // Tu trang acive tinh ra index sau do tim sp can render
        int index = Integer.parseInt(page);
        index = (index - 1) * 20;
        page = index + "";

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {

                search = (search == null || search.equals("")) ? "" : " p.productName LIKE '%" + search + "%' ";
                // cho nay thieu mot dieu kien cho search price category la ""
                //khien cho sql sai, nhung ma ket qua k anh huong
                price = (price == null || price.equals("")) ? "" : "p.price BETWEEN '" + from + "' AND '" + to + "'";
                category = (category == null || category.equals("")) ? "" : "c.categoryName LIKE '%" + category + "%' ";

                if (!search.equals("") && !category.equals("")) {
                    category = " OR " + category;

                }
                if ((!category.equals("") || !search.equals("")) && !price.equals("")) {
                    price = " OR " + price;
                }

                String sql = "";
                if (category.equals("") && search.equals("") && price.equals("")) {
                    sql = "USE Assignment1_TranBaSon\n"
                            + "SELECT productID,productName,description,price,image,quantity,createDate,status,categoryName From (SELECT p.productID,p.productName,p.description,p.price,p.image,p.quantity,p.createDate,p.status,c.categoryName  \n"
                            + "FROM  dbo.tblCategory c INNER JOIN dbo.tblProduct p \n"
                            + "ON p.category = c.categoryID where p.status = 'true' AND p.quantity > 0) tmp "
                            + "  ORDER BY tmp.createDate, tmp.productID "
                            + " OFFSET " + page + " ROWS\n"
                            + " FETCH FIRST 20 ROWS only";
                } else {
                    sql = "USE Assignment1_TranBaSon\n"
                            + "SELECT productID,productName,description,price,image,quantity,createDate,status,categoryName From (SELECT p.productID,p.productName,p.description,p.price,p.image,p.quantity,p.createDate,p.status,c.categoryName  \n"
                            + "FROM  dbo.tblCategory c INNER JOIN dbo.tblProduct p \n"
                            + "ON p.category = c.categoryID where  ( " + search + "   " + category + "  " + price + ")  AND   p.status = 'true' AND p.quantity > 0) tmp "
                            + "  ORDER BY tmp.createDate, tmp.productID "
                            + " OFFSET " + page + " ROWS\n"
                            + " FETCH FIRST 20 ROWS only";
                }

//                System.out.println(sql);
//                System.out.println("from " + from + "to " + to);
                pst = cn.prepareCall(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");

                    String productName = rs.getString("productName");

                    String description = rs.getString("description");

                    float productPrice = rs.getFloat("price");

                    String img = rs.getString("image");

                    int quantity = rs.getInt("quantity");

                    Date date = rs.getDate("createDate");

                    boolean status = rs.getBoolean("status");

                    String productCategory = rs.getString("categoryName");

                    ProductDTO pro = new ProductDTO(productID, productName, description, img, productPrice, quantity, date, status, productCategory);
                    list.add(pro);
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
        return list;
    }

    public ArrayList<ProductDTO> getProductAdmin(String search, String price, String category, String page) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
        //tách string lấy giá tiền từ đâu đến đâu
        String from = "";
        String to = "";
        if (!price.equals("")) {
            String[] arrayNumber = price.split("-");
            from = arrayNumber[0];
            to = arrayNumber[1];
        }
        // Tu trang acive tinh ra index sau do tim sp can render
        int index = Integer.parseInt(page);
        index = (index - 1) * 20;
        page = index + "";

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {

                search = (search == null || search.equals("")) ? "" : " p.productName LIKE '%" + search + "%' ";
                // cho nay thieu mot dieu kien cho search price category la ""
                //khien cho sql sai, nhung ma ket qua k anh huong
                price = (price == null || price.equals("")) ? "" : "p.price BETWEEN '" + from + "' AND '" + to + "'";
                category = (category == null || category.equals("")) ? "" : "c.categoryName LIKE '%" + category + "%' ";

                if (!search.equals("") && !category.equals("")) {
                    category = " OR " + category;

                }
                if ((!category.equals("") || !search.equals("")) && !price.equals("")) {
                    price = " OR " + price;
                }

                String sql = "";
                if (category.equals("") && search.equals("") && price.equals("")) {
                    sql = "USE Assignment1_TranBaSon\n"
                            + "SELECT productID,productName,description,price,image,quantity,createDate,status,categoryName From (SELECT p.productID,p.productName,p.description,p.price,p.image,p.quantity,p.createDate,p.status,c.categoryName  \n"
                            + "FROM  dbo.tblCategory c INNER JOIN dbo.tblProduct p \n"
                            + "ON p.category = c.categoryID ) tmp "
                            + "  ORDER BY tmp.createDate, tmp.productID "
                            + " OFFSET " + page + " ROWS\n"
                            + " FETCH FIRST 20 ROWS only";
                } else {
                    sql = "USE Assignment1_TranBaSon\n"
                            + "SELECT productID,productName,description,price,image,quantity,createDate,status,categoryName From (SELECT p.productID,p.productName,p.description,p.price,p.image,p.quantity,p.createDate,p.status,c.categoryName  \n"
                            + "FROM  dbo.tblCategory c INNER JOIN dbo.tblProduct p \n"
                            + "ON p.category = c.categoryID where  ( " + search + "   " + category + "  " + price + ")) tmp "
                            + "  ORDER BY tmp.createDate, tmp.productID "
                            + " OFFSET " + page + " ROWS\n"
                            + " FETCH FIRST 20 ROWS only";
                }

//                System.out.println(sql);
//                System.out.println("from " + from + "to " + to);
                pst = cn.prepareCall(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");

                    String productName = rs.getString("productName");

                    String description = rs.getString("description");

                    float productPrice = rs.getFloat("price");

                    String img = rs.getString("image");

                    int quantity = rs.getInt("quantity");

                    Date date = rs.getDate("createDate");

                    boolean status = rs.getBoolean("status");

                    String productCategory = rs.getString("categoryName");

                    ProductDTO pro = new ProductDTO(productID, productName, description, img, productPrice, quantity, date, status, productCategory);
                    list.add(pro);
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
        return list;
    }

    public int getCountAdmin(String search, String price, String category) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        //tách string lấy giá tiền từ đâu đến đâu
        String from = "";
        String to = "";
        if (price != null && !price.isEmpty()) {
            String[] arrayNumber = price.split("-");
            from = arrayNumber[0];
            to = arrayNumber[1];
        }

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                search = (search == null || search.equals("")) ? "" : " p.productName LIKE '%" + search + "%' ";
                // cho nay thieu mot dieu kien cho search price category la ""
                //khien cho sql sai, nhung ma ket qua k anh huong
                price = (price == null || price.equals("")) ? "" : "p.price BETWEEN '" + from + "' AND '" + to + "'";
                category = (category == null || category.equals("")) ? "" : "c.categoryName LIKE '%" + category + "%' ";
                if (!search.equals("") && !category.equals("")) {
                    category = " OR " + category;
                }
                if ((!category.equals("") || !search.equals("")) && !price.equals("")) {
                    price = " OR " + price;
                }
                String sql = "";
                if (category.equals("") && search.equals("") && price.equals("")) {
                    //ca 3 input deu rong
                    sql = "SELECT COUNT(*) countt FROM \n"
                            + "(SELECT p.productID,p.productName,p.description,p.price,p.image,p.quantity,p.createDate,p.status,c.categoryName  \n"
                            + "FROM  dbo.tblCategory c INNER JOIN dbo.tblProduct p \n"
                            + "ON p.category = c.categoryID  ) tmp";
                } else {
                    sql = "SELECT COUNT(*) countt FROM \n"
                            + "(SELECT p.productID,p.productName,p.description,p.price,p.image,p.quantity,p.createDate,p.status,c.categoryName  \n"
                            + "FROM  dbo.tblCategory c INNER JOIN dbo.tblProduct p \n"
                            + "ON p.category = c.categoryID where  ( " + search + "   " + category + "  " + price + ") ) tmp ";
                }

//                System.out.println("-----cua count--------");
//                System.out.println(sql);
                pst = cn.prepareCall(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("countt");
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {

            if (rs != null) {
                rs.close();
            };
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }

//        System.out.println("Count: " + count);
        if (count % 20 == 0) {
            //chia hết 20 vừa đủ k qua trang mới
            count = count / 20;
        } else {
            count /= 20;
            count++;
        }

        return count;
    }

    public int getCount(String search, String price, String category) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        //tách string lấy giá tiền từ đâu đến đâu
        String from = "";
        String to = "";
        if (price != null && !price.isEmpty()) {
            String[] arrayNumber = price.split("-");
            from = arrayNumber[0];
            to = arrayNumber[1];
        }

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                search = (search == null || search.equals("")) ? "" : " p.productName LIKE '%" + search + "%' ";
                // cho nay thieu mot dieu kien cho search price category la ""
                //khien cho sql sai, nhung ma ket qua k anh huong
                price = (price == null || price.equals("")) ? "" : "p.price BETWEEN '" + from + "' AND '" + to + "'";
                category = (category == null || category.equals("")) ? "" : "c.categoryName LIKE '%" + category + "%' ";
                if (!search.equals("") && !category.equals("")) {
                    category = " OR " + category;
                }
                if ((!category.equals("") || !search.equals("")) && !price.equals("")) {
                    price = " OR " + price;
                }
                String sql = "";
                if (category.equals("") && search.equals("") && price.equals("")) {
                    //ca 3 input deu rong
                    sql = "SELECT COUNT(*) countt FROM \n"
                            + "(SELECT p.productID,p.productName,p.description,p.price,p.image,p.quantity,p.createDate,p.status,c.categoryName  \n"
                            + "FROM  dbo.tblCategory c INNER JOIN dbo.tblProduct p \n"
                            + "ON p.category = c.categoryID  where p.status = 'true' AND p.quantity > 0) tmp ";
                } else {
                    sql = "SELECT COUNT(*) countt FROM \n"
                            + "(SELECT p.productID,p.productName,p.description,p.price,p.image,p.quantity,p.createDate,p.status,c.categoryName  \n"
                            + "FROM  dbo.tblCategory c INNER JOIN dbo.tblProduct p \n"
                            + "ON p.category = c.categoryID where  ( " + search + "   " + category + "  " + price + ")  AND   p.status = 'true' AND p.quantity > 0) tmp ";
                }

//                System.out.println("-----cua count--------");
//                System.out.println(sql);
                pst = cn.prepareCall(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("countt");
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
            e.printStackTrace();
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

//        System.out.println("Count: " + count);
        if (count % 20 == 0) {
            //chia hết 20 vừa đủ k qua trang mới
            count = count / 20;
        } else {
            count /= 20;
            count++;
        }

        return count;
    }

    public ProductDTO getOneProduct(String productID) throws SQLException {
        Connection cn = null;
        ProductDTO product = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT p.productID,p.productName,p.description,p.price,p.image,p.quantity,p.createDate,p.status,c.categoryName  \n"
                        + "FROM  dbo.tblCategory c INNER JOIN dbo.tblProduct p \n"
                        + "ON p.category = c.categoryID WHERE p.productID = '" + productID + "'";
                pst = cn.prepareCall(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String proID = rs.getString("productID");

                    String productName = rs.getString("productName");

                    String description = rs.getString("description");

                    float productPrice = rs.getFloat("price");

                    String img = rs.getString("image");

                    int quantity = rs.getInt("quantity");

                    Date date = rs.getDate("createDate");

                    boolean status = rs.getBoolean("status");

                    String productCategory = rs.getString("categoryName");

                    product = new ProductDTO(proID, productName, description, img, productPrice, quantity, date, status, productCategory);
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
        return product;
    }

    public int deleteProduct(String list[]) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                for (String product : list) {
                    String sql = "UPDATE dbo.tblProduct SET status = '0' WHERE productID = '" + product + "'";
                    pst = cn.prepareCall(sql);
                    rs = pst.executeUpdate();
//                    System.out.println(product);
                }

            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {

            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }

        }
        return rs;
    }

    public int updateProduct(ProductDTO product) throws SQLException {
        Connection cn = null;
        int rs = 0;
        PreparedStatement pst = null;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String id = product.getProductID();
                String name = product.getProductName();
                String description = product.getDescription();
                String category = product.getCategory();
                if (category.equals("food")) {
                    category = "1";
                } else {
                    category = "2";
                }
                int quantity = product.getQuantity();
                float price = product.getPrice();
                boolean status = product.isStatus();

                String sql = "UPDATE dbo.tblProduct SET productName = '" + name + "', description ='" + description + "', price = " + price + ", quantity = " + quantity + ",status = '" + status + "', category = '" + category + "' \n"
                        + "WHERE productID = '" + id + "'";
                pst = cn.prepareCall(sql);
                rs = pst.executeUpdate();

            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }

        }
        return rs;
    }

    public int createProduct(ProductDTO product) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = 0;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String id = product.getProductID();
                String name = product.getProductName();
                String price = product.getPrice() + "";
                String quantity = product.getQuantity() + "";
                String category = product.getCategory() + "";
                if (category.equals("food")) {
                    category = "1";
                } else {
                    category = "2";
                }

                String img = product.getImg();
                String desciption = product.getDescription();

                String sql = "INSERT dbo.tblProduct\n"
                        + "        ( productID ,\n"
                        + "          productName ,\n"
                        + "          description ,\n"
                        + "          price ,\n"
                        + "          image ,\n"
                        + "          quantity ,\n"
                        + "          createDate ,\n"
                        + "          status ,\n"
                        + "          category\n"
                        + "        )\n"
                        + "VALUES  ( '" + id + "' , -- productID - varchar(20)\n"
                        + "          '" + name + "' , -- productName - varchar(20)\n"
                        + "          N'" + desciption + "' , -- description - nvarchar(1000)\n"
                        + "          " + price + " , -- price - float\n"
                        + "          N'" + img + "' , -- image - nvarchar(500)\n"
                        + "          " + quantity + " , -- quantity - int\n"
                        + "          GETDATE() , -- createDate - datetime\n"
                        + "          'true' , -- status - bit\n"
                        + "          '" + category + "'  -- category - varchar(6)\n"
                        + "        )";
                pst = cn.prepareCall(sql);
//                System.out.println(sql);
                rs = pst.executeUpdate();
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }

            if (cn != null) {
                cn.close();
            }

        }
        return rs;
    }

    public ArrayList<OrderDTO> getOrder(String date, String userID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<OrderDTO> list = new ArrayList<>();
        try {
            cn = MyConnection.getConnection();

            if (cn != null) {
               date = date.equals("") ? "" : " o.orderDate= '"+date+"' AND ";
                String sql = "SELECT o.orderID, o.totalMoney, o.orderDate\n"
                        + "FROM dbo.tblOrders o  INNER JOIN dbo.tblUsers u ON u.userID = o.userID\n"
                        + "WHERE "+date+" u.userID ='"+userID+"'";
//                System.out.println(sql);
                pst = cn.prepareCall(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    Date orderDate = rs.getDate("orderDate");
                    float total = rs.getFloat("totalMoney");
                    OrderDTO oder = new OrderDTO(orderID, orderDate, total);
                    list.add(oder);
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
        return list;
    }

    public ArrayList<OrderDetailDTO> getOrderDetailDTO(String orderID, String name) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<OrderDetailDTO> list = new ArrayList<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                orderID = orderID.equals("") ? "" : "o.orderID = '" + orderID + "'";
                if (!name.equals("")) {
                    orderID = orderID + " AND ";
                }
                name = name.equals("") ? "" : "p.productName LIKE '%"+name+"%'";
                
                String sql = "SELECT p.productName, p.price, d.quantity\n"
                        + "FROM dbo.tblOrderDetail d JOIN dbo.tblOrders  o ON d.orderID = o.orderID JOIN dbo.tblProduct p ON p.productID = d.productID\n"
                        + "\n"
                        + "WHERE "+orderID+"  "+name+"  ";

                pst = cn.prepareCall(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    Float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    OrderDetailDTO orderDetail = new OrderDetailDTO(productName, price, quantity);
                    list.add(orderDetail);
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
        return list;

    }

//   public static void main(String[] args) throws SQLException {
//        ProductDAO dao = new ProductDAO();
//        ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
//        list = dao.getProductAdmin("", "", "", "2");
//        for (ProductDTO productDTO : list) {
//            System.out.println(productDTO.getProductName() + "-");
//            System.out.println(productDTO.isStatus());
//            System.out.println("----");
//        }
//        ArrayList<String> a = new ArrayList<>();
//        a.add("2");
//        a.add("3");
//        System.out.println(dao.deleteProduct(a));
//       ProductDTO product = new ProductDTO("t1", "testName","descup", null,241 , 5, null, true, "1");
//           int i = dao.updateProduct(product);
//        System.out.println(i);
//        int i = dao.createProduct(product);
//        System.out.println("\n"+ i);
//        ArrayList<OrderDetailDTO> list = null;
//        list = dao.getOderDetail("1", "", "user");
//        for (OrderDetailDTO orderDetailDTO : list) {
//            System.out.println(orderDetailDTO.getName());
//       }
//
//    }
}
