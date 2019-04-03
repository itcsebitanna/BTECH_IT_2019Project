<%@page import="java.io.File"%>
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
            .style14 {color: #000000}
            .style15 {color: #000000; font-weight: bold; font-size: 100%; }


            -->
        </style>
    </head>
    <script type="text/JavaScript">
        function timeRefresh(timeoutPeriod)
        {
            setTimeout("location.reload(true);",timeoutPeriod);
        }
    </script>
    <%@include file="dbconnect.jsp"%>
    <body onload="JavaScript:timeRefresh(10000);">
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
                        <p align="center"><strong>File Details </strong></p>
                        <table width="786" border="0" align="center" bordercolor="#252525" bgcolor="#336699" class="brdr">
                            <tr>
                                <td width="56" height="40" align="center" class="style13">S.No</td>
                                <td width="131" align="center" class="style13">Date</td>
                                <td width="165" align="center" class="style13">File Name </td>
                                <td width="241" align="center" class="style13">Description</td>
                                <td width="171" align="center" class="style13">Action</td>
                            </tr>
                            <%
                                        int id = 0;
                                        ResultSet rs = st.executeQuery("select * from serverfiles1 where uname ='" + uname + "' and email = '" + email + "'");

                                        while (rs.next()) {
                                            id += 1;
                            
                            %>
                            <tr>
                                <td height="42" bgcolor="#FFFFFF" class="style13 style14"><%=id%></td>
                                <td bgcolor="#FFFFFF" class="style15"><%=rs.getString("caldate")%></td>
                                <td bgcolor="#FFFFFF" class="style15"><%=rs.getString("fname")%></td>
                                <td bgcolor="#FFFFFF" class="style15"><%=rs.getString("description")%></td>
                                <td align="center" bgcolor="#FFFFFF" class="style13"><a href="<%=rs.getString("filepath")%>">Download</a></td>
                            </tr>
                            <%}%>
                            <%
                                        ResultSet rs1 = st.executeQuery("select * from serverfiles2 where uname ='" + uname + "' and email = '" + email + "'");
                                        while (rs1.next()) {
                                            id += 1;
                            %>
                            <tr>
                                <td height="42" bgcolor="#FFFFFF" class="style13 style14"><%=id%></td>
                                <td bgcolor="#FFFFFF" class="style15"><%=rs1.getString("caldate")%></td>
                                <td bgcolor="#FFFFFF" class="style15"><%=rs1.getString("fname")%></td>
                                <td bgcolor="#FFFFFF" class="style15"><%=rs1.getString("description")%></td>
                                <td align="center" bgcolor="#FFFFFF" class="style13"><a href="<%=rs1.getString("filepath")%>">Download</a></td>
                            </tr>
                            <%}%>
                        </table>

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
