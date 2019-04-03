<%-- 
    Document   : dropdown
    Created on : 20 Mar, 2019, 12:41:55 AM
    Author     : John Andrews
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
            .next{
                margin-left: 80%;
                padding:3px 8px;
                border:3px;
                background-color: gray;
                text-decoration: none;
                color:black;
            }
            center{
                font-size: 20px;
            }
        
        </style>
    </head>
    <body>
        <form action="dropdown.jsp?va=1" method="post">
       <h2>Add Patient</h2>
       <div class="underline"></div>
       <br><br><br>
       
       <center>
           <label style="margin-right: 150px">Hospital Name</label>
           <select name="item" style="width:250px;">
               <option value="-1">Select Hospital</option>
                   <%
                    
                    try{
                        String Query="select * from users";
                        Statement stm=Con.createStatement();
                        ResultSet rs=stm.executeQuery(Query);
                        while(rs.next())
                        {
                            %>
                               <option value="<%=rs.getString("hospital")%>"><%=rs.getString("hospital")%></option>
                            <%
                        }
                      }catch(Exception ex){
                           ex.printStackTrace();
                           out.println("Errot"+ex.getMessage());
                    
                    }

                       
                    %>
           </select>
       </center>
           <br><br><br><br><br><br> <input type="submit" value="Save & Continue">
  <span>
      <%
                               String ent = request.getParameter("va");
                                        if (ent != null) {
                     
                             try{
                                 
                                 String Hospname = request.getParameter("item");
                                 String key=request.getParameter("key");
         
                                 if(Hospname != null){
                                     
                                     session.setAttribute("hospital", Hospname);
                                     session.setAttribute("symkey", key );
                                     response.sendRedirect("addpatient.jsp");
           
                                 }else{
                                     out.println("Hospital Value Didn't set!!");
                                 }
                             }catch(Exception e){
                                 
                               System.out.println(e.getMessage());
                                 
                             }}
                                            
                                            
     %>
  </span>
        </form>
    </body>
</html>
