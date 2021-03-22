<%-- 
    Document   : management
    Created on : Nov 5, 2020, 1:06:39 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <c:if test="${sessionScope.USER.getRoleID() != 'admin'}">
        <c:url var="loginFirst" value="MainController">
            <c:param name="btnAction" value="backToLogin"></c:param>
        </c:url>
        <c:redirect url="${loginFirst}"></c:redirect>
    </c:if>

    <head>
        <title>Management Product Page</title>
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
        <nav class="pr-0 pl-0 navbar navbar-expand-md navbar-dark bg-dark main-menu" style="box-shadow:none">
            <div  class="container-fluid" >

                <button type="button" id="sidebarCollapse" class="btn btn-link d-block d-md-none">
                    <i class="bx bx-menu icon-single"></i>
                </button>

                <a class="navbar-brand" href="#">
                    <h4 class="font-weight-bold">HannaShop</h4>
                </a>
                <div class="collapse navbar-collapse">
                    <p style="color: white;padding-left: 900px" class="mb-0 ml-2">Welcome ${sessionScope.USER.getFullName()}</p>
                    <i class="fas fa-creative-commons-zero    "></i>
                </div>
                <div> 
                    <ul class="navbar-nav">
                        <li class="nav-item ml-md-3">
                            <c:url var="logout" value="MainController" >  
                                <c:param name="btnAction" value="searchAdmin" ></c:param>
                            </c:url>
                            <a class="btn btn-success" href="${logout}"><i class="bx bxs-user-circle mr-1"></i>Back</a>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">UPDATE PRODUCT</h1>
            </div>
        </section>

        <c:set var="warning" value="${sessionScope.ERROR_UPDATE}"> </c:set>

        <c:if test="${warning!=null}">
            <div class="alert alert-primary" role="alert">
                ${warning}
            </div>
        </c:if>             


        <!----------------------------------------PRODUCT-------------------------------------------->



        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">NO </th>
                                    <td></td>
                                    <th scope="col"></th>
                                    <th scope="col" class="text-center">NAME</th>
                                    <th scope="col" class="text-center">CATEGORY</th>
                                    <th scope="col" class="text-center">Description</th>
                                    <th scope="col" class="text-center">QUANTITY</th>
                                    <th scope="col" class="text-center">Price</th>
                                    <th scope="col" class="text-center">Status</th>
                                    <th> </th>
                                    <th> </th>
                                    <th> </th>
                                </tr>

                            </thead>
                            <tbody>

                                <c:set var="listProduct" value="${sessionScope.LIST_PRODUCT_UPDATE}"></c:set>
                                <c:if test="${listProduct!=null}">

                                <form action="MainController" method="POST">


                                    <c:forEach varStatus="counter" var="product" items="${listProduct}"> 


                                        <!-- ------------------    PRODUCT--------->

                                        <tr style="text-align: center">
                                            <td>${counter.index + 1}</td>
                                            <td><div style="width: 110px;height: 110px;"><img style="width: 100%;height: 100%" src="images/bg-01.jpg" alt="Italian Trulli"></div></td>
                                            <td> <input type="hidden" name="txtIDUpdate" value="${product.productID}"></td>

                                            <td><h6><input minlength="1" maxlength="20" name ="txtNameUpdate" class="form-control" required="required" value="${product.productName}" style="width: 130px" /></h6></td>
                                                    <c:set var="listCategory" value="${sessionScope.LIST_CATEGORY}"></c:set>

                                                <td><select name="txtCategoryUpdate" style="height: 39px">
                                                    <c:forEach var="category" items="${listCategory}">
                                                        <c:if test="${category.equals(product.category)}">
                                                            <option selected="selected" value="${category}" >${category}</option>
                                                        </c:if>
                                                        <c:if test="${!category.equals(product.category)}">
                                                            <option value="${category}" >${category}</option>
                                                        </c:if>

                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td><h6><input minlength="1" maxlength="300" required="required" name ="txtDescriptionUpdate" class="form-control" type="text" value="${product.description}" style="width: 200px" /></h6></td>
                                            <td class="pl-4"><input min="0" max="500" type="number" name ="txtQuantityUpdate"  value="${product.quantity}" style="width: 50px" /></td>
                                            <td><input min="1" max="100" name ="txtPriceUpdate" required="required" type="number" value="${product.price}" style="width: 50px" /></h6></td>
                                            <td style="padding-top: 13px"> 

                                                <c:set var="listStatus" value="${sessionScope.LIST_STATUS}"></c:set>
                                                    <select name="txtStatusUpdate" style="height: 37px" > 
                                                    <c:if test="${product.status == true}">
                                                        <option selected="selected" value="true" >true</option>
                                                        <option value='false'  >false</option>
                                                    </c:if>
                                                    <c:if test="${product.status == false}">
                                                        <option selected="selected" value="false" >false</option>
                                                        <option value='true'  >true</option>
                                                    </c:if>    
                                                </select>

                                            </td>



                                        <input type="hidden" name="activePage" value="${sessionScope.ACTIVE_PAGE}">
                                        <input type="hidden" name="txtSearch" value="${sessionScope.NAME_SEARCH}">
                                        <input type="hidden" name="txtCategory" value="${categoryForPaging}">
                                        <input type="hidden" name="txtPrice" value="${priceForPaging}">



                                        <td  style="padding-top: 15px" class="text-right"><button type="submit" name="btnAction" value="adminUpdateAction" class="btn btn-sm btn-warning"><i class="fa fa-edit"></i> </button></td>
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
