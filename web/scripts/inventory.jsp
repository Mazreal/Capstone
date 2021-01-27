<%-- 
    Document   : inventory
    Created on : 25-Feb-2020, 2:41:36 PM
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
        <link rel="stylesheet" href="css/fixedTable.css">
        <link rel="stylesheet" href="css/addInventory.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <title>FIT - Inventory</title>
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
                    <li class="nav-item">
                        <a class="nav-link" href="poServlet"><span class=" icon-format_list_bulleted mr-1 wrap-icon"></span><span>Purchase Order</span></a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="mealServlet"><span class=" icon-cutlery mr-1 wrap-icon"></span><span>Meal</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#"><span class=" icon-format_list_bulleted mr-1 wrap-icon"></span><span>Inventory</span></a>
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
        <%--Inventory Header --%> 
        <div class="invHeader">
            <h1><b>Inventory</b></h1>
            <h4 style="color: red;">${message}</h4>
        </div>

        <hr class="style1">
        <form method="POST" action="InventoryServlet"> 
        <div class="pullBtn" align="right" > 
            <input type="hidden" name="action" value="pullExpired">
            <input type="submit" class="btn btn-pill px-4 btn-outline-primary" value="Pull Expired Inventory">
        </div>
        </form>
        <%--Ingredient Table List--%> 
        <div class="row">
            <div class="col-6">
                <div class="wrapper">
                    <h2 class="d-inline-block align-middle">Ingredients</h2>
                    <!-- Button to Open the Modal for ingredients -->
                    <button type="button" class="add btn btn-pill px-4 btn-outline-primary" data-toggle="modal" data-target="#myModal">
                        + Add
                    </button>
                </div>

                <!-- The Modal -->
                <div class="modal" id="myModal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Add New Ingredient</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            
                            <form action="InventoryServlet" method="POST">
                                <!-- Modal body -->
                                <div class="modal-body">
                                    <div id="dynamicInput">
                                        <table class="table table-condensed" id="ingredientTest">
                                            <thead><tr>
                                                    <td>Ingredient Name</td>
                                                    <td>Quantity</td>
                                                    <td>Date Produced</td>
                                                </tr></thead>
                                            <tbody><tr>

                                                    <td><input type="text" class="form-control" name="ingredientName[]"></td>
                                                    <td><input type="number" class="form-control" name="quantity[]"></td>
                                                    <td><input type="date" class="form-control" name="dateProduced[]"></td>

                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="addbtn">
                                        <input class="btn  btn-danger" type="button" value="- Delete" onClick='deleteIngredient()' >
                                        <input class="btn  btn-info" type="button" value="+ Add" onClick="addInput('dynamicInput');">
                                    </div>
                                </div>
                                    
                                <!-- Modal footer -->
                                <div class="modal-footer">
                               
                                    <input type="hidden" name="action" value="addIngredient">
                                    <input type="submit" class="btn btn-success" value="Save">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="table-responsive ">
                    <div class="tableFixHead">
                        <table>
                            <thead class="alert-dark">

                                <tr>
                                    <th >Ingredient Name</th>
                                    <th >Station #</th>
                                    <th >Quantity</th>
                                    <th >Date Produced</th>
                                    <th >Expired</th>
                                </tr>
                            </thead>

                            <c:forEach var="ingredient" items="${ingredientList}" varStatus="num">
                                <c:if test="${not empty ingredient.ingredientDateProduced}">
                                    <tr>
        <!-- <td class="btn btn-light">${ingredient.ingredientNumber}</td>-->
                                        <td>${ingredient.ingredientName}</td>
                                        <td>${ingredient.station.stationID}</td>
                                        <td>${ingredient.quantity}</td>
                                        <td>${ingredient.ingredientDateProduced}</td>
                                        <td>${ingredient.expired}</td>

                                    </tr>
                                </c:if>
                            </c:forEach>

                        </table>
                    </div>
                </div>
            </div>
            <%--END Ingredient Table List--%> 
            <%--Meal Table List--%> 
            <div class="col-6">
                <h2 class="d-inline-block align-middle">Meals</h2>

                <button type="button" class="add btn btn-pill px-4 btn-outline-primary" data-toggle="modal" data-target="#modalMeal">
                    + Add
                </button>


                <!-- The Modal -->
                <div class="modal" id="modalMeal">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Add New Meal Items</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <form method="POST" action="InventoryServlet">
                                <!-- Modal body -->
                                <div class="modal-body">

                                    <div id="mealInput">
                                        <table class="table table-condensed" id="mealTest">
                                           
                                            <thead><tr>
                                                    <td>Meal Name</td>
                                                    <td>Quantity</td>
                                                    <td>Date Produced</td>
                                                </tr></thead>
                                            <tbody><tr>

                                                    <td><input type="text" class="form-control" name="mealName[]"></td>
                                                    <td><input type="number" class="form-control" name="mealQuantity[]"></td>
                                                    <td><input type="date" class="form-control" name="mealDate[]"></td>

                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                     <div class="addBtn">
                                                <input class="btn btn-danger" type="button" value="- Delete" onClick='deleteMeal()'>
                                                <input class="btn btn-info" type="button" value="+ Add" onClick="addMealInput('mealInput');">      
                                     </div>
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <input type="hidden" name="action" value="addMeal">
                                    <input type="submit" class="btn btn-success" value="Save">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="table-responsive">
                    <div class="tableFixHead">
                        <table>
                            <thead class="alert-dark">
                                <tr>
                                    <th>Meal #</th>
                                    <th>Meal Name</th>
                                    <th>Quantity</th>
                                    <th>Date Produced</th>
                                    <th>Expired</th>
                                </tr>
                            </thead>
                            <c:forEach var="meal" items="${mealitems}" >
                                <c:if test="${not empty meal.mealDateProduced}">
                                    <tr>
                                        <td>${meal.mealItemNumber}</td>
                                        <td>${meal.mealName}</td>
                                        <td>${meal.quantity}</td>
                                        <td>${meal.mealDateProduced}</td>
                                        <td>${meal.mealExpired}</td>
                                    </tr>
                                </c:if>
                            </c:forEach>


                        </table>
                    </div>
                </div>
            </div>
        </div>
        <br/>
        <br/>
            <center><p>Copyright</p></center>
            <center><p>@2020 Food Inventory Tracker</p></center>
      
        <script src="js/addNewInventory.js"></script>
    </body>
</html>