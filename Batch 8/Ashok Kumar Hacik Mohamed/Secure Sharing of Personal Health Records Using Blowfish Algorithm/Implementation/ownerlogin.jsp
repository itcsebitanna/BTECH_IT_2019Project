<%-- 
    Document   : ownerlogin
    Created on : 19 Mar, 2019, 5:52:08 PM
    Author     : John Andrews
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {font-family: Arial, Helvetica, sans-serif;width:80%;margin:auto}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
  width: 40%;
  padding: 12px 20px;
  margin: 13px 0px;
  margin:auto;
  display: block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}
.sub{
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin-bottom:8px;
  margin-top:14px;
  margin-left:330px;
  border: none;
  border-radius:10px;
  cursor: pointer;
  width: 20%;
}
button:hover {
  opacity: 0.8;
}
<!--.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}-->
.cancelbtn{
background-color: #f44336;
  color: white;
  padding: 5px 18px;
  margin:8px 0;
  border:none;
  cursor:pointer;
}
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}
img.avatar {
  width: 15%;
  <!--border-radius: 80%;-->
}​
.container {
  padding:20px;
  background-color:yellow;
}
span.psw {
  float: right;
  padding-top: 16px;
}
.var{
margin-bottom:8px;
margin-top:8px;
margin-left:252px;
}
.underline{
width:100%;
height:17px;
background-color:#5ca259;
}
.head{
margin:8px 13px;
}
</style>
</head>
<body>
<form name="Login" action="ownerlogin.jsp?va=1" method="post">
<div class=head>
<em style=font-weight:bold;font-size:20px;>PHR Owner Login</em>
<div class=underline></div>
</div>
  <div class="imgcontainer">
   <img src="Images/pic6.png" alt="Avatar" class="avatar">
  </div>
 <div class=container>
   <div class=var><label><b>User Name</b></label></div>
   <input type=text placeholder="Enter Username" name="uname" required>

   <div class=var><label><b>Password</b></label></div>
   <input type=password placeholder="Enter Password" name="psw" required>

   <button type="submit" class=sub>Login</button> 
<br>

   <label>
     <input type="checkbox" checked="checked" name="remember"> Remember me
   </label>
 </div>

  <div class="container" style="background-color:#f1f1f1">
    <!--<button type="button" class="cancelbtn">Cancel</button>-->
    <a href="ownersignup.jsp" class=cancelbtn>Signup</a>
    <span class="psw">Forgot <a href="#">password?</a></span>
  </div>
    <span class="style5">
		<%@include file="include/dbconnect.jsp"%>
                  <%
                               String ent = request.getParameter("va");
                                        if (ent != null) {
                                           Statement smt = null;
                                            ResultSet rs = null;
                             
                                            String UserName = request.getParameter("uname");
                                            String Password = request.getParameter("psw");

                                            try {
                                                smt = Con.createStatement();
                                                rs = smt.executeQuery("select * from owners where username='" + UserName + "' and password='" + Password + "'");
                                                    
                                               out.println("Login successful.");  
                                              if (rs.next()) {
                                                        session.setAttribute("ownerlogid", rs.getString("user_id"));
                                                        session.setAttribute("ownername", rs.getString("username"));
                                                        response.sendRedirect("ownermain.jsp");
                                                       

                                                    } else {
                                                        out.println("UserName Or Password Wrong..");
                                                    }

                                                }

                                            catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                        %>
                  </span>
  </form>
</body>
</html>