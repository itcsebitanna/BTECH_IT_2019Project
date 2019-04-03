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
        <title >JSP Page</title>
        <style>   
            .sidenav{
                height: 650px;
                width: 450px;
                position:fixed;
                z-index:1;
                top:0;
                left:0; 
                overflow-x:hidden;
                padding-top:20px;
            }
            body{
                background-color:lightgray;
            }
            
            .main{
               height:500px;
               margin-left:450px;
               padding-top:20px;
               <!--border:2px solid red;-->
         }
         
        
        .hidden{
                
                display: none;
                
            }
            table.two{
                width: 400px;
                border: 2px solid;
            }
            td{
                width: 200px;
                padding-left: 20px;
            }
            
        </style>   
        <script type="text/javascript">
               
               function showhide(){
                   
                   var checkbox=document.getElementById("chk");
                   var hiddeninputs=document.getElementsByClassName("hidden");
                   
                   for(var i=0;i!==hiddeninputs.length;i++){
                       
                       if(checkbox.checked){
                           
                           hiddeninputs[i].style.display="block";
                       }
                   }
                   var pro=document.getElementsByName("dprovide");
                   document.getElementsByName("provide").value=pro.value;
               }
               
        </script>
    </head>
    <body>
        
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
         
         <form name="crypt" class="form1"  action="decrypt.jsp?ta=1" method="post">
         <div class="sidenav">
         <table name="two" cellpadding="5" cellspacing="0">
         <tr>
         <td><b><label class="one">PHR Id </label></b></td>
         <td><b><input type="text" type="text" value="<%= phr_id %>" name="phr_id"></b></td><br><br><br>
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
         <input type="checkbox" name="chkbox" id="chk" onclick="showhide()"/>&nbsp;&nbsp;&nbsp;&nbsp;
         <label for="chk">Click to Provide Report</label>
       <%  
         }catch(Exception e){
                          
                        System.out.println(e.getMessage());
                     
                      }
}
               
       %>
 
        
         </div>
         <div class="main">
            
          <table class="hidden"  cellpadding="5" cellspacing="0">     
                    <tr>
              <td>  
        
                <label class="hidden">Clinical Report</label>
              </td>  
              <td><br><br>
                  <textarea style="height:80px" maxlength="300" class="hidden" name="report" placeholder="Provide Report"></textarea><br><br>
              </td>
            </tr>  
            <tr>
              <td>  
                  <label class="hidden">Symmetric key</label>
              </td>    
              <td><br><br>
                  <input type="text" name="key" class="hidden" placeholder="Enter Key to Encrypt Report"><br><br>
              </td>
            </tr>  
            <tr>
                <td colspan="2" align="right">
                  <input type="submit"class="hidden" value="Submit">
                </td>
            </tr>    
        
          </table>  
             
          </div>
         </form>
         <%
                     String str=request.getParameter("ta");
                     
                      if (str != null) {
                          
                          String report=request.getParameter("report");
                          String key=request.getParameter("key");
                         
                          
                          String e_record=SeSPHR.JBlowfish.encrypt(key,report);
                                 
                          String provide=(String) session.getAttribute("provider");
                          String doc_name=(String )session.getAttribute("username");
                          
                          Statement stmt = Con.createStatement();
                          ResultSet rs = stmt.executeQuery("select max(id) as cnt from clinic_report");
                              int Id = 0;
                              if (rs.next()) {
                                      Id = rs.getInt("cnt");
                                 }
                              Id = Id + 1;
                              String Report_Id = "RPT" + Id;
                                          
                                                      
                               try{
                                                String sql="insert into clinic_report(report_id,report) values(?,?) ";
                                                PreparedStatement smt = Con.prepareStatement(sql);
                                                smt.setString(1,Report_Id);
                                                /*smt.setString(2, session.getAttribute("username"));
                                                smt.setString(3, session.getAttribute("provider"));
                                                smt.setString(4, phr_id);*/
                                                smt.setString(2, e_record);
                                                                                            
                                                smt.executeUpdate();
                                                
                                                
                                                
                                                String query="insert into viewed(phr_id,provider,doctor) values (?,?,?)";
                                                PreparedStatement smt1=Con.prepareStatement(query);
                                                
                                                smt1.setString(1, phr_id);
                                                smt1.setString(2, provide);
                                                smt1.setString(3, doc_name);
                               
                                                smt1.executeUpdate();
                                                
                                                
                                                String repo_log="insert into report_log(report_id,doctor,provider,phr_id) values (?,?,?,?)";
                                                PreparedStatement smt2=Con.prepareStatement(repo_log);
                                                
                                                smt2.setString(1, Report_Id);
                                                smt2.setString(2, doc_name);
                                                smt2.setString(3, provide);
                                                smt2.setString(4, phr_id);
                               
                                                smt2.executeUpdate();
                                                
                                                
                                                out.println("Registered Successfully!!");
                                            }catch(Exception e){
                                            System.out.println(e.getMessage());
                                        }
                                        }
                                     
    %>    
                          
                                          
    </body>
</html>
 