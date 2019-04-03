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
    </head>
    <body>
                                         <form name="ensure" action="example.jsp?va=1" method="post">
                                             <h1> Encrypted Details</h1>
                                             <%
                                               String sessionKey=request.getParameter("key");
                                               String doctor=request.getParameter("item");
                                               session.setAttribute("doc_name", doctor);
                                              
                                               
                                          
                                               Statement stm = null;
                                               ResultSet rs = null;
           
                  
                                               String sessionName=(String) session.getAttribute("hospital");
                                                                   
                    
                                              try{
                       

                                                 stm= Con.createStatement();
                                                 rs = stm.executeQuery("select user_id from users where username='" + doctor + "'");
                      
                                                 while(rs.next())
                                           {
                                          
                                               session.setAttribute("doc_id",rs.getString("user_id"));
                                          
                                           }
                                            }catch(Exception ex){
                                               ex.printStackTrace();
                                               out.println("Error"+ex.getMessage());
                    
                                              }
  
                                               session.setAttribute("keys",sessionKey);
                                               
                                               out.println(sessionKey);
                                              %>
                                              Symmetric Key : <input type="text" value="<%=request.getParameter("key")%>" name="skey"><br><br>
                                              <%
                                                  String seKey=request.getParameter("key");
                                                  String kkey=request.getParameter("kkey");
                                                  session.setAttribute("keyStr",seKey);
                                                
                                              %>
                                              Name :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("name"))%>" name="epname"><br><br>
                                              Blood group :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("bgroup"))%>" name="ebgroup"><br><br>
                                              Hospital :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("hospital"))%>" name="ehospital"><br><br>
                                              Doctor :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("item"))%>" name="edoctor"><br><br>
                                              Disease :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("disease"))%>" name="edisease"><br><br>
                                              Age :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("age"))%>" name="eage"><br><br>
                                              Address :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("address"))%>" name="eaddr"><br><br>
                                              Dob :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("dob"))%>" name="edob"><br><br>
                                              Gender :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("gender"))%>" name="egender"><br><br>
                                              Email :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("email"))%>" name="eemail"><br><br>
                                              Mobile :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("mobile"))%>" name="emobile"><br><br>
                                              Description :<input type="text" value="<%=SeSPHR.JBlowfish.encrypt(sessionKey,request.getParameter("desc"))%>" name="edesc"><br><br>
                                        
                                              <input type="hidden" name="provide" value="<%=session.getAttribute("ownername")%>">                            
                                              <input type="submit" value="Submit"> 
       </form>
    </body>
</html>
 