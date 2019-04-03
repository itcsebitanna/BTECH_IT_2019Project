<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US">
    <head profile="http://gmpg.org/xfn/11">
        <title>Cloud</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
        <!--[if IE]><link rel="stylesheet" type="text/css" href="style_ie.css" /><![endif]-->
        <!--[if lt IE 7]><link rel="stylesheet" type="text/css" href="style_ie6.css" /><![endif]-->
        <style type="text/css">
            <!--
            .style1 {color: #FFFFFF}
            #Layer1 {
                position:absolute;
                width:200px;
                height:52px;
                z-index:1;
                left: 63px;
                top: 108px;
            }
            .style2 {
                color: #FFFFFF;
                font-size: 36px;
                font-weight: bold;
            }
            #Layer2 {
                position:absolute;
                width:568px;
                height:31px;
                z-index:1;
                left: 265px;
                top: 803px;
            }
            .style13 {color: #FFFFFF; font-weight: bold; font-size: 110%; }
            .style3 {
                color: #FF0000;
                font-weight: bold;
            }


            -->
        </style>
    </head>
    <%@include file="dbconnect.jsp"%>
    <%
                String b1, msg = null;
                b1 = request.getParameter("Submit");
                if (b1 != null) {
                    String uname = "", pwd = "";
                    uname = request.getParameter("uname");
                    pwd = request.getParameter("pwd");
                    try {
                        ResultSet rs = st.executeQuery("select * from userregister where uname='" + uname + "' and pword='" + pwd + "'");
                        if (rs.next()) {

                            session.setAttribute("userid", rs.getString("uname"));
                            session.setAttribute("email", rs.getString("email"));
                            response.sendRedirect("UserPage.jsp");
                        } else {
                            msg = "Incorrect User..";
                        }
                    } catch (Exception ex) {
                        out.println(ex.getMessage());
                    }

                }
    %>
    <body>
        <div id="page">
            <div id="page-top">
                <div id="page-bottom">
                    <div id="header">
                        <div id="header-cats">
                            <ul>
                                <li class="cat-item"><a href="index.jsp">Home Page</a> </li>
                                <li class="cat-item"><a href="register.jsp">User Register</a> </li>
                            </ul>
                        </div>
                        <div class="style2" id="Layer1">Rollback Mechanism </div>
                    </div>
                    <div id="main">
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p align="center"><strong>Authorized User Login </strong></p>
                        <form method="post" action="">
                            <table width="386" border="0" align="center" bgcolor="#336699" class="brdr">
                                <tr>
                                    <td width="127" height="52"><span class="style13">User Name </span></td>
                                    <td width="215"><input name="uname" type="text" class="txt" required="required" placeholder="Enter User Name" title="Enter User Name"/></td>
                                </tr>
                                <tr>
                                    <td height="60"><span class="style13">Password</span></td>
                                    <td><input name="pwd" type="password" class="txt" required="required" placeholder="Enter Password" title="Enter Password"/>            </td>
                                </tr>
                                <tr>
                                    <td height="60" colspan="2" align="center"><label>
                                            <input name="Submit" type="submit" class="btn" value="Login" />
                                        </label></td>
                                </tr>
                            </table>
                        </form>
                        <p align="center" class="style3"></p>
                            <p align="center" class="style3"><%if (msg != null) {
                                        out.println(msg);
                                    }%></p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p align="center" class="style1">&nbsp;</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
