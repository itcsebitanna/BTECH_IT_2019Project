<%-- 
    Document   : search
    Created on : Mar 23, 2019, 7:42:15 AM
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
            th{
                
                color:blue;
            }
      </style>
    </head>
    <body>
        <h2>New Arrivals</h2>
       <div class="underline"></div>
       <br><br><br>
        <center>
 
           <table cellpadding="5" cellspacing="0" border="1" >
              <tr>
                  <th> S.No</th>
                  <th> Provider</th>
                  <th> PHR Id</th>
              </tr>    
               <%
                     String uname =(String) session.getAttribute("username");
                     
                   try{
                       Statement smt = Con.createStatement();
                       ResultSet rs = smt.executeQuery("SELECT * FROM viewed WHERE doctor='" + uname + "'");
                        int Id=0;
                    
                       
                        while(rs.next()) 
                        {
                            Id=Id+1;
                %>
                <form action="justview.jsp" method="post">
                  <tr> 
                  <input type="hidden" name="usernam" value="<%=session.getAttribute("username")%>" >
                    <td><input type="hidden" name="row_no" value="<%= Id %>" >  <%= Id %></td>
                    <td><input type="hidden" name="provider" value="<%=rs.getString("provider")%>" > <%=rs.getString("provider")%> </td>
                    <td><input type="hidden" name="phr_id" value="<%=rs.getString("phr_id")%>" > <%=rs.getString("phr_id")%> </td>
                    <td> <input type="submit" name="name" value="View"></td>
                  </tr>
              </form>
                    <%        
                        
                      }

                   
                   }catch(Exception ex){
                           ex.printStackTrace();
                           out.println("Error"+ex.getMessage());
                    
                    }
                   
               %>
           </table>
    
         </center>
    </body>
</html>
