<%-- 
    Document   : cart
    Created on : Nov 5, 2020, 10:54:56 AM
    Author     : ADMIN
--%>




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <c:if test="${sessionScope.USER.getRoleID() != 'user'}">
        <c:url var="loginFirst" value="MainController">
            <c:param name="btnAction" value="backToLogin"></c:param>
        </c:url>
        <c:redirect url="${loginFirst}"></c:redirect>
    </c:if>



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>  
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <!------ Include the above in your HEAD tag ---------->

    </head>
    <body>




        <c:set var="cart" value="${sessionScope.CART.getCart().values()}" ></c:set>

            <!--      start /   in THông báo sau khi checkOut-->
        <c:set var="warning2" value="${sessionScope.MESSAGE_ORDER}"> </c:set>
        <c:if test="${warning2!=null}">
            <div class="alert alert-primary" role="alert">
                ${warning2}
            </div>
        </c:if>
        <!--      End /   in THông báo sau khi checkOut-->


        <!--        //CheckDonHangConTrongKhong-->
        <c:if test="${cart !=null && not empty cart}">

            <div class="row">
                <div class="col-sm-12 col-md-10 col-md-offset-1">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th></th>
                                <th style="text-align: center;">Quantity</th>
                                <th></th>
                                <th class="text-center">Price</th>
                                <th class="text-center">Total</th>
                                <th> </th>
                            </tr>
                        </thead>

                        <c:set var="warning" value="${sessionScope.MESSAGE_CART}"> </c:set>
                        <c:if test="${warning!=null}">
                            <div class="alert alert-primary" role="alert">
                                ${warning}
                            </div>
                        </c:if>






                        <tbody>
                            <c:if test="${not empty cart}">
                                <c:set var="total"  value="0" ></c:set>
                                <c:forEach var="elem" items="${cart}" >

                                    <c:set var="total" value="${total+elem.getPrice()*elem.getQuantity()}" ></c:set>
                                        <tr>
                                            <td class="col-sm-8 col-md-5">
                                                <div class="media">
                                                    <a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;"> </a>
                                                    <div class="media-body">
                                                        <h4 class="media-heading"><a href="#">${elem.getProductName()}</a></h4>
                                                    <h5 class="media-heading">  <a href="#">sale 0%</a></h5>
                                                    <span>Status: </span><span class="text-success"><strong>No Voucher</strong></span>
                                                </div>
                                            </div>
                                        </td>

                                        <td style="padding-right: 0;padding-top: 0 " >
                                            <div style="width: 30px;height: 30px;">
                                                <c:url var="downn" value="MainController">
                                                    <c:param name="btnAction" value="updateQuantity"></c:param>
                                                    <c:param name="txtOpetator" value="-"></c:param>
                                                    <c:param name="productUpdateID" value="${elem.getProductID()}"></c:param>
                                                </c:url>

                                                <a href="${downn}"><button style="width: 30px;height: 30px" class="btn btn-success" style="display: block">-</button></a>
                                            </div>
                                        </td>
                                        <td  style="text-align: center; padding: 0">

                                            <h5 class="pl-0 pr-0" >${elem.getQuantity()}</h5>
                                        </td>
                                        <td style="padding-left: 0;padding-top: 0 " >
                                            <div style="width: 60px;height: 60px;">

                                                <c:url var="up" value="MainController">
                                                    <c:param name="btnAction" value="updateQuantity"></c:param>
                                                    <c:param name="txtOpetator" value="+"></c:param>
                                                    <c:param name="productUpdateID" value="${elem.getProductID()}"></c:param>
                                                </c:url>


                                                <a href="${up}"><button style="width: 30px;height: 30px" class="btn btn-success" style="display: block">+</button></a>

                                            </div>
                                        </td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>${elem.getPrice()}$</strong></td>

                                        <td class="col-sm-1 col-md-1 text-center"><strong>${elem.getPrice()*elem.getQuantity()}$</strong></td>


                                        <td class="col-sm-1 col-md-1 text-center"><strong></strong></td>
                                        <td class="col-sm-1 col-md-1">

                                            <c:url var="delete" value="MainController">
                                                <c:param name="btnAction" value="DeleteProductByUser"></c:param>

                                                <c:param name="productIDDeleteByUser" value="${elem.getProductID()}"></c:param>
                                            </c:url>

                                            <a href="${delete}">
                                                <button type="button" class="btn btn-danger"  onclick="return confirm('Ok to Remove?');">
                                                    <span class="glyphicon glyphicon-remove"></span> Remove
                                                </button></td>

                                        </a>

                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td>   </td>
                                <td>   </td>
                                <td>   </td>
                                <td>
                                    <h3>Total</h3>
                                </td>
                                <td class="text-right">
                                    <h3><strong>${total}$</strong></h3>

                                </td>
                            </tr>
                            <tr>
                            </tr>
                            <tr>
                            </tr>
                            <tr>
                                <td>   </td>
                                <td>   </td>
                                <td>   </td>
                                <td>
                                    <c:url var="back" value="MainController" >
                                        <c:param name="btnAction" value="search" ></c:param>
                                            <!-- sau khi add load van con -->
                                        <c:param name="activePage" value="${sessionScope.ACTIVE_PAGE}"> </c:param>
                                        <c:param name="txtSearch" value="${sessionScope.NAME_SEARCH}"></c:param>
                                        <c:param name="txtCategory" value="${categoryForPaging}"></c:param>
                                        <c:param name="txtPrice" value="${priceForPaging}"></c:param>
                                    </c:url>
                                    <a href="${back}">
                                        <button type="button" class="btn btn-default">
                                            <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                                        </button>
                                        <a/>
                                </td>
                                <td>
                                    <c:url var="checkOut" value="MainController">
                                        <c:param name="btnAction" value="checkOut" ></c:param>
                                    </c:url>
                                    <a href="${checkOut}">
                                        <button type="button" class="btn btn-success">
                                            <span class="glyphicon glyphicon-play"> Checkout</span>
                                        </button>
                                        <h1 style="color: green">${requestScope.SUCCESS} </h1>
                                </td>
                                </a>                         
                            </tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>
                            <!--                             Thanh Toán bằng Paypal-->
                            <form action="authorize_payment" method="POST">
                                <input type="hidden"  name="total" value="${total}"/>
                                <input class="btn btn-primary" type="submit"  value="Pay with payPal" />
                            </form>
                        </td>
                        <tr>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if> 
        <!--     ket thuc   if check gio Hang-->
        <c:if test="${cart==null ||  empty cart}">
            <p style="color: red; font-size: 30px; text-align: center"> Your cart is empty </p>
            <c:url var="back" value="MainController" >
                <c:param name="btnAction" value="search" ></c:param>
                    <!-- sau khi add load van con -->
                <c:param name="activePage" value="${sessionScope.ACTIVE_PAGE}"> </c:param>
                <c:param name="txtSearch" value="${sessionScope.NAME_SEARCH}"></c:param>
                <c:param name="txtCategory" value="${categoryForPaging}"></c:param>
                <c:param name="txtPrice" value="${priceForPaging}"></c:param>
            </c:url>
            <a style="margin-left: 46%" href="${back}">
                <button style="margin: 0 auto" type="button" class="btn btn-default">

                    <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                </button>
                <a/>
            </c:if>



            <c:url var="viewHistory" value="MainController">
                <c:param name="btnAction" value="viewHistory" ></c:param>
            </c:url>   
            <a style="margin-left: 44%" href="${viewHistory}">
                <button style="margin: 0 auto;height: 50px; font-size: 20px" type="button" class="btn btn-warning">

                    View History
                </button>
                <a/>
                </body>
                </html>
