<%-- 
    Document   : share_key
    Created on : 27 Mar, 2019, 10:50:33 AM
    Author     : John Andrews
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/dbconnect.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
          String doc=request.getParameter("user");
          String phr=request.getParameter("phr_id");
          
    try{               
                       String key = null;
                       String email = null;
                       String str="Request";
                       String str1="Granted";
                       Statement smt = Con.createStatement();
                       Statement smt1 = Con.createStatement();
                       ResultSet rs = smt.executeQuery("select secret_key from phrlog where doctor ='" + doc + "' and phr_id='" + phr + "'");
                       
                       if(rs.next()){
                           
                           key=rs.getString("secret_key");    
                       out.println("Secret Key is : "+ key);
                       }
                       
                       ResultSet rs1 = smt.executeQuery("select email from users where username ='" + doc + "'");
                       
                       if(rs1.next()){
                           
                           email=rs1.getString("email");    
                       out.println("E-mail Id is : "+ email);
                       }
                       String sub="SeSPHR:Decryption Key";
                       String msg="The Decryption Key for "+phr+" is-"+key;
	   
                       str=SeSPHR.SendMailTLS.encrypt(email,sub,msg); 
                       out.println(str);
                   
                                   
                
          }catch(Exception e){
                           
                          System.out.println(e.getMessage());
                      
                       }
        %>
    </body>
</html>
