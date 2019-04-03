<%
            String uname = (String) session.getAttribute("userid");
            String email = (String) session.getAttribute("email");
%>
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
            .style13 {color: #FFFFFF; font-weight: bold; font-size: 100%; }
            .style3 {
                color: #FF0000;
                font-weight: bold;
            }


            -->
        </style>
    </head>
    <%@include file="dbconnect.jsp"%>
    <body>
        <div id="page">
            <div id="page-top">
                <div id="page-bottom">
                    <div id="header">
                        <div id="header-cats">
                            <ul>
                                <li class="cat-item"><a href="UserPage.jsp">Home Page</a> </li>
                                <li class="cat-item"><a href="Fileupload.jsp">File Conversion</a> </li>
                                <li class="cat-item"><a href="Viewfile.jsp">View File</a> </li>
                                <li class="cat-item"><a href="index.jsp">Logout</a> </li>
                            </ul>
                        </div>
                        <div class="style2" id="Layer1">Rollback Mechanism </div>
                    </div>
                    <div id="main">
                        <p>&nbsp;</p>
                        <p align="center"><strong>User Details </strong></p>
                        <%
                                    ResultSet rs = st.executeQuery("select * from userregister where uname ='" + uname + "' and email = '" + email + "'");
                                    if (rs.next()) {
                        %>
                        <table width="540" border="0" align="center" bordercolor="#252525" bgcolor="#336699" class="brdr">
                            <tr>
                                <td width="221" height="40" class="style13"> Name </td>
                                <td width="309" class="style13"><%=rs.getString("fname")%></td>
                            </tr>

                            <tr>
                                <td height="42" class="style13"><span class="style13">Mobile Number </span></td>
                                <td class="style13"><%=rs.getString("mobile")%></td>
                            </tr>
                            <tr>
                                <td height="37" class="style13">E-Mail ID </td>
                                <td class="style13"><%=rs.getString("email")%></td>
                            </tr>
                            <tr>
                                <td height="95" class="style13">Address</td>
                                <td class="style13"><label><%=rs.getString("addr")%></label></td>
                            </tr>
                            <tr>
                                <td height="39" class="style13">City</td>
                                <td class="style13"><%=rs.getString("city")%></td>
                            </tr>
                            <tr>
                                <td height="38" class="style13">State</td>
                                <td class="style13"><%=rs.getString("state")%></td>
                            </tr>
                            <tr>
                                <td height="50" class="style13">User Name </td>
                                <td class="style13"><%=rs.getString("uname")%></td>
                            </tr>
                        </table>
                        <%}%>
                        <p>&nbsp;</p>
                        <p align="center" class="style3"></p>
                        <p align="center" class="style3"></p>
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
                        <p>&nbsp;</p>
                        <p align="center" class="style1">&nbsp;</p>
                        <div id="top-panel">
                            <div id="top-panel-top"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
