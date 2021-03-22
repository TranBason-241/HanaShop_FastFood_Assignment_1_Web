<%-- 
    Document   : search.jsp
    Created on : Jan 7, 2021, 11:05:02 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>search Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/boxicons@2.0.2/css/boxicons.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css"></script>
    </head>
    <body>

        <!--//Check da login chua-->




        <nav class="pr-0 pl-0 navbar navbar-expand-md navbar-light bg-light main-menu" style="box-shadow:none">
            <div  class="container-fluid" style="background-color: red;">

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

                        <button name="btnAction" value="search" class="ml-2 btn btn-success my-2 my-sm-0" type="submit"><i class="bx bx-search"></i></button>
                    </form>
                    <p class="mb-0 ml-2">${sessionScope.USER.getFullName()}</p>
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
                        <c:if test="${sessionScope.USER==null}">
                            <li class="nav-item ml-md-3">
                                <c:url var="login" value="MainController" >  
                                    <c:param name="btnAction" value="login" ></c:param>
                                </c:url>
                                <a class="btn btn-success" href="${login}"><i class="bx bxs-user-circle mr-1"></i>login</a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.USER!=null}">
                            <li class="nav-item ml-md-3">
                                <c:url var="logout" value="MainController" >  
                                    <c:param name="btnAction" value="logout" ></c:param>
                                </c:url>
                                <a class="btn btn-success" href="${logout}"><i class="bx bxs-user-circle mr-1"></i>logout</a>
                            </li>
                        </c:if> 


                    </ul>
                </div>

            </div>
        </nav>

        <div style="height: 300px; width: 100%;  background-image: url(images/background1.jpg);" > </div>


        <!--        ------------------------PRODUCT-------------------------------------->
        <div class="mt-5 container">
            <div class="row">

                <c:set value="${sessionScope.LIST_PRODUCT}" var="listProduct"></c:set>
                <c:if test="${listProduct!=null}">
                    <c:forEach var="product" items="${listProduct}">
                        <div class="card col-3 pd-2">
                            <img class="card-img-top" src="${product.img}" alt="">
                            <div class="card-body">
                                <h4 class="card-title">${product.productName}</h4>
                                <span style=" display: block;
                                      width: 100%;
                                      overflow: hidden;
                                      white-space: nowrap;
                                      text-overflow: ellipsis;" class="card-text">${product.description}</span>
                                <p class="card-text">Date ${product.createDate}</p>
                                <h3>Price:${product.price}</h3>
                                <c:url var="addPro" value="MainController" >
                                    <c:param name="btnAction" value="add" ></c:param>
                                    <c:param name="productID" value="${product.productID}" ></c:param>

                                    <c:param name="activePage" value="${sessionScope.ACTIVE_PAGE}"> </c:param>
                                    <c:param name="txtSearch" value="${sessionScope.NAME_SEARCH}"></c:param>
                                    <c:param name="txtCategory" value="${categoryForPaging}"></c:param>
                                    <c:param name="txtPrice" value="${priceForPaging}"></c:param>
                                </c:url>
                                <p style="display: flex" ><span style="width: 70%">Quantity: ${product.quantity}</span><a  href="${addPro}"><button class="btn btn-success">Add</button></a></p>   
                            </div>
                        </div>
                    </c:forEach>
                </c:if>



            </div>
            <div>
                <ul style="width: 300px; list-style-type: none; margin: 0 auto; font-size: 20px" class="d-flex mt-5 mb-4" >

                    <c:if test="${sessionScope.NUMBER_OF_PAGE!=null}">

                        <c:forEach begin="1" end="${sessionScope.NUMBER_OF_PAGE}"  varStatus="counter">
                            <li class="ml-2"><a href="MainController?btnAction=search&activePage=${counter.index}&txtSearch=${sessionScope.NAME_SEARCH}&txtCategory=${categoryForPaging}&txtPrice=${priceForPaging}">${counter.index}</a></li>  
                            </c:forEach>
                        </c:if>
                                


                </ul>
            </div>

        </div>
    </body>
</html>

