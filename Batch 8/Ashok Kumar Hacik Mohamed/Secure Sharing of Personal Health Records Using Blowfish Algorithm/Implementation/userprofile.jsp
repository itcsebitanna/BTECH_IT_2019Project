<%-- 
    Document   : ownprofile
    Created on : Mar 23, 2019, 5:24:01 AM
    Author     : nehru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/dbconnect.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <style>
            body{
                
                font-family:Times New Roman;
         
            }
            h2{
               color:red;
            }    
            .underline{
                width:100%;
                height: 10px;
                background-color: lightblue;
            }

            center{
                font-size: 20px;
            }
            table{
                
                width:50%;
            }
            td.topic{
                
                color:blue;
            }
            
         </style>   
    </head>
    <body>
        <h2>Profile</h2>
       <div class="underline"></div>
       <br><br><br>
       <center>
           <table cellpadding="5" cellspacing="0" border="1" >
               <%
                   String doc_id=(String) session.getAttribute("userlogid"); 
                   
                   try{
                        String Query="select * from users where user_id='" + doc_id + "'";
                        Statement stm=Con.createStatement();
                        ResultSet rs=stm.executeQuery(Query);
                        while(rs.next()) 
                        {
                %>
                <tr> <td class="topic"> ID</td><td> <%=rs.getString("user_id")%> </td></tr>
                <tr> <td class="topic"> DOCTOR</td><td> <%=rs.getString("username")%> </td></tr>
                <tr> <td class="topic"> HOSPITAL</td><td> <%=rs.getString("hospital")%> </td></tr>
                <tr> <td class="topic"> E-MAIL</td><td> <%=rs.getString("email")%> </td></tr>
                <tr> <td class="topic"> CONTACT</td><td> <%=rs.getString("mobile")%> </td></tr>
                <tr> <td class="topic"> PIN CODE</td><td> <%=rs.getString("pincode")%> </td></tr>
                    <%        
                        }
                   
                   
                   
                   }catch(Exception ex){
                           ex.printStackTrace();
                           out.println("Errot"+ex.getMessage());
                    
                    }
                   
               %>
           </table>
          
                       
                 
    </body>
</html>
