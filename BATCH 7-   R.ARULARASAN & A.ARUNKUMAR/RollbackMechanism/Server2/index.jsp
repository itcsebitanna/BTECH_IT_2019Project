<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>CloudSim-Server</title>
        <meta name="keywords" content="free web template, business website, CSS, HTML" />
        <meta name="description" content="free CSS HTML template for professional business websites" />
        <link href="style.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
            <!--
            .style1 {
                color: #FF0000;
                font-weight: bold;
            }
            -->
        </style>
    </head>
    <body>
        <div id="templatemo_container">
            <div id="templatemo_header"><div id="templatemo_logo"><img src="images/logo.gif" alt="Logo" />
                    <div id="templatemo_sitetitle">Server</div>
                </div>

            </div>
            <div id="templatemo_menu">
                <ul>
                    <li><a href="index.jsp"  class="current">Home</a></li>

                </ul>
            </div>

            <div id="templatemo_banner">
                <h1>&nbsp;</h1>
            </div>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p><strong><center>Authorized User Login</center> </strong></p>
          <p><strong></strong></p>
            </p>
            <form method="post">
                <table width="433" border="0" align="center" bgcolor="#66CCFF">
                    <tr>
                        <td width="181" height="40"><strong>User Name </strong></td>
                        <td width="236">
                            <input name="uname" type="text" class="txt" required title="Enter User Name"/>
                        </td>
                    </tr>
                    <tr>
                        <td height="60"><strong>Password</strong></td>
                        <td>
                            <input name="pword" type="password" class="txt" required title="Enter Password"/>
                        </td>
                    </tr>
                    <tr>
                        <td height="45" colspan="2" align="center"><label>
                                <input name="Submit" type="submit" class="btn" value="Login" />
                            </label></td>
                    </tr>
                </table>
            </form>
            <p>&nbsp;</p>
            <%
                        String msg = "";
                        String btn = request.getParameter("Submit");
                        if (btn != null) {
                            String un = request.getParameter("uname");
                            String pwd = request.getParameter("pword");
                            if (un.equals("admin") && pwd.equals("admin")) {
                                response.sendRedirect("AdminHome.jsp");
                            } else {
                                msg = "Invalid Login...";
                            }
                        }
            %>
            <p class="style1"><center><%if (msg != null) {
                            out.println(msg);
                        }%></center></p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <div id="templatemo_light_blue_row"></div>
        </div>
        <div align=center></div>
    </body>
</html>