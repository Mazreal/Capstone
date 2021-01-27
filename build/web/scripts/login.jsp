<html>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/loginCss.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <script src="scripts/jquery-3.3.1.min.js"></script>
    <script src="scripts/popper.min.js"></script>
    <script src="scripts/bootstrap.min.js"></script>
    <script src="scripts/moments.min.js"></script>
    <script src="scripts/bootstrap-datetimepicker.js"></script>
    <script src="scripts/nouislider.min.js"></script>
    <title>Login</title>
 
<body>

    <div class="wrapper fadeInDown">

        <div id="formContent">

            <!-- Tabs Titles -->
            <h2 class="active"> Welcome to FIT </h2>

            <!-- Icon -->
            <div class="fadeIn first">
                <img src="images/logo_transparent.png" id="icon" alt="User Icon" />
            </div>

            <!-- Login Form -->

            <form action="login" method="POST">
                <input type="text" id="login" class="fadeIn second" name="username" placeholder="username">
                <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
                <br/>
                <br/>
                <input type="submit" class="fadeIn fourth" id="submit" value="Submit" href="main.jsp"/>                   
                <p style="color: red;"> ${errorMessage}</p>
            </form>

            <!-- Remind Passowrd -->
            <div id="formFooter">
                <a class="underlineHover" onclick="myFunction()" id= "forgetpassword" >Forgot Password?</a>
            </div>
            <script src="text/javascript">
       function myFunction()
       {
           alert("Please contact your administrator.");
       }
    </script>
        </div>
            
    </div>
    
</body>
   
</html>