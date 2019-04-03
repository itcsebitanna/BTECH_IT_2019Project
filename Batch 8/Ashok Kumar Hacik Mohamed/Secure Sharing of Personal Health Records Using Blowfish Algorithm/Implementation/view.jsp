<%-- 
    Document   : view
    Created on : 27 Mar, 2019, 12:06:16 AM
    Author     : John Andrews
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/dbconnect.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Record</title>
        <script language="javascript">
        function reQuest(){
           var f=document.form;
            f.method="post";
            f.action="decrypt.jsp?va=1";
            f.submit();
        }
     
        </script>
        
    </head>
    <body>
        <%
            String phr_id=request.getParameter("phr_id");
            String provider=request.getParameter("provider");  
        %>
        <div>
        <form method="post" name="form">
            <input type="hidden" name="provider" value="<%=request.getParameter("provider")%>" >
            <input type="hidden" name="phr_id" value="<%=request.getParameter("phr_id")%>" >
            <input type="hidden" name="usname" value="<%=request.getParameter("usernam")%>" >
            
            <table cellpadding="5" cellspacing="0" border="1">
        <%
        
           
            session.setAttribute("pro",provider);
            try{
               String query="select * from phrs where phr_id='" + phr_id + "'";
               Statement st=Con.createStatement();
               
               
               ResultSet rs=st.executeQuery(query);
               while(rs.next())
               {
         %>
         
         <tr> <td><label>PHR Id</label></td><td><input type="hidden" name="phr_id" value="<%= phr_id %>" ><label><%= phr_id %></label></td></tr>
           <tr> 
               <td><label>Patient Name</label></td>
               <td><input type="hidden" name="pname" value="<%=rs.getString("pname")%>" ><label id="labto"><%=rs.getString("pname")%></label></td>
           </tr>
           <tr> 
               <td><label>Blood Group</label></td>
               <td><input type="hidden" name="bgroup" value="<%=rs.getString("bgroup")%>" ><label><%=rs.getString("bgroup")%></label></td>
           </tr>
           <tr> 
               <td><label>Hospital</label></td>
               <td><input type="hidden" name="hospital" value="<%=rs.getString("hospital")%>" ><label><%=rs.getString("hospital")%></label></td>
           </tr>
           <tr> 
               <td><label>Disease</label></td>
               <td><input type="hidden" name="disease" value="<%=rs.getString("disease")%>" ><label><%=rs.getString("disease")%></label></td>
           </tr>
           <tr> 
               <td><label>Age</label></td>
               <td><input type="hidden" name="age" value="<%=rs.getString("age")%>" ><label><%=rs.getString("age")%></label></td>
           </tr>
           <tr> 
               <td><label>Address</label></td>
               <td><input type="hidden" name="address" value="<%=rs.getString("addr")%>" ><label><%=rs.getString("addr")%></label></td>
           </tr>
           <tr> 
               <td><label>Dob</label></td>
               <td><input type="hidden" name="dob" value="<%=rs.getString("dob")%>" ><label><%=rs.getString("dob")%></label></td>
           </tr>
           <tr> 
               <td><label>Gender</label></td>
               <td><input type="hidden" name="gender" value="<%=rs.getString("gender")%>" ><label><%=rs.getString("gender")%></label></td>
           </tr>
           <tr> 
               <td><label>Email</label></td>
               <td><input type="hidden" name="email" value="<%=rs.getString("email")%>" ><label><%=rs.getString("email")%></label></td>
           </tr>
           <tr> 
               <td><label>Mobile</label></td>
               <td><input type="hidden" name="mobile" value="<%=rs.getString("mobile")%>" ><label><%=rs.getString("mobile")%></label></td>
           </tr>
           <tr> 
               <td><label>Description</label></td>
               <td><input type="hidden" name="desc" value="<%=rs.getString("description")%>" ><label><%=rs.getString("description")%></label></td>
           </tr>
         
           <tr>
               <td><label>Enter Decryption key</label></td>
               <td><input name="key" type="text" placeholder="Enter Key" required></td>
           </tr>>
           <%
               }  
           %>      
           <tr>
               <td colspan="2"> <input type="button" name="request" onclick="reQuest();" value="Decrypt Records">  </td>         
           </tr>
          
           <%
               
              }catch(Exception e)
                 {
                    System.out.println(e.getMessage());
                 }
        %>
           </table>
           
        </form>
           </div>
         
          
  </body>
</html>