<%-- 
    Document   : purchaseOrder
    Created on : 3-Mar-2020, 1:36:23 PM
    Author     : jlnnf
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Work+Sans:100,300,400,700,900" rel="stylesheet">
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/nouislider.min.css">
        <link rel="stylesheet" href="css/bootstrap-datetimepicker.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/list_css/list.css">
        <link rel="stylesheet" href="css/purchorder.css">
        <link rel="stylesheet" href="css/fixedTable.css">
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>

        <title>FIT - Purchase Orders</title>
    </head>
    <body>
 <%-- Navigation Bar--%>
        <nav class=" px-4 navbar navbar-expand-lg navbar-dark bg-secondary mb-4">
            <img src="images/logo_transparent.png" height="125"><a class="navbar-brand" href="#"></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown4" aria-controls="navbarNavDropdown4" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown4">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="mainServlet"><span class="icon-home2 mr-1 wrap-icon"></span><span>Home</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#"><span class=" icon-format_list_bulleted mr-1 wrap-icon"></span><span>Purchase Order</span></a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="mealServlet"><span class=" icon-cutlery mr-1 wrap-icon"></span><span>Meal</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="InventoryServlet"><span class=" icon-format_list_bulleted mr-1 wrap-icon"></span><span>Inventory</span></a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="ManageClientServlet"><span class=" icon-person_outline mr-1 wrap-icon"></span><span>Manage Clients</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="report"><span class="icon-library_books mr-1 wrap-icon"></span><span>Report</span></a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="icon-perm_data_setting mr-1 wrap-icon"></span><span>Settings</span>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">My Account</a>
                            <a class="dropdown-item" href="login">Logout</a>

                    </li>
            </div>
        </nav>
        <%--End of Navigation Bar --%>

        <div class="poHeader">
            <h1><b>Purchase Orders</b></h1>
        </div>
        <hr class="style1">



        <div class="createBtn" align="right">
            <a target="_blank" href="addPO" class="btn btn-pill px-4 btn-outline-primary"> + Add PO</a>
        </div>



        <%-- Purchase order list  --%>

        <div class="row d-flex justify-content-center">          
            <div class="just-padding col-11">
                <div class="tableFixHead">
                    <table class="table table-bordered">
                        <thead >
                            <tr>
                                <th>Purchase Order #</th>
                                <th>Client # </th> 
                                <th>Company Name</th>
                                <th>Company Email</th>
                                <th>Date</th> 
                                <th>Date Required</th>
                                <th>Delivery Status</th>
                                <th>Completed</th>
                                <th>Notes</th>
                                <th>Delete</th>
                                <th>Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="po" items="${pos}">
                                <tr>
                                    <td>${po.poNo}</td>
                                    <td>${po.clientNo}</td>
                                    <td>${po.empName}</td>
                                    <td>${po.empEmail}</td>
                                    <td>${po.poDatePlaced}</td>
                                    <td>${po.poDateRequired}</td>
                                    <td>${po.delivery}</td>
                                    <td>${po.completed}</td>
                                    <td>${po.poNotes}</td>
                                    <td> 
                                        <form action="addPO" method="POST">
                                            
                                            <button class="btn btn-md" ><i class="icon icon-trash" style="font-size:25px;color:#eb1314 "></i></button>
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="selectedPoNo" value="${po.poNo}">                        
                                        </form>
                                    </td>
                                    <td>
                                        <form action="addPO" method="GET">
                                            <button class="btn btn-md"><i class="icon icon-pencil" style="font-size:25px;color: #ffda7a"></i></button>
                                            <input type="hidden" name="action" value="view">
                                            <input type="hidden" name="selectedPoNo" value="${po.poNo}">                        
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
        <br>

    <center><p>Copyright</p></center>
    <center><p>@2020 Food Inventory Tracker</p></center>



        <%-- END PO LIST  --%>
        <%-- JS --%>

        <script src="js/popper.min.js"></script>     
        <script src="js/moments.min.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/meal-list.js"></script>
        <script src="js/add.js"></script>
        <script src="js/purchorder.js"></script>

    </body>
</html>