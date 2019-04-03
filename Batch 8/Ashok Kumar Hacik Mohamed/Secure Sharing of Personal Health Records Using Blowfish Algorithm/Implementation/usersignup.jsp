<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="include/dbconnect.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href=signcss.css  rel=stylesheet type=text/css>
</head>
<body>
<form  name="Register" action="usersignup.jsp?va=1" method="post">
  <div class="container">
    <h1>PHR User Register</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>
<label for="name"><b>Name</b></label>
    <input type="text"  placeholder="Enter Name"  name="name" required>
   <label for="email"><b>Password</b></label>
    <input type="password"  placeholder="Enter Password"  name="psw" required>
   <label for="psw"><b>Hospital</b></label>
    <input type="text"  placeholder="Enter Hospital" name="hospital"  required>
    <label for="dob"><b>Email</b></label>
    <input type="text"  placeholder="Enter Email Id"  name="email" required>
   <label for="mobile"><b>Mobile Number</b></label>
   <input type="text"  placeholder="Enter Mobile Number"  name="mobile" required>
   <label for="gender"><b>Gender</b></label>
    <input type="text"  placeholder="Enter Gender"  name="gender" required>
   <label for="pincode"><b>Pincode</b></label>
    <input type="text"  placeholder="Enter Pincode"  name="pincode" required>    <hr>
<p>By creating an account you agree to our <a  href="#">Terms &amp; Privacy</a>.</p>
    <input name="submit" type="submit"  value="Register" />
  </div>

  <div class="container signin">
    <p>Already have an account? <a href="userlogin.jsp" target="_self">Sign in</a>.</p>
  </div>
    <span>
     <%

                                        String ent = request.getParameter("va");
                                        if (ent != null) {
                                            String name = request.getParameter("name");
                                            String password = request.getParameter("psw");
                                            String hospital = request.getParameter("hospital");
                                            String email = request.getParameter("email");
                                            String contact = request.getParameter("mobile");
                                            String gender = request.getParameter("gender");
                                            String pincode = request.getParameter("pincode");
                     
                                            try {
                                                Statement smt = Con.createStatement();
                                                Statement smt1 = Con.createStatement();
                                                Statement smt2 = Con.createStatement();
                                                ResultSet rs = smt.executeQuery("select max(id) as cnt from users");
                                                int Id = 0;
                                                if (rs.next()) {
                                                    Id = rs.getInt("cnt");
                                                }
                                                Id = Id + 1;
                                                String UserId = "DOC" + Id;
                                                ResultSet rs1 = smt.executeQuery("select * from users where username='" + name + "' and password='" + password + "'");
                                                if (rs1.next()) {
                                                    out.println("Your Username not Available...");
                                                } else {
                                                    int n = smt1.executeUpdate("insert into users values(" + Id + ",'"+ UserId +"','" + name + "','"+ password +"','" + hospital + "','" + email + "','" + contact + "','" + gender + "','" + pincode + "')");
                                                    if (n == 1) {
                                                        out.println("Register successfully..");
                                                        response.sendRedirect("userlogin.jsp");
                                                    }
                                                }

                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }

                            %>    
    </span>
</form>
</body>
</html>