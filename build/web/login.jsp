<%-- 
    Document   : login
    Created on : Nov 4, 2020, 11:36:21 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Login page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->
        <link rel="icon" type="image/png" href="images/icons/favicon.ico" />
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <!--===============================================================================================-->
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>

    <body>

        <div class="limiter">
            <div class="container-login100" style="background-image: url('images/loginBackground.jpg');">
                <div  class="wrap-login100 p-l-110 p-r-110 p-t-62 p-b-33">
                    <form action="MainController" method="POST" class="login100-form validate-form flex-sb flex-w">
                        <span class="login100-form-title p-b-53">
                            Sign In With
                        </span>


                        <a style="position: absolute; top: 125px;" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/Assignment1_TranBaSon/LoginGoogleController&response_type=code
                           &client_id=762869505081-71sqiieiboda9ruld8f24mls1giqsp3j.apps.googleusercontent.com&approval_prompt=force" class="btn-google m-b-20 ">
                            <img src="images/icons/icon-google.png " alt="GOOGLE "> Google
                        </a>


                        <div class="p-t-31 p-b-9 ">
                            <span class="txt1 ">
                                <input type="hidden ">
                            </span>
                        </div>
                        <div style="position: relative; " class="p-t-31 p-b-9 ">
                            <span style="position: absolute; bottom: 0;right: 395px;; " class="txt1 ">
                                Username
                            </span>
                        </div>
                        <div class="wrap-input100 validate-input " data-validate="Username is required ">
                            <input value="${sessionScope.userIDERROR}" class="input100 " type="text " name="txtUserName">
                            <span class="focus-input100 "></span>
                        </div>

                        <div class="p-t-13 p-b-9 ">
                            <span class="txt1 ">
                                Password
                            </span>


                        </div>
                        <div class="wrap-input100 validate-input " data-validate="Password is required ">
                            <input class="input100 " type="password" name="txtPassword">

                            <span class="focus-input100 "></span>
                        </div>
                        <p style="color: red; font-size: 20px">${sessionScope.ERROR.getUserNameError()}</p>

                        <div class="container-login100-form-btn m-t-17 ">
                            <button name="btnAction" value="login" class="login100-form-btn ">
                                Sign In
                            </button>
                        </div>



                    </form>

                </div>
            </div>

        </div>




        <!--===============================================================================================-->
        <script src="vendor/jquery/jquery-3.2.1.min.js "></script>
        <!--===============================================================================================-->
        <script src="vendor/animsition/js/animsition.min.js "></script>
        <!--===============================================================================================-->
        <script src="vendor/bootstrap/js/popper.js "></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js "></script>
        <!--===============================================================================================-->
        <script src="vendor/select2/select2.min.js "></script>
        <!--===============================================================================================-->
        <script src="vendor/daterangepicker/moment.min.js "></script>
        <script src="vendor/daterangepicker/daterangepicker.js "></script>
        <!--===============================================================================================-->
        <script src="vendor/countdowntime/countdowntime.js "></script>
        <!--===============================================================================================-->
        <script src="js/main.js "></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>

    </body>

</html>