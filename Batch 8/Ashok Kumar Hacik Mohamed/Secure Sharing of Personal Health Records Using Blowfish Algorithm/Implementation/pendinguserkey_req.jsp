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
        <h2>Pending Requests</h2>
       <div class="underline"></div>
       <br><br><br>
        <center>
 
           <table cellpadding="5" cellspacing="0" border="1" >
              <tr>
                  <th> S.No</th>
                  <th> From</th>
                  <th> PHR Id</th>
              </tr>    
               <%
                     String uname =(String) session.getAttribute("ownername");
                     
                     String str="Request";
                   try{
                       Statement smt = Con.createStatement();
                       ResultSet rs = smt.executeQuery("SELECT * FROM userskey_req WHERE to_pro='" + uname + "' AND action='" + str + "'");
                        int Id=0;
                 
                     while(rs.next()) 
                        {
                            Id=Id+1;
                  %>
              <form action="share_key.jsp" method="post">
                <tr> 
                    
                    <td><input type="hidden" name="row_no" value="<%= Id %>" >  <%= Id %></td>
                    <td><input type="hidden" name="user" value="<%=rs.getString("from_doc")%>" > <%=rs.getString("from_doc")%> </td>
                    <td><input type="hidden" name="phr_id" value="<%=rs.getString("phr_id")%>" > <%=rs.getString("phr_id")%> </td>
                    <td> <input type="submit" name="nam" value="Provide Key"></td>
   
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
