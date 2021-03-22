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
                    <form action="MainController" method="post" class="form-inline my-2 my-lg-0 mx-auto">
                        <select class="form-control mr-2" name="txtCategory" ">
                            <option value="Category">Category</option>
                            <c:set var="listCategory" value="${sessionScope.LIST_CATEGORY}"></c:set>
                            <c:set var="selectedCategory" value="${sessionScope.CATEGORY_SEARCH}"></c:set>
                            <c:set var="selectedCategory2" value=""></c:set>
                            <c:if test="${selectedCategory!=null}" >
                                <c:set var="selectedCategory2" value="${selectedCategory}" ></c:set>
                            </c:if>
                            <c:if test="${listCategory!=null}">
                                <c:forEach items="${listCategory}" var="category">
                                    <c:if test="${selectedCategory2.equals(category)}">
                                        <option selected="selected" value="${category}">${category}</option>
                                        <c:set var="categoryForPaging" value="${category}" ></c:set>
                                    </c:if>
                                    <c:if test="${!selectedCategory2.equals(category)}">
                                        <option value="${category}">${category}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </select>

                        <select class="form-control mr-2" name="txtPrice">
                            <c:set value="${sessionScope.LIST_PRICE}" var="listPrice"></c:set>
                            <c:set var="selectedPrice" value="${sessionScope.PRICE_SEARCH}"></c:set>
                            <c:set var="selectedPrice2" value=""></c:set>
                            <c:if test="${selectedPrice!=null}" >
                                <c:set var="selectedPrice2" value="${selectedPrice}" ></c:set>
                            </c:if>

                            <c:if test="${listPrice!=null}">
                                <c:forEach items="${listPrice}" var="price">
                                    <c:if test="${selectedPrice2.equals(price)}">
                                        <option selected="selected" value="${price}">${price}</option>
                                        <c:set var="priceForPaging" value="${price}" ></c:set>
                                    </c:if>

                                    <c:if test="${!selectedPrice2.equals(price)}">
                                        <option value="${price}">${price}</option>
                                    </c:if>

                                </c:forEach>
                            </c:if>

                        </select>


                        <input value="${sessionScope.NAME_SEARCH}" name="txtSearch"  class="form-control" type="txtSearch" placeholder="Search for products..." aria-label="Search">

                        <button name="btnAction" value="searchAdmin" class="ml-2 btn btn-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                    <p style="color: white" class="mb-0 ml-2">Welcome ${sessionScope.USER.getFullName()}</p>
                    <i class="fas fa-creative-commons-zero    "></i>
                </div>

                <div> 
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <c:url var="viewCart" value="MainController" >
                                <c:param name="btnAction" value="viewCart" ></c:param>
                                    <!-- sau khi add load van con -->
                                <c:param name="activePage" value="${sessionScope.ACTIVE_PAGE}"> </c:param>
                                <c:param name="txtSearch" value="${sessionScope.NAME_SEARCH}"></c:param>
                                <c:param name="txtCategory" value="${categoryForPaging}"></c:param>
                                <c:param name="txtPrice" value="${priceForPaging}"></c:param>
                            </c:url>

                            <a class="btn btn-link" href="${viewCart}"><i class="bx bxs-cart icon-single"></i> <span class="badge badge-danger"></span></a>
                        </li>
                        <li class="nav-item ml-md-3">
                            <c:url var="logout" value="MainController" >  
                                <c:param name="btnAction" value="logout" ></c:param>
                            </c:url>
                            <a class="btn btn-success" href="${logout}"><i class="bx bxs-user-circle mr-1"></i>logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">MANAGE PRODUCT</h1>
            </div>
        </section>
        <div style="padding-left: 1150px">

            <c:url var="createProduct" value="MainController" >
                <c:param name="btnAction" value="createProduct"  ></c:param>
            </c:url>
            <a style="margin-left: 300px" class="btn btn-success btn-sm ml-3" href="${createProduct}">
                ADD NEW PRODUCT
            </a>

        </div>


        <!----------------------------------------PRODUCT-------------------------------------------->



        <div class="container mb-4">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
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

                                <c:set var="listProduct" value="${sessionScope.LIST_PRODUCT}"></c:set>
                                <c:if test="${listProduct!=null}">

                                <form action="MainController" method="POST">

                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <th> <button type="submit" name="btnAction" value="adminDelete" class="btn btn-sm btn-danger"><i class="fa fa-trash"></i> </button> </th>
                                        <th> </th>
                                        <th> </th>
                                    </tr>
                                    <c:forEach varStatus="counter" var="product" items="${listProduct}"> 
                                        <!-- ------------------    PRODUCT--------->
                                        <tr style="text-align: center">
                                            <td></td>
                                            <td><div style="width: 110px;height: 110px;"><img style="width: 100%;height: 100%" src="images/bg-01.jpg" alt="Italian Trulli"></div></td>
                                            <td></td>
                                            <td><h6>${product.productName}</h6></td>
                                            <td><h6>${product.category}</h6></td>
                                            <c:set var="fixLenght" value="${product.description}" ></c:set>
                                            <c:if test="${product.description.length() > 30}">
                                                <c:set var="fixlength2" value="${product.description.substring(0,30)}" ></c:set>
                                                <c:set var="fixLenght" value="${fixlength2+='...'}" ></c:set>
                                            </c:if>
                                            <td><h6 style="width: 200px">${fixLenght}</h6></td>
                                            <td class="pl-4"><h6>${product.quantity}</h6></td>
                                            <td><h6>${product.price}</h6></td>
                                            <td style="padding-top: 13px"> 
                                                <h6>${product.status}</h6>
                                            </td>
                                            <!--                                            Giu lai search, page do:-->
                                        <input type="hidden" name="activePage" value="${sessionScope.ACTIVE_PAGE}">
                                        <input type="hidden" name="txtSearch" value="${sessionScope.NAME_SEARCH}">
                                        <input type="hidden" name="txtCategory" value="${categoryForPaging}">
                                        <input type="hidden" name="txtPrice" value="${priceForPaging}">

                                        <td style="padding-top: 15px"> <input type="checkbox"  name="checkedItem" value="${product.productID}" style="width: 30px;height: 30px"> </td>
                                            <c:url var="update" value="MainController">
                                                <c:param name="btnAction" value="adminUpdate" ></c:param>
                                                <c:param name="productUpdateID" value="${product.productID}" ></c:param>
                                            </c:url>
                                        <td  style="padding-top: 15px" class="text-right"><a class="btn btn-warning" href="${update}"><i class="fa fa-edit"></i></a></td>

                                        </tr>
                                    </c:forEach>
                                </form>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
                                
            <div>
                <ul style="width: 300px; list-style-type: none; margin: 0 auto; font-size: 20px" class="d-flex mt-5 mb-4" >

                    <c:if test="${sessionScope.NUMBER_OF_PAGE!=null}">

                        <c:forEach begin="1" end="${sessionScope.NUMBER_OF_PAGE}"  varStatus="counter">
                            <li class="ml-2"><a href="MainController?btnAction=searchAdmin&activePage=${counter.index}&txtSearch=${sessionScope.NAME_SEARCH}&txtCategory=${categoryForPaging}&txtPrice=${priceForPaging}">${counter.index}</a></li>  
                            </c:forEach>
                        </c:if>
                </ul>
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
