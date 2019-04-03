<%-- 
    Document   : example
    Created on : 21 Mar, 2019, 1:29:02 AM
    Author     : John Andrews
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="include/dbconnect.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registered</title>
    </head>
    <body>
         <span>
     <%
                                   
                                       String ent = request.getParameter("va");
                                       
                                        if (ent != null) {
                                            String skey=request.getParameter("enkey");
                                            String pname = request.getParameter("epname");
                                            String bgroup = request.getParameter("ebgroup");
                                            String hospital = request.getParameter("ehospital");
                                            String doctor = request.getParameter("edoctor");
                                            String disease = request.getParameter("edisease");
                                            String age_string = request.getParameter("eage");
                                            String addr = request.getParameter("eaddr");
                                            String dob = request.getParameter("edob");
                                            String gender = request.getParameter("egender");
                                            String email = request.getParameter("eemail");
                                            String mobile = request.getParameter("emobile");
                                            String desc = request.getParameter("edesc");
                                            String provider=request.getParameter("provide");
                                            
                                          
                                          
                                            
                                          
                                            
                                            
                                          Statement stmt = Con.createStatement();
                                          ResultSet rs = stmt.executeQuery("select max(id) as cnt from phrs");
                                                int Id = 0;
                                                if (rs.next()) {
                                                    Id = rs.getInt("cnt");
                                                }
                                                Id = Id + 1;
                                                String Phr_Id = "PHR" + Id;
                                          
                                                      
                                          try{
                                                String sql="insert into phrs(phr_id,pname,bgroup,hospital,doctor,disease,age,addr,dob,gender,email,mobile,description,provider) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
                                                PreparedStatement smt = Con.prepareStatement(sql);
                                                smt.setString(1,Phr_Id);
                                                smt.setString(2, pname);
                                                smt.setString(3, bgroup);
                                                smt.setString(4, hospital);
                                                smt.setString(5, doctor);
                                                smt.setString(6, disease);
                                                smt.setString(7, age_string);
                                                smt.setString(8, addr);
                                                smt.setString(9, dob);
                                                smt.setString(10, gender);
                                                smt.setString(11, email);
                                                smt.setString(12, mobile);
                                                smt.setString(13, desc);
                                                smt.setString(14, provider);
                                                
                                                smt.executeUpdate();
                                                
                                                
                                                
                                                String query="insert into phrlog(provider,doctor,phr_id,secret_key) values (?,?,?,?)";
                                                PreparedStatement smt1=Con.prepareStatement(query);
                                                
                                                String sessionkey=(String) session.getAttribute("keyStr");
                                               
                                                String doctor_name=(String) session.getAttribute("doc_name");
                                                String provider_name=(String) session.getAttribute("ownername");
                                                  
                                                smt1.setString(1, provider_name);
                                                smt1.setString(2, doctor_name);
                                                smt1.setString(3, Phr_Id);
                                                smt1.setString(4, sessionkey);
                                               
                                                
                                                smt1.executeUpdate();
                                                
                                                out.println("Registered Successfully!!");
                                            }catch(Exception e){
                                            System.out.println(e.getMessage());
                                        }
                                        }
                                     
    %>    
                            
      </span>
    </body>
</html>
