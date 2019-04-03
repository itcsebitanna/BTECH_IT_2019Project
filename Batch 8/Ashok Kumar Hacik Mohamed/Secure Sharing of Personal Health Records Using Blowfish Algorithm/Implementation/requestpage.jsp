<%-- 
    Document   : requestpage
    Created on : Mar 29, 2019, 7:08:08 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/dbconnect.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        </style>    
    </head>
    <body>
        <h2>Request Decryption Key</h2>
           <div class="underline"></div>
           <br><br><br>
        <center>
        <form action="requestpage.jsp?va=1" method="post">
          
           <table cellspacing="0" cellpadding="5">
          <tr>  
              <td>
                 <label><b>PHR Id</b></label>
              </td>
              <td><br>
                 <input type="text" name="phr_id" placeholder="Enter PHR Id" required=""><br><br>
              </td>
          </tr>
          <tr>
            <td>
              <label><b>Provider Name</b></label>
            </td>  
            <td><br>
                <input type="text" name="provider" placeholder="Enter Provider Name" required=""><br><br>
            </td>
          </tr>
          <tr>
              <td colspan="2"><br>
               <center>
                 <input type="submit" value="Send Request">
               </center>    
              </td>
          </tr>    
            </form>
        </center>    
    
      <%
          String ent=request.getParameter("va");
          if(ent != null){
          
              Statement smt=null;
              ResultSet rs=null;
              ResultSet rs1=null;
       
           
            String phr_id=request.getParameter("phr_id");
            String provider=request.getParameter("provider");
            String doc_name=(String) session.getAttribute("username");
            String str="Request";
            
              try {
                                                smt = Con.createStatement();
                                                rs = smt.executeQuery("select * from phrlog where phr_id='" + phr_id + "' and doctor='" + doc_name + "'");
                                                    
                                                if (!rs.next()) {
                                                        
                                                   out.println("you are not authorized!");
                                                  
                                                    }else{
                                                   
                                                rs1 = smt.executeQuery("select * from userskey_req where phr_id='" + phr_id + "' and action='" + str + "'");
                                                    
                                                if (rs1.next()) {
                                                        
                                                   out.println("you have already Requested the Key!");
                                                  
                                                    }else{
                                                String sql="insert into userskey_req(from_doc,to_pro,phr_id,action) values(?,?,?,?)";
                                                PreparedStatement smt1 = Con.prepareStatement(sql);
                                                smt1.setString(1,doc_name);
                                                smt1.setString(2, provider);
                                                smt1.setString(3, phr_id);
                                                smt1.setString(4, str);
                                            
                                                smt1.executeUpdate();
                                                out.println("Key request sent successfully!!");
                                                }
                                                }
                                               
                                               
                                                    }catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
            
          }
      %>    
    </body>
</html>
