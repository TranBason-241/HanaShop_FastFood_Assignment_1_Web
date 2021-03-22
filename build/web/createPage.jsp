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
        <title>Create Product Page</title>
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
                <h1 class="jumbotron-heading">CREATE PRODUCT</h1>
            </div>
        </section>

        <c:set var="warning" value="${sessionScope.MESSAGE_CREATE}"> </c:set>

        <c:if test="${warning!=null}">
            <div class="alert alert-primary" role="alert">
                ${warning}
            </div>
        </c:if>             
        <!----------------------------------------PRODUCT-------------------------------------------->
        <div class=" mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <th class="text-center" scope="col">ID</th>
                                    <th scope="col" class="text-center">NAME</th>
                                    <th scope="col" class="text-center">CATEGORY</th>
                                    <th scope="col" class="text-center">Description</th>
                                    <th scope="col" class="text-center">QUANTITY</th>
                                    <th scope="col" class="text-center">Price</th>
                                    <th> </th>
                                    <th> </th>
                                    <th> </th>
                                </tr>
                            </thead>
                            <tbody>



                            <form action="CreateProductAdmin" method="POST" enctype="multipart/form-data">

                                <!-- ------------------    PRODUCT--------->

                                <tr style="text-align: center">
                                    <td></td>
                                    <td> <input required="required" type="file" name="fileUp" > </td>
                                    <td> <input z class="form-control" style="width: 100px" type="text" required="required" minlength="1" maxlength="20" name="txtIDCreate" value="${requestScope.ID_CREATE}"></td>

                                <td style="width: 200px"><input minlength="1" maxlength="20" name ="txtNameCreate" class="form-control" required="required" value="${requestScope.NAME_CREATE}" style="width: 200px" /></td>
                                        <c:set var="listCategory" value="${sessionScope.LIST_CATEGORY}"></c:set>

                                        <td><select name="txtCategoryCreate" style="height: 39px">
                                            <c:forEach var="category" items="${listCategory}">

                                                <c:if test="${category.equals(requestScope.CATEGORY_CREATE)}">
                                                    <option selected="selected" value="${category}" >${category}</option>
                                                </c:if>
                                                <c:if test="${!category.equals(requestScope.CATEGORY_CREATE)}">
                                                    <option value="${category}" >${category}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td  style="width: 200px" class="mr-0"><input minlength="1" maxlength="300" required="required" name ="txtDescriptionCreate" class="form-control" type="text" value="${requestScope.DESCRIPTION_CR200EATE}" style="width: 200px; margin-right: 0" /></td>
                                    <td style="width: 50px" class="pd-0"><input min="0" max="100" type="number" name ="txtQuantityCreate"  value="${requestScope.QUANITY_CREATE}" style="width: 50px" /></td>
                                    <td><input min="1" max="100" name ="txtPriceCreate" required="required" type="number" value="${requestScope.PRICE_CREATE}" style="width: 50px" /></h6></td>








                                    <td  style="padding-top: 15px" class="text-right"><button type="submit" name="btnAction" value="adminCreateAction" class="btn btn-sm btn-warning"><i class="fa fa-edit"></i> </button></td>
                                </tr>

                            </form>
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
