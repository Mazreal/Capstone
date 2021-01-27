<!------ Include the above in your HEAD tag ---------->


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" contenSt="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Work+Sans:100,300,400,700,900" rel="stylesheet">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="fonts/icomoon/style.css">

        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/nouislider.min.css">
        <link rel="stylesheet" href="css/bootstrap-datetimepicker.css">
        <link rel="stylesheet" href="css/style.css">
        <link href="css/report_new.css" rel="stylesheet"> 
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>

        <title>
            FIT - Reports
        </title>

    </head>
    <body>
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

                    <li class="nav-item">
                        <a class="nav-link" href="ManageClientServlet"><span class=" icon-person_outline mr-1 wrap-icon"></span><span>Manage Clients</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#"><span class="icon-library_books mr-1 wrap-icon"></span><span>Report</span></a>
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
        <div class="poHeader">
            <h1><b>Report</b></h1>
        </div>
        <hr class="style1"> 
        <br>
        <div class="wrapper">

            <input type="checkbox" id="checkdate" onclick="EnableDisableTextBox(this);"/>
            Looking for specific date?

            <br/>

            <span>Start Date</span>
            <input type="text" class="dateselect" required="required" id="add"/>
            <span> - End Date</span>
            <input type="text" class="dateselect" required="required" id="delete" />

            <div class="dropdown">
                <select id="reporttype">
                    <option value="grablist">Grab List</option>
                    <option value="invoice">Invoice</option>
                    <option value="expiredmeals">Expired Meals</option>
                </select>
            </div>

            <input class="btn btn-pill" type="button" value="Search" onClick="search(this);">

        </div>

        <br>


        <div class="contentBox" >
            <center>
                <form action="report" method="GET">
                    <select name="typeReport" id="reporttype">
                        <option value="grablist">Grab List</option>
                        <option value="invoice">Invoice</option>
                        <option value="expiredmeals">Expired Meals</option>
                        <option value="purchase">Purchase Order</option>
                    </select>
                    <input type="hidden" name="action" value="add">
                   <input type="submit" value="Add New" class="btn btn-outline-primary add-new" class="fa fa-plus">
                </form>
            </center>
            <br>
            <br>
    </div>
        <div class="container">
            <div class="row">
                <div class="col-sm">
            <form action="report" method="POST">
                <input type="hidden" name="action" value="openG">
                <button class="btn btn-info text-white"><span style="font-size:80px" class="icon-format_list_bulleted mr-1 wrap-icon"></span>
                    <br>
                    <br>

                    Grab List</button>

            </form>
                </div>
                <div class="col-sm">
            <form action="report" method="POST">  
                <input type="hidden" name="action" value="openI">
                <button class="btn btn-info text-white"><span style="font-size:80px" class="icon-cutlery mr-1 wrap-icon"></span>
                    <br>
                    <br>
                    Invoice</button>
            </form>
                </div>
                <div class="col-sm">
            <form action="report" method="POST"> 
                <input type="hidden" name="action" value="openE">
                <button  class="btn btn-info text-white"><span style="font-size:80px" class="icon-library_books mr-1 wrap-icon"></span>
                    <br>
                    <br>
                    Expired Meal</button>
            </form>
                </div>
                <div class="col-sm">
            <form action="report" method="POST"> 
                <input type="hidden" name="action" value="openP">
                <button  class="btn btn-info text-white"><span style="font-size:80px" class="icon-format_list_bulleted mr-1 wrap-icon"></span>
                    <br>
                    <br>
                    Purchase Order</button>
            </form>
                </div>
            </div>
            </div>
        <br/>
        <br/>
    <center><p>Copyright</p></center>
    <center><p>@2020 Food Inventory Tracker</p></center>
    <script src="js/report_new.js"></script>
</body>
</html>