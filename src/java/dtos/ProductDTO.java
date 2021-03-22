/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class ProductDTO {
  private String productID;
  private   String productName;
  private   String description;
  private   String img;
  private   float price;
  private   int quantity;
  private   Date createDate;
  private   boolean status;
  private   String category;

   
    
    
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductDTO(String productID, String productName, String description, String img, float price, int quantity, Date createDate, boolean status, String category) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.img = img;
        this.price = price;
        this.quantity = quantity;
        this.createDate = createDate;
        this.status = status;
        this.category = category;
    }
            
            
            
}
