<%-- 
    Document   : clients
    Created on : Mar 7, 2020, 5:53:29 PM
    Author     : 769293
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="css/manage_clients.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.js"></script>
        <title>FIT - Clients</title>
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
                    <li class="nav-item">
                        <a class="nav-link" href="mealServlet"><span class=" icon-cutlery mr-1 wrap-icon"></span><span>Meal</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="InventoryServlet"><span class=" icon-format_list_bulleted mr-1 wrap-icon"></span><span>Inventory</span></a>
                    </li>

                    <li class="nav-item active">
                        <a class="nav-link" href="#"><span class=" icon-person_outline mr-1 wrap-icon"></span><span>Manage Clients</span></a>
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
            <h1><b>Clients</b></h1>
        </div>
        <hr class="style1">

        <%-- Functionality for the table --%>
        <%-- https://www.tutorialrepublic.com/codelab.php?topic=bootstrap&file=table-with-add-and-delete-row-feature --%>
        <script type="text/javascript">
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
                var actions = $("table td:last-child").html();
                // Append table with add row form on add new button click
                $(".add-new").click(function () {
                    $(this).attr("disabled", "disabled");
                    var index = $("table tbody tr:last-child").index();
                    var row = '<tr>' +
                            '<td><input type="text" class="form-control" name="clientName" id="name" placeholder="Name"></td>' +
                            '<td><input type="text" class="form-control" name="clientEmail" id="email" placeholder="example@ex.com"></td>' +
                            '<td><input type="text" class="form-control" name="clientAddress" id="address" placeholder="Address"></td>' +
                            '<td><input type="text" class="form-control" name="clientCompany" id="company" placeholder="Company Name"></td>' +
                            '<td><input type="text" class="form-control" name="clientPhone" id="phone" pattern="([0-9]{3})[0-9]{3}-[0-9]{4}" placeholder="(123) 456-7890"></td>' +
                            '<td>' + actions + '</td>' +
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
                        $("#huh").append('<form action="ManageClientServlet?action=add" method="POST">');
                        var exist = null;

                        exist = $(this).parents("tr").find("input[name=selectedClient]").val();
                        //alert(exist);
                        $("#huh form").append('<input type="hidden" name="selectedClient" value="' + exist + '">')


                        input.each(function () {

                            var dName = $(this).attr("name");
                            var dValue = $(this).val();

                            $("#huh form").append('<input type="hidden" name="' + dName + '" value="' + dValue + '">')
                            $(this).parent("td").html(dValue + '<input type="hidden" name="' + dName + '" value="' + dValue + '">');
                        });
                        $(this).parents("tr").find(".add, .edit").toggle();
                        $(".add-new").removeAttr("disabled");
                        $("#huh form").submit();
                    }

                });
                // Edit row on edit button click
                $(document).on("click", ".edit", function () {
                    var count = 0;
                    $(this).parents("tr").find("td:not(:last-child)").each(function () {
                        var dName = "";
                        var flag = 0;
                        var exist = null;
                        if (count === 0)
                        {
                            exist = $(this).parents("tr").find("input[name=selectedClient]").val();

                            if (exist != "undefined")
                            {

                                flag = 1;
                            }
                            dName = "clientName";


                        } else if (count === 1)
                        {
                            dName = "clientEmail";

                        } else if (count === 2)
                        {
                            dName = "clientAddress";

                        } else if (count === 3)
                        {
                            dName = "clientCompany";

                        } else if (count === 4)
                        {
                            dName = "clientPhone";

                        }
                        if (count === 0)
                        {

                            if (flag === 1)
                            {
                                $(this).html('<input type="hidden" name="selectedClient" value="' + exist + '"><input type="text" name="' + dName + '" class="form-control" value="' + $(this).text() + '">');

                            } else
                            {
                                $(this).html('<input type="text" name="' + dName + '" class="form-control" value="' + $(this).text() + '">');

                            }
                        } else
                        {
                            $(this).html('<input type="text" name="' + dName + '" class="form-control" value="' + $(this).text() + '">');
                        }

                        count++;

                    });
                    $(this).parents("tr").find(".add, .edit").toggle();
                    $(".add-new").attr("disabled", "disabled");
                });
                // Delete row on delete button click
                $(document).on("click", ".delete", function () {
                    var dValue = $(this).parents("tr").find("input[name=selectedClient]").val();
                    $("#huh").append('<form action="ManageClientServlet?action=delete" method="POST">');

                    $("#huh form").append('<input type="hidden" name="selectedClient" value="' + dValue + '">')
                    $(this).parents("tr").remove();
                    $(".add-new").removeAttr("disabled");
                    $("#huh form").submit();
                });
            });
        </script>
        <%-- Table --%>
        <div id="huh"></div>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-8"><h2>Client <b>Details</b></h2></div>
                        <div class="col-sm-4">
                            <button type="button" class="btn btn-outline-primary add-new"><i class="fa fa-plus"></i> Add New</button>                                
                        </div>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead class="alert-dark">
                        <tr>
                            <th style="width: 15%">Name</th>
                            <th style="width: 25%">E-mail</th>
                            <th style="width: 20%">Address</th>
                            <th>Company</th>
                            <th>Contact Number</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="client" items="${clientList}">

                            <tr>

                                <td><input type="hidden" name="selectedClient" value="${client.clientNo}">
                                    <input type="hidden" name="clientName" value="${client.clientName}">${client.clientName}</td>
                                <td><input type="hidden" name="clientEmail" value="${client.clientEmail}">${client.clientEmail}</td>
                                <td><input type="hidden" name="clientAddress" value="${client.clientAddress}">${client.clientAddress}</td>
                                <td><input type="hidden" name="clientCompany" value="${client.clientCompany}">${client.clientCompany}</td>
                                <td><input type="hidden" name="clientPhone" value="${client.clientPhone}">${client.clientPhone}</td>
                                <td>
                                    <a class="add" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a>
                                    <a class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                    <a class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                                </td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div> 
<br/>
        <br>

    <center><p>Copyright</p></center>
    <center><p>@2020 Food Inventory Tracker</p></center>

        <%-- END --%>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/moments.min.js"></script>
        <script src="js/bootstrap-datetimepicker.js"></script>
        <script src="js/nouislider.min.js"></script>
    </body>
</html>