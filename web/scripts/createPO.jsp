<%-- 
    Document   : createPO
    Created on : 24-Mar-2020, 2:17:01 PM
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
        <link rel="stylesheet" href="css/addPO.css">
        <link rel="stylesheet" href="css/list_css/list.css">

        <title>FIT - Purchase Order</title>
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
                    <li class="nav-item">
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
            <center>
            <h1><b>Purchase Orders</b></h1>
            </center>
        </div>
        <hr class="style1">


        <div class="d-flex justify-content-center">
            <div class="col-md-9">
                <div class="poForm">
                    <div class="bg-light p-4">
                        <c:if test="${selectedPo == null}">
                            <h3>Add New Purchase Orders</h3>
                            <div>${message}</div>
                            <form method="POST" action="addPO">
                                <div class="row">
                                    <div class="col">
                                        <label for="datetimepicker1">Purchase Order#</label>
                                        <input type="text" class="form-control"  placeholder="1" name="poNo" required/>
                                        <label for="datetimepicker1">Client # (Choose from existing client)</label>
                                        <input type="text" class="form-control" placeholder="1" name="clientNo" required/>
                                        <label for="datetimepicker1">Company Name</label>
                                        <input type="text" class="form-control" placeholder="Company Name" name="empName" required/>
                                        <label for="datetimepicker1">Email</label>
                                        <input type="text" class="form-control" placeholder="user@email.com" name="empEmail" required/>

                                    </div>
                                    <div class="col">
            
                                        <label for="datetimepicker1">Date</label>
                                        <input type="date" class="form-control " name="datePlaced"/>
                                        <label for="datetimepicker1">Date Required</label>
                                        <input type="date" class="form-control " name="dateRequired" />
                                        <label for="datetimepicker1">Notes</label>
                                        <textarea class="form-control" name="notes" placeholder="Additional Comments" ></textarea>
                                    </div>
                                </div> 
                                <hr>

                                <div id="dynamicInput">
                                    <table class="table table-condensed" id="testTable">
                                      <div class="col" align="right">
                                            <input class="btn btn-danger" type="button" value="- Delete" onClick="deleteMealItems()">
                                            <input class="btn  btn-info" type="button" value="+ Add" onClick="addInput('dynamicInput');">
                                        </div>
                                        </br>
                                        <thead><tr>
                                                <td>Meal Items</td>
                                                <td>Quantity</td>
                                            </tr></thead>
                                        <tbody><tr>

                                                <td><input type="text" class="form-control" name="mealItems[]"></td>
                                                <td><input type="number" class="form-control" name="quantity[]"></td>

                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div align="right">
                                <input type="hidden" name="action" value="add">
                                <input type="submit" class="btn btn-success"  value="Save">
                                </div>
                            </form>
                        </c:if>
                        <c:if test="${selectedPo != null}">
                            <h3>Edit Purchase Orders</h3>
                            <div>${message}</div>
                            <form method="POST" action="addPO">
                                <div class="row">
                                    <div class="col">
                                        <label for="datetimepicker1">Purchase Order#</label>
                                        <input type="text" class="form-control"  placeholder="1" name="poNo" value="${selectedPo.poNo}" readonly/>
                                        <label for="datetimepicker1">Client #</label>
                                        <input type="text" class="form-control" placeholder="1" name="clientNo" value="${selectedPo.clientNo}" />
                                        <label for="datetimepicker1">Company Name</label>
                                        <input type="text" class="form-control" placeholder="Company Name" name="empName" value="${selectedPo.empName}" />
                                        <label for="datetimepicker1">Email</label>
                                        <input type="text" class="form-control" placeholder="user@email.com" name="empEmail" value="${selectedPo.empEmail}" />

                                    </div>
                                    <div class="col">
                                        <label for="datetimepicker1">Delivery</label>
                                        <input type="text" class="form-control"  name="delivery" value="${selectedPo.delivery}" />
                                        <label for="datetimepicker1">Completed</label>
                                        <input type="text" class="form-control"  name="completed" value="${selectedPo.completed}" />
                                        <label for="datetimepicker1">Date</label>
                                        <input type="date" class="form-control " name="datePlaced" value="${selectedPo.poDatePlaced}" readonly/>
                                        <label for="datetimepicker1">Date Required</label>
                                        <input type="date" class="form-control " name="dateRequired" value="${selectedPo.poDateRequired}"/>
                                        <label for="datetimepicker1">Notes</label>
                                        <input type="text" class="form-control" name="notes" value="${selectedPo.poNotes}" placeholder="Additional Comments"/>
                                    </div>
                                </div> 
                                <hr>

                                <div id="dynamicInput">
                                    <table class="table table-condensed" id="testTable">
                                        <div class="col" align="right">
                                            <input class="btn btn-danger" type="button" value="- Delete" onClick="deleteMealItems()">
                                            <input class="btn  btn-info" type="button" value="+ Add" onClick="addInput('dynamicInput');">        
                                        </div>
                                        </br>
                                        <thead><tr>
                                                <td>Meal Items</td>
                                                <td>Quantity</td>
                                            </tr></thead>
                                        <tbody>
                                            <c:forEach var="i" begin="0" end="${end}" step="1">
                                                <tr>
                                            <input type="hidden" class="form-control" name="poItemNo" value="${selectedPo.poItemNo[i]}">
                                            <td><input type="text" class="form-control" name="mealItems[]" value="${selectedPo.menuItem[i]}"></td>
                                            <td><input type="text" class="form-control" name="quantity[]" value="${selectedPo.quantity[i]}"></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div align="right">
                                <input type="hidden" name="action" value="edit">
                                <input type="submit" class="btn  btn-success" value="Save Edit">
                                </div>
                            </form>

                        </c:if>
                    </div>
                    
                </div>
            </div>
        </div>
        <br>
        <br>

            <center><p>Copyright</p></center>
            <center><p>@2020 Food Inventory Tracker</p></center>

        <%-- JS --%>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/moments.min.js"></script>
        <script src="js/bootstrap-datetimepicker.js"></script>
        <script src="js/nouislider.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/meal-list.js"></script>
        <script src="js/add.js"></script>
        <script src="js/addPOItems.js"></script>
    </body>
</html>
