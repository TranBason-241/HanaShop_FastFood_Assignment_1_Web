<%-- 
    Document   : management
    Created on : Nov 5, 2020, 1:06:39 PM
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
        <title>History order</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>

    <body>

        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">


        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading"> ORDER HISTORY</h1>
            </div>
        </section>


        <nav class="pr-0 pl-0 navbar navbar-expand-md navbar-dark bg-dark main-menu" style="box-shadow:none">
            <div  class="container-fluid" >

                <button type="button" id="sidebarCollapse" class="btn btn-link d-block d-md-none">
                    <i class="bx bx-menu icon-single"></i>
                </button>

                <a class="navbar-brand" href="#">
                    <h4 class="font-weight-bold">HannaShop</h4>
                </a>
                <div class="collapse navbar-collapse">
                    <form action="HistoryController" method="post" class="form-inline my-2 my-lg-0 mx-auto">
                        <input  name="txtSearchDate" class="form-control" type="date" >
<!--                        <input value="" name="txtSearchHistory"  class="form-control" type="txtSearch" placeholder="Search for products..." aria-label="Search">-->
                        <button  class="ml-2 btn btn-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                    <p style="color: white" class="mb-0 ml-2">Welcome ${sessionScope.USER.getFullName()}</p>
                    <i class="fas fa-creative-commons-zero    "></i>
                </div>

                <div> 
                    <ul class="navbar-nav">
                        <li class="nav-item">
                        </li>
                        <li class="nav-item ml-md-3">
                            <c:url var="logout" value="MainController" >  
                                <c:param name="btnAction" value="viewCart" ></c:param>
                            </c:url>
                            <a class="btn btn-success" href="${logout}"><i class="bx bxs-user-circle mr-1"></i>back to cart</a>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>

       


        <!----------------------------------------PRODUCT-------------------------------------------->



        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <td scope="col"></td>
                                    <th scope="col"></th>
                                    <th scope="col" class="text-center">ID</th>
                                    <th scope="col" class="text-center"></th>
                                    <th scope="col" class="text-center"></th>
                                    <th scope="col" class="text-center"></th>
                                    <th scope="col" class="text-center">total</th>
                                    <th scope="col" class="text-center">order date</th>
                                    <th> </th>
                                    <th> </th>
                                    <th> </th>
                                </tr>

                            </thead>
                            <tbody>

                                <c:set var="listProduct" value="${sessionScope.LIST_ORDER}"></c:set>
                                <c:if test="${listProduct!=null}">

                                <form action="MainController" method="POST">

                                 
                                    <c:forEach varStatus="counter" var="product" items="${listProduct}"> 
                                        <!-- ------------------    PRODUCT--------->
                                        <tr style="text-align: center">
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td><h6>${product.orderID}</h6></td>
                                            <td><h6></h6></td>
                                            
                                            <td><h6></h6></td>
                                            <td class="pl-4"><h6></h6></td>
                                            <td style="padding-top: 13px"> 
                                                <h6>${product.total}</h6>
                                            </td>
                                            <td style="padding-top: 13px"> 
                                                <h6>${product.orderDate}</h6>
                                            </td>
                                            
                                            <c:url var="ViewOrderDetail" value="OrderDetailController">
                                             
                                                <c:param name="orderIDToView" value="${product.orderID}" ></c:param>
                                            </c:url>
                                            
                                            
                                            <td><h6><a href="${ViewOrderDetail}" class="btn btn-success" > View Detail</a></h6></td>
                                            <!--                                            Giu lai search, page do:-->
                                        </tr>
                                    </c:forEach>
                                </form>

                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
           
        </div>

        <!-- Footer -->


        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
