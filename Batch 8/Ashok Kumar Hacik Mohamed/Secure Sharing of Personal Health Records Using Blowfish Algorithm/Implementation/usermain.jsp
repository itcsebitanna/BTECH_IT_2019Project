<%-- 
    Document   : usermain
    Created on : 19 Mar, 2019, 7:29:26 PM
    Author     : John Andrews
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<style>
.sidenav{
  height: 100%;
  width: 200px;
  position:fixed;
  z-index:1;
  top:0;
  left:0; 
  background-color:#5ca259;
  overflow-x:hidden;
  padding-top:20px;
}
<!--ul li{
list-style-type:none;
padding:6px 6px 6px 6px;
}-->
ul{
list-style-type:none;
list-style-position:outside;
padding-left:0pt;
}
ul li{
  padding-top:5px;
}
ul li:hover{
  background-color:#8eb645;
}
ul li a{
text-align:left;
text-decoration:none;
padding:6px 6px 6px 6px;
font-size:18px;
display:block;
}
.sidenav a:hover{
     color:red;
}
.sidenav h2{
  text-align:center;
  }
.main{
  height:500px;
  margin-left:200px;
  <!--border:2px solid red;-->
}
.main p{
 text-indent:50px;
 text-align:justify;
}
#introfreme{
 height:1480px;
 width:100%;
 border:none;
}
</style>
<body>
<div class=sidenav>
<h2>Menu</h2>
  
 <ul>
   <li><a href="userprofile.jsp" target=contents>Profile</a></li>
   <li><a href="searchrec.jsp" target=contents>Pending records</a></li>
   <li><a href="displayrec.jsp" target=contents>Viewed records</a></li>
   <li><a href=requestpage.jsp target=contents>Request Decryption Key</a></li>
   <li><a href=adminlogin.html target=contents>View Clinical Report</a></li>
   <li><a href=userlogout.jsp target="_parent">Logout</a></li>
 </ul>
</div>
<div class=main>
<iframe id=introfreme name="contents" src="abstract.html" ></iframe>
</div>
</body>
</html>