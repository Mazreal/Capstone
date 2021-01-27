<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Work+Sans:100,300,400,700,900" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/nouislider.min.css">
        <link rel="stylesheet" href="css/bootstrap-datetimepicker.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/list_css/list.css">
        <link rel="stylesheet" href="css/pop-up_add.css">
        <title>FIT - Meals</title>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.js"></script>
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
                    <li class="nav-item active">
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
        <%-- End Navigation --%>
        <div class="itemHeader">
            <h1><b>Meals</b></h1>
        </div>
        <hr class="style1">
        <%-- Add Meal Item Button --%>
        <div class="addBtn" align="right"> 
            <a href="MealList" class="btn btn-pill px-4 btn-outline-primary">+ Add Meal Item</a>
        </div>

        <%-- Modal --%>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel" align="left">Add Meal Item</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    </div>
                    <div class="modal-body" >
                        <div class="row">
                            <div class="col-lg">
                                <div class="bg-light p-4 mb-4">
                                    <div class="form-group">
                                        <%---- Add Ingredient ----%>   
                                        <form id="testForm" action="MealList" method="POST" > 
                                            <div>
                                                <label for="datetimepicker1">Name</label>
                                                <input type="text" name="mealName" class="form-control" placeholder="Meal name" value="${meal}"/>
                                            </div><br/>
                                             <div>
                                                <input type="hidden" name="mealQuantity" class="form-control" id="validationDefault02">
                                            </div><br/>
                                            <%--https://www.tutorialrepublic.com/codelab.php?topic=bootstrap&file=table-with-add-and-delete-row-feature--%>
                                            <script type="text/javascript">
                                                $(document).ready(function () {
                                                    $('[data-toggle="tooltip"]').tooltip();
                                                    var actions = $("table td:last-child").html();
                                                    // Append table with add row form on add new button click
                                                    $(".add-new").click(function () {
                                                        $(this).attr("disabled", "disabled");
                                                        var index = $("table tbody tr:last-child").index();
                                                        var row = '<tr align="center">' +
                                                                '<td><input type="text" class="form-control" name="ingredientName" id="ingredientName"></td>' +
                                                                '<td><input type="text" class="form-control" name="weight" id="weight"></td>' +
                                                                '<td><input type="text" class="form-control" name="par" id="par"></td>' +
                                                                '<td><input type="text" class="form-control" name="shelfLife" id="shelfLife"></td>' +
                                                                '<td><input type="text" class="form-control" name="stationNo" id="stationNo"></td>' +
                                                                '<td>' + actions +
                                                                '</tr>';
                                                        $("table").append(row);
                                                        $("table tbody tr").eq(index + 1).find(".add, .edit").toggle();
                                                        $('[data-toggle="tooltip"]').tooltip();
                                                    });
                                                    // Add row on add button click
                                                    $(document).on("click", ".add", function () {
                                                        var empty = false;
                                                        var input = $(this).parents("tr").find('input[type="text"]');
                                                        input.each(function () {
                                                            if (!$(this).val()) {
                                                                $(this).addClass("error");
                                                                empty = true;
                                                            } else {
                                                                $(this).removeClass("error");
                                                            }
                                                        });
                                                        $(this).parents("tr").find(".error").first().focus();

                                                        if (!empty) {
                                                            input.each(function () {
                                                                var dName = $(this).attr("name");
                                                                var dValue = $(this).val();

                                                                $(this).parent("td").html(dValue + '<input type="hidden" name="' + dName + '" value="' + dValue + '">');

                                                            });

                                                            $(this).parents("tr").find(".add, .edit").toggle();
                                                            $(".add-new").removeAttr("disabled");
                                                        }
                                                    });
                                                    // Edit row on edit button click
                                                    $(document).on("click", ".edit", function () {
                                                        var count = 0;
                                                        $(this).parents("tr").find("td:not(:last-child)").each(function () {
                                                            var dName = "dd";
                                                            if (count === 0)
                                                            {
                                                                dName = "ingredientName";

                                                            } else if (count === 1)
                                                            {
                                                                dName = "weight";

                                                            } else if (count === 2)
                                                            {
                                                                dName = "par";

                                                            } else if (count === 3)
                                                            {
                                                                dName = "shelfLife";

                                                            } else if (count === 4)
                                                            {
                                                                dName = "stationNo";

                                                            }
                                                            $(this).html('<input type="text" name="' + dName + '"class="form-control" value="' + $(this).text() + '">');
                                                            
                                                        });
                                                        $(this).parents("tr").find(".add, .edit").toggle();
                                                        $(".add-new").attr("disabled", "disabled");
                                                    });
                                                    // Delete row on delete button click
                                                    $(document).on("click", ".delete", function () {
                                                        $(this).parents("tr").remove();
                                                        $(".add-new").removeAttr("disabled");
                                                    });
                                                });
                                            </script>
                                            <div class="container">
                                                <div class="table-wrapper" id="popup_wrapper">
                                                    <div class="table-title" id="popup_table">
                                                        <div class="row">
                                                            <div class="col-sm-8">
                                                                <label for="datetimepicker1">Ingredient Name</label></div>
                                                            <div class="col-sm-4">
                                                                <button type="button" class="btn btn-info add-new"><i class="fa fa-plus"></i>Add</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <table class="table table-bordered" id="pop_table">
                                                        <thead>
                                                            <tr align="center">
                                                                <th> <label for="datetimepicker1">Ingredient Name</label></th>
                                                                <th style="width: 15%"> <label for="datetimepicker1">Weight (kg/ml)</label></th>
                                                                <th style="width: 20%"> <label for="datetimepicker1">PAR</label></th>
                                                                <th style="width: 15%"> <label for="datetimepicker1">Shelf Life (days)</label></th>
                                                                <th style="width: 10%"> <label for="datetimepicker1">Station #</label></th>
                                                                <th> <label for="datetimepicker1">Actions</label></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>

                                                            <tr align="center">
                                   
                                                                <td style="display: none">
                                                                    <a class="add" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a>
                                                                    <a class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                                                    <a class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                                                                </td>
                                                            </tr>      
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="modal-footer">

                                                <button type="button" data-dismiss="modal" class="btn btn-danger">Cancel</button>

                                                <input type="hidden" name="action" value="save"/>
                                                <input type="submit" value="Add" class="btn btn-success"/>

                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <%-- End Modal --%>                   
    <%-- Meal Item List --%>
        <div class="row d-flex justify-content-center"> <%-- Center table --%>
            <div class="just-padding col-9"> <%-- how long the table looks --%>

                <table class="table table-condensed" style="border-collapse:collapse" id="mealTable">
                    <thead>

                        <tr>
                            <th>Meal Item #</th>
                            <th>Meal Name</th>
                            <th>Shelf Life</th>
                        </tr>
                        <c:forEach var="meal" items="${mealitems}" varStatus="num">
                            <tr data-toggle="collapse" data-target="#accordion${num.index}" style="color: #0249bf;">

                                <td>${meal.mealItemNumber}</td>
                                <td>${meal.mealName}</td>
                                <td>${meal.mealShelfLife}</td>
                                <td class="hiddenRow"><div class="collapse" id="accordion${num.index}"><a href="MealEdit?selectedMeal=${meal.mealItemNumber}" class="icon icon-pencil"></a></div></td>
                            </tr>
                        </thead>
                        <thead>
                            <tr>

                                <th class="hiddenRow"><div class="collapse" id="accordion${num.index}">Meal Ingredient</div></th>
                                <th class="hiddenRow"><div class="collapse" id="accordion${num.index}">Weight (kg)</div></th>
                                <th class="hiddenRow"><div class="collapse" id="accordion${num.index}">PAR</div></th>
                                <th class="hiddenRow"><div class="collapse" id="accordion${num.index}">Shelf Life (days)</div></th>
                                <th class="hiddenRow"><div class="collapse" id="accordion${num.index}">Station #</div></th>


                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <%---- INGREDIENT BELOW ----%>
                                <c:forEach var="mealRecipe" items="${MealRecipe}" varStatus="status">
                                    <c:set var="mealNo" value="${meal.mealItemNumber}"/> 
                                    <c:set var="recipeNo" value="${mealRecipe.mealItemNumber}"/> 
                                    <c:if test="${mealNo == recipeNo}">
                                        <c:forEach var="ingredient" items="${mealRecipe.ingredientList}" varStatus="stat">

                                            <td class="hiddenRow"><div class="collapse" id="accordion${num.index}">${ingredient.ingredientName}</div></td>
                                            <td class="hiddenRow"><div class="collapse" id="accordion${num.index}">${mealRecipe.ingredientQuantity[stat.index]}</div></td>
                                            <td class="hiddenRow"><div class="collapse" id="accordion${num.index}">${ingredient.par}</div></td>
                                            <td class="hiddenRow"><div class="collapse" id="accordion${num.index}">${ingredient.ingredientShelfLife}</div></td>
                                            <td class="hiddenRow"><div class="collapse" id="accordion${num.index}">${ingredient.station.stationID}</div></td>


                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </tbody>
                        <%---- INGREDIENT ABOVE ----%>
                    </c:forEach>
                </table>
                <br>
                <br>

            <center><p>Copyright</p></center>
            <center><p>@2020 Food Inventory Tracker</p></center>

                
                <%-- End --%>
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

                </body>
                </html>