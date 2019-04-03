<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="include/dbconnect.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href=signcss.css  rel=stylesheet type=text/css>
<style>
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
   <label for="email"><b>Blood group</b></label>
    <input type="text"  placeholder="Enter Blood group"  name="bgroup" required>
   <label for="hosp"><b>Hospital</b></label>
    <input type="text"  value="<%=session.getAttribute("hospital") %>" name="hospital"  required>
    <label for="dob"><b>Select doctor</b></label>
    <!--<input type="text"  placeholder="Enter doctor"  name="doctor" required>-->
    <select name="item" style="width:250px;">
        <option value="-1">Select Doctor</option>
                   <%
                       Statement stm = null;
                       ResultSet rs = null;
           
                  
                        String sessionName=(String) session.getAttribute("hospital");
                                                                   
                    
                    try{
                       

                      stm= Con.createStatement();
                      rs = stm.executeQuery("select username from users where hospital='" + sessionName + "'");
                      
                        while(rs.next())
                        {
                            %>
                               <option value="<%=rs.getString("username")%>"><%=rs.getString("username")%></option>
                            <%
                        }
                      }catch(Exception ex){
                           ex.printStackTrace();
                           out.println("Errot"+ex.getMessage());
                    
                    }
                                           
                    %>
           </select>
  <br><br><br> <label for="mobile"><b>Disease</b></label>
   <input type="text"  placeholder="Enter Disease"  name="disease" required>
   <label for="age"><b>Age</b></label>
    <input type="text"  placeholder="Enter Age"  name="age" required>
   <label for="addr"><b>Patient Address</b></label>
    <input type="text"  placeholder="Enter Address"  name="address" required>  
    <label for="dob"><b>Date of Birth</b></label>
    <input type="text"  placeholder="Enter Date of  Birth"  name="dob" required>  
    <label for="gender"><b>Gender</b></label>
    <input type="text"  placeholder="Enter gender"  name="gender" required>  
    <label for="email"><b>Email</b></label>
    <input type="text"  placeholder="Enter Email"  name="email" required>
    <label for="mobile"><b>Mobile</b></label>
    <input type="text"  placeholder="Enter Mobile Number"  name="mobile" required> 
    <label for="mobile"><b>Patient Description</b></label>
    <input type="text"  placeholder="Provide description"  name="desc" required> <hr>
    <label for="text"><b>Encryption Key<b></label>
    <input type="text" placeholder="Enter Symmetric Key" name="key" required><hr>
    
    
    <input name="submit" type="submit"  value="Register" />
  </div> 
</form>
              
</body>
    
</html>