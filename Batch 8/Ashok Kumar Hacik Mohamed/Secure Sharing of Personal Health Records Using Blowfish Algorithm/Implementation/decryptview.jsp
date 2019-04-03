<%-- 
    Document   : enctest
    Created on : 21 Mar, 2019, 1:11:16 PM
    Author     : John Andrews
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/dbconnect.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>   
            .sidenav{
                height: 650px;
                width: 100%;
                margin: auto;
                position:fixed;
                z-index:1;
                top:0;
                left:0; 
                overflow-x:hidden;
                margin-top: 0;
               
               }
            body{
                 font-family:Times New Roman;
                background-color:lightgray;
            }
            
            .form1{
                margin-top: 0;
            } 
         table.two{
                width: 400px;
                border: 2px solid;
                margin-top: 0;
               
            }
            td{
                width: 200px;
                padding-left: 20px;
            }
         
          
            h2{
               color:red;
            }    
            .underline{
                width:100%;
                height: 10px;
                background-color: lightblue;
            }
      
        </style>   
       
    </head>
    <body>
          <h2>Viewing PHR</h2>
         <div class="underline"></div>
         <br><br><br>
        
               <%
                    String ent = request.getParameter("va");
                    
                       String pname=null;
                       String bgroup=null;
                       String hospital=null;
                       String doctor=null;
                       String disease=null;
                       String age=null;
                       String address=null;
                       String dob=null;
                       String gender=null;
                       String email=null;
                       String mobile=null;
                       String description=null;
                       String provider=null;
                       
                       String phr_id=request.getParameter("phr_id");
                        String s_key=request.getParameter("key");
                
                        
                    if(ent != null){ 
                        
                        Statement smt = null;
                        ResultSet rs = null;
                        
                        try 
                      {
                     smt = Con.createStatement();
                     rs = smt.executeQuery("select * from phrs where phr_id='" + phr_id + "'");
                    
                     
                     while (rs.next()) {
                         
                           pname=rs.getString("pname");
                           bgroup=rs.getString("bgroup");
                           hospital=rs.getString("hospital");
                           doctor=rs.getString("doctor");
                           disease=rs.getString("disease");
                           age=rs.getString("age");
                           address=rs.getString("addr");
                           dob=rs.getString("dob");
                           gender=rs.getString("gender");
                           email=rs.getString("email");
                           mobile=rs.getString("mobile");
                           description=rs.getString("description");
                           provider=rs.getString("provider");
                           session.setAttribute("provider",provider);
                              
         
               } 
                              
               %>
         <center>
         <form name="crypt" class="form1"  action="decrypt.jsp?ta=1" method="post">
         <div class="sidenav">
         <table class="two" cellpadding="5" cellspacing="0">
         <tr>
         <td><b><label class="one">PHR Id </label></b></td>
         <td><b><input disabled="disabled" type="text" value="<%= phr_id %>" name="phr_id"></b></td><br><br><br>
        </tr>
         <tr>
             <td><b><label class="one">Name </label></b></td>
             <td><b><input disabled="disabled" type="text" value="<%=SeSPHR.JBlowfish.decrypt(s_key ,pname)%>" name="dpname"></b></td>
         </tr>
         <tr>
             <td><b><label class="one">Blood Group </label></b></td>
             <td><b><input disabled="disabled" type="text" value="<%=SeSPHR.JBlowfish.decrypt(s_key,bgroup)%>" name="dbgroup"></b></td>
         </tr>
         <tr>
             <td><b><label class="one">Hospital </label></b></td>
             <td><b><input disabled="disabled" type="text" value="<%=SeSPHR.JBlowfish.decrypt(s_key,hospital)%>" name="dhospital"></b></td>
         </tr>
         <tr>
             <td><b><label class="one">Doctor </label></b></td>
             <td><b><input disabled="disabled" type="text" value="<%=SeSPHR.JBlowfish.decrypt(s_key,doctor)%>" name="ddoctor"></b></td>
         </tr>
         <tr>
             <td><b><label class="one">Disease </label></b></td>
             <td><b><input disabled="disabled" type="text" value="<%=SeSPHR.JBlowfish.decrypt(s_key,disease)%>" name="ddisease"></b></td>
         </tr>
         <tr>
             <td><b><label class="one">Age </label></b></td>
             <td><b><input disabled="disabled" type="text" value="<%=SeSPHR.JBlowfish.decrypt(s_key,age)%>" name="dage"></b></td>
         </tr>
         <tr>
             <td><b><label class="one">Address</label></b></td>
             <td><b><input disabled="disabled" type="text" value="<%=SeSPHR.JBlowfish.decrypt(s_key,address)%>" name="daddr"></b></td>
         </tr>
         <tr>
             <td><b><label class="one">Dob </label></b></td>
             <td><b><input disabled="disabled" type="text" value="<%=SeSPHR.JBlowfish.decrypt(s_key,dob)%>" name="ddob"></b></td>
         </tr>
         <tr>
             <td><b><label class="one">Gender </label></b></td>
             <td><b><input disabled="disabled" type="text" value="<%=SeSPHR.JBlowfish.decrypt(s_key,gender)%>" name="dgender"></b></td>
         </tr>
         <tr>
             <td><b><label class="one">Email </label></b></td>
             <td><b><input disabled="disabled" type="text" value="<%=SeSPHR.JBlowfish.decrypt(s_key,email)%>" name="demail"></b><td>
         </tr>        
         <tr>
             <td><b><label class="one">Mobile </label></b></td>
             <td><b><input disabled="disabled" type="text" value="<%=SeSPHR.JBlowfish.decrypt(s_key,mobile)%>" name="dmobile"></b></td>
         </tr>
         <tr>    
             <td><b><label class="one">Description </label></b></td>
             <td><b><textarea disabled="disabled" style="height:40px" maxlength="150" value="<%=SeSPHR.JBlowfish.decrypt(s_key,description)%>" name="ddesc" cols="21"><%=SeSPHR.JBlowfish.decrypt(s_key,description)%></textarea></b></td>
        </tr>
        <tr>
         <td><b><label class="one">Provider </label></b></td>
         <td><b><input disabled="disabled" type="text" value="<%= provider %>" name="dprovide"></b></td><br><br><br>
        </tr>
         </table>
         <br><br><br> 
        
       <%  
         }catch(Exception e){
                          
                        System.out.println(e.getMessage());
                     
                      }
}
               
       %>
 
        
         </div>
         </form>
                </center> 
    </body>
</html>
 