/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author ADMIN
 */
public class ErrorDTO {
    String userNameError;
    String passwordError;
    String emailError;

    public ErrorDTO(String userNameError, String passwordError, String emailError, String phoneError) {
        this.userNameError = userNameError;
        this.passwordError = passwordError;
        this.emailError = emailError;
        this.phoneError = phoneError;
    }
    String phoneError;

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }
   
    
}
