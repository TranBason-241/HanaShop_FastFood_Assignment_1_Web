/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.HashMap;

/**
 *
 * @author ADMIN
 */
public class CartDTO {
    private String custumer;
    HashMap<String,ProductDTO> cart;

    public CartDTO(String custumer, HashMap<String, ProductDTO> cart) {
        this.custumer = custumer;
        this.cart = cart;
    }

    public String getCustumer() {
        return custumer;
    }

    public void setCustumer(String custumer) {
        this.custumer = custumer;
    }

    public HashMap<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, ProductDTO> cart) {
        this.cart = cart;
    }
    
    public void add(ProductDTO pro){
        if(this.cart==null){
            cart= new HashMap<>();
        }
        if(cart.containsKey(pro.getProductID())){
            int tmp = cart.get(pro.getProductID()).getQuantity() + 1;
            cart.get(pro.getProductID()).setQuantity(tmp);
        }else {
            pro.setQuantity(1);
            cart.put(pro.getProductID(), pro);
        }
    }
    public void delete(String proID){
        if(this.cart==null){
            return;
        }
        if(this.cart.containsKey(proID)){
            cart.remove(proID);
        }
    }
    public void update(String proId, String operator){
        if(this.cart==null){
            return;
        }
        if(operator.equals("+")){
            if(this.cart.containsKey(proId)){
                int tmp = this.cart.get(proId).getQuantity()+1;
                this.cart.get(proId).setQuantity(tmp);
            }
        }else if(operator.equals("-")){
           if(this.cart.containsKey(proId)){
                if(this.cart.get(proId).getQuantity() <= 1){
                    return;
                }else {
                    int tmp = this.cart.get(proId).getQuantity() - 1;
                    this.cart.get(proId).setQuantity(tmp);
                }
            }
        }
    }
    
}
