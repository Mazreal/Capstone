<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Work+Sans:100,300,400,700,900" rel="stylesheet">
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/nouislider.min.css">
        <link rel="stylesheet" href="css/bootstrap-datetimepicker.css">
        <link rel="stylesheet" href="css/style.css">
        <title>FIT - Home</title>
    </head>
    <body>


        <div id="background" class="carousel-item active img" style="background-image: url(images/white.jpg); background-position: center; height: 570px; width: 100%;"></div>

        <nav class=" px-4 navbar navbar-expand-lg navbar-dark bg-secondary mb-4">
            <img src="images/logo_transparent.png" height="125"><a class="navbar-brand" href="#"></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown4" aria-controls="navbarNavDropdown4" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown4">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#"><span class="icon-home2 mr-1 wrap-icon"></span><span>Home</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="poServlet"><span class=" icon-format_list_bulleted mr-1 wrap-icon"></span><span>Purchase Order</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="mealServlet"><span class=" icon-cutlery mr-1 wrap-icon"></span><span>Meals</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="InventoryServlet"><span class=" icon-format_list_bulleted mr-1 wrap-icon"></span><span>Inventory</span></a>
                    </li>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ManageClientServlet"><span class=" icon-person_outline mr-1 wrap-icon"></span><span>Manage Clients</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="report"><span class="icon-library_books mr-1 wrap-icon"></span><span>Reports</span></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="icon-perm_data_setting mr-1 wrap-icon"></span><span>Settings</span>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">My Account</a>
                            <a class="dropdown-item" href="login">Logout</a>
                    </li>
                        </nav>
        
                        <div class="site-section" >
                            <div class="container" >
                                <div class="row mb-9">
                            <div class="col-md-12">

                                        <h2 style="font-size:40px" class="heading-21921">Food Inventory Tracker</h2>
                                        <p style="font-size:23px" class="lead">Welcome, ${username}!</p>
                                    </div>
                                </div>
                            </div>

                            <center>
                                <div class="row mb-3">
                                    <div class="col">


                                        <a href="InventoryServlet" class="btn btn-info text-white" ><i style="font-size: 60px" class="icon-format_list_bulleted mr-1 wrap-icon"></i>
                                          Inventory</a>

                                        <a class="btn btn-info text-white" href="mealServlet"><i style="font-size:60px" class="icon-cutlery mr-1 wrap-icon"></i>
                                         
                                           Meal</a>

                                        <a class="btn btn-info text-white" href="report"><i style="font-size:60px" class="icon-library_books mr-1 wrap-icon"></i>
                                        
                                           Report</a>

                                    </div>
                                </div>
                                <br>
                                <p>Copyright<br/>
                                 @2020 Food Inventory Tracker</p>
                            </center>                           
</body>
  <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/moments.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/bootstrap-datetimepicker.js"></script>
    <script src="js/nouislider.min.js"></script>
</html>


