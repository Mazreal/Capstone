<%-- 
    Document   : Meal_Items
    Created on : 4-Feb-2020, 12:13:18 PM
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
        <title>FIT - Meals</title>
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
                        <a class="nav-link" href="#"><span class=" icon-cutlery mr-1 wrap-icon"></span><span>Meal</span></a>
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
         
                <th class="hiddenRow"><div  class="collapse" id="accordion${num.index}">Meal Ingredient</div></th>
                <th class="hiddenRow"><div class="collapse" id="accordion${num.index}">Shelf Life</div></th>
                <th class="hiddenRow"><div class="collapse" id="accordion${num.index}">Weight</div></th>
                
         
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
                    <td class="hiddenRow"><div class="collapse" id="accordion${num.index}">${ingredient.ingredientShelfLife}</div></td>
                    <td class="hiddenRow"><div class="collapse" id="accordion${num.index}">${mealRecipe.ingredientQuantity[stat.index]}</div></td>
                    
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