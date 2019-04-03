<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="include/dbconnect.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href=signcss.css  rel=stylesheet type=text/css>
<style>
    body{
        width:70%;
        margin: auto;
    }
    .underline{
                width:100%;
                height: 10px;
                background-color: lightblue;
            }
    .hor{
       text-align: right;
       color: red;
    }
</style>
</head>
<body>
<form  name="Register" action="enctest.jsp?va=1" method="post">
  <div class="container">
    <h1>Add Patient</h1>
     <div class="underline"></div>
       <hr>
      
       
   
       
   <label for="name"><b>Patient Name</b></label>
    <input type="text"  placeholder="Enter Patient Name"  name="name" required>
    <label for="hosp"><b>Hospital</b></label>
    <input type="text" name="hospital"  required>
    <label for="dob"><b>Select doctor</b></label>
    <input type="text" name="doctor"  required>
    <label for="mobile"><b>Disease</b></label>
   <input type="text"  placeholder="Enter Disease"  name="disease" required>
   <label for="gender"><b>Gender</b></label>
   <input type="text"  placeholder="Enter gender"  name="gender" required>  
   <label for="email"><b>Email</b></label>
   <input type="text"  placeholder="Enter Email"  name="email" required>
   <label for="mobile"><b>Patient Description</b></label>
   <input type="text"  placeholder="Provide description"  name="desc" required> <hr>
   <label for="text"><b>Encryption Key<b></label>
   <input type="text" placeholder="Enter Symmetric Key" name="key" required><hr>
    
    
    <input name="submit" type="submit"  value="Register" />
  </div> 
</form>
              
</body>
    
</html>