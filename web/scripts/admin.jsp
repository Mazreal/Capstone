<%-- 
    Document   : admin
    Created on : Mar 23, 2020, 4:41:41 PM
    Author     : 769293
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="css/jquery-ui-1.12.1.custom/jquery-ui.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/admin.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
       <title>Admin - FIT</title>
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
              <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                         <span class="icon-perm_data_setting mr-1 wrap-icon"></span><span>Settings</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                          <a class="dropdown-item" href="login">Logout</a>
              </li>
          </div>
        </nav>
 <%-- End Navigation --%>

    <div class="container" >
        <div class="col-md-12">
                <h2 style="font-size:40px" class="heading-21921">Food Inventory Tracker</h2>
                <p style="font-size:23px" class="lead">Welcome, ${username}!</p>
                <hr class="style1">
        </div>
                <center>
                    <div class="col-sm-8"><h2>Manage <b>Users</b></h2></div> 
                </center>
 <%-- Add User Form --%>
  <c:if test="${selectedUser == null}">
        <script type="text/javascript">
        function myFunction() {
        document.getElementById("addUserForm").style.display = 'block';
        }
         function close() {
        document.getElementById("addUserForm").style.display = 'none';
        }
        </script>
        <div class="addBtn" align="left"> 
        <button onclick="myFunction()" class="btn btn-pill px-4 btn-outline-primary">+ Add User</button>
        </div>
        <div class="fadeInDown" >
        <form action="AdminServlet" method="POST" style="display: none;" id="addUserForm" >
            <div class="fadeIn first">
             <a type="button" class="close fadeOut" href="AdminServlet" aria-label="Close"><span aria-hidden="true">&times;</span></a>
             <h3>Add User</h3>
             </div>
            <input type="hidden" name="userNo" readonly="true" />
           <div class="input-container">
               <i class="fa fa-user icon"></i><input type="text" class="fadeIn second" name="username" placeholder="Username" required=""/>
           </div>
           <div class="input-container">
               <i class="fa fa-key icon"></i><input type="password" class="fadeIn second" name="password" placeholder="Password" required=""/>
           </div>
           <div class="input-container">
             <i class="fa fa-address-card icon"></i>
             <select name="roleID" class="fadeIn second">
                 <option value="A">Admin</option>
                 <option value="W">Warehouse</option>
                 <option value="K">Kitchen</option>
             </select>
           </div>
            <input type="hidden" name="action" value="add"/>
            <div align="right"><input type="submit" class="btn btn-success fadeIn second"  value="Add"/></div>
        </div>
        </form>
            
        </div>
        <br/>
    </c:if>
    <c:if test="${selectedUser != null}">
        <div class="addBtn" align="left"> 
        <button onclick="myFunction()" class="btn btn-pill px-4 btn-outline-primary">+ Add User</button>
        </div>
        
        <form action="AdminServlet" method="POST" id="editUserForm">
            <div class="fadeIn first">
            <h3>Edit User</h3>
            </div>
            <div class="input-container">
                <i class="fa fa-hashtag icon"></i><input type="text" class="fadeIn second input-field" name="userNo" value="${selectedUser.userNo}" readonly="true"><br>
            </div>
            <div class="input-container">
                <i class="fa fa-user icon"></i><input type="text"  name="username" class="fadeIn second" value="${selectedUser.username}">
            </div>
            <div class="input-container">
                <i class="fa fa-key icon"></i><input type="password"  name="password" class="fadeIn second" value="${selectedUser.password}">
            </div>
            <div class="input-container">
             <i class="fa fa-address-card icon"></i>
             <select class="fadeIn second" name="roleID" value="${selectedUser.roleID}">
                 <option value="A">Admin</option>
                 <option value="W">Warehouse</option>
                 <option value="K">Kitchen</option>
             </select>
            </div>
            <input type="hidden" name="action" value="edit">
            <a class="btn btn-danger fadeIn second" href="AdminServlet#" class="fadeIn second" >Cancel</a>
           <input type="submit"  class="btn btn-success fadeIn second" value="Save" id="saveUser">
        </form>
                 <br/>
    </c:if>
                 <center>
           <table id="manageUsers" class="table table-bordered">
        <tr align="center">
            <th style="width: 13%;" >User #</th>
            <th>Username</th>
            <th style="width: 10%;">Role</th>
            <th colspan="2" style="width: 30%;">Action</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td align="center">${user.userNo}</td>

                <td align="center">${user.username}</td>

                <td align="center">${user.roleID}</td>
                <td align="center">
                    <form action="AdminServlet" method="POST">
                     <input type="submit" value="Delete">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="selectedUserNo" value="${user.userNo}">  <br/>
                    </form>
                </td>
                <td align="center">
                    <form action="AdminServlet" method="GET">
                        <input type="submit" value="Edit">
                        <input type="hidden" name="action" value="view">
                        <input type="hidden" name="selectedUserNo" value="${user.userNo}">                        
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
                 </center>
                <br/>
        <br/>
        <br/>
            <center><p>Copyright</p></center>
            <center><p>@2020 Food Inventory Tracker</p></center>
    </body>
    
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/moments.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/bootstrap-datetimepicker.js"></script>
    <script src="js/nouislider.min.js"></script>
</html>
