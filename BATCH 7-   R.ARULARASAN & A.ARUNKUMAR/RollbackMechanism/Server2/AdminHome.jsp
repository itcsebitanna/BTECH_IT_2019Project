<%@page import="java.sql.ResultSet"%>
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
            .style1 {color: #FF0000}
            -->
        </style>
    </head>
    <script type="text/javascript">
        var timeout = setTimeout("location.reload(true);",600000);
        function resetTimeout() {
            clearTimeout(timeout);
            timeout = setTimeout("location.reload(true);",600000);
        }
    </script>
    <script>
        var time = new Date().getTime();
        $(document.body).bind("mousemove keypress", function(e) {
            time = new Date().getTime();
        });

        function refresh() {
            if(new Date().getTime() - time >= 60000)
                window.location.reload(true);
            else
                setTimeout(refresh, 10000);
        }

        setTimeout(refresh, 10000);
    </script>
    <script>
        setInterval(function () { autoloadpage(); }, 60); // it will call the function autoload() after each 30 seconds.
        function autoloadpage() {
            $.ajax({
                url: "URL of the destination page",
                type: "POST",
                success: function(data) {
                    $("form#form1").html(data); // here the wrapper is main div
                }
            });
        }
    </script>
    <script type="text/JavaScript">
        function timeRefresh(timeoutPeriod)
        {
            setTimeout("location.reload(true);",timeoutPeriod);
        }
    </script>
        <script language="javascript">
        function del()
        {
            if(!confirm("Are you Sure Want to delete?"))
            {
                return false;
            }
            return true;
        }
    </script>

    <%@include file="dbconnect.jsp" %>
    <%
                String msg = "";
                String act = request.getParameter("Action");
                if (act != null) {
                    String uname = request.getParameter("uname");
                    String email = request.getParameter("email");
                    String abfile = request.getParameter("abfile");
                    String accfilename = abfile.substring(0, abfile.lastIndexOf('.'));
                    String filepath = "/CloudSim/Files/" + uname + "/" + accfilename + ".pdf";

                    st.executeUpdate("update convertedfiles set filepath='" + filepath + "' where uname='" + uname + "' and email='" + email + "' and AbsFile='" + abfile + "'");
                    st.executeUpdate("update serverfiles2 set ch='1' where uname='" + uname + "' and email='" + email + "' and AbsFile='" + abfile + "'");
                }
                String act1 = request.getParameter("DelAction");
                if (act1 != null) {
                    String sid = request.getParameter("delid");
                    st.executeUpdate("delete from  serverfiles2 where id='" + sid + "'");
                }
    %>

    <body onload="JavaScript:timeRefresh(10000);">
        <div id="templatemo_container">
            <div id="templatemo_header"><div id="templatemo_logo"><img src="images/logo.gif" alt="Logo" />
                    <div id="templatemo_sitetitle">Server</div>
                </div>

            </div>
            <div id="templatemo_menu">
                <ul>
                    <li><a href="AdminHome.jsp"  class="current">Home</a></li>
                    <li><a href="index.jsp">Logout</a></li>
                </ul>
            </div>

            <div id="templatemo_banner">
                <h1>&nbsp;</h1>
            </div>
            <p>&nbsp;</p>
            <p><strong><center>File Conversion Request</center></strong></p>
            <p>&nbsp;</p>
            <form method="post" id="form1">
                <table width="847" border="0" align="center" bgcolor="#66CCFF">
                    <tr>
                        <td width="39" height="40"><strong>S.No</strong></td>
                        <td width="96"><strong>Date</strong></td>
                        <td width="203"><strong>User Name </strong></td>
                        <td width="276"><strong>File Name </strong></td>
                        <td width="211"><strong>Action</strong></td>
                    </tr>
                    <%
                                int id = 0;
                                ResultSet rs = st.executeQuery("select * from serverfiles2");
                                while (rs.next()) {
                                    id += 1;


                    %>
                    <tr>
                        <td height="38" bgcolor="#FFFFFF"><strong><%=id%></strong></td>
                        <td bgcolor="#FFFFFF"><strong><%=rs.getString("caldate")%></strong></td>
                        <td bgcolor="#FFFFFF"><strong><%=rs.getString("uname")%></strong></td>
                        <td bgcolor="#FFFFFF"><strong><%=rs.getString("fname")%></strong></td>
                        <%
                                                            int ch = rs.getInt("ch");
                                                            if (ch == 0) {
                        %>
                        <td align="center" bgcolor="#FFFFFF"><a href="AdminHome.jsp?Action=conv&amp;uname=<%=rs.getString("uname")%>&email=<%=rs.getString("email")%>&abfile=<%=rs.getString("AbsFile")%>">Convert </a> </td>
                        <%} else {%>
                        <td align="center" bgcolor="#FFFFFF"><strong><a href="AdminHome.jsp?DelAction=del&amp;delid=<%=rs.getString("id")%>" onclick="return del()">Delete</a></strong></td>
                        <%}%>
                    </tr>
                    <%}
                                rs = st.executeQuery("select max(ID) as cnt from serverfiles2");
                                id = 0;
                                if (rs.next()) {
                                    id = rs.getInt("cnt");
                                }
                                if (id >= 6) {
                    %>
                    <script language="javascript">
                        alert("Server is Overloaded..Try to Request Alternative Server");
                    </script>
                    <%
                                    rs = st.executeQuery("select max(ID) as cnt from serverfiles1");
                                    int id1 = 0;
                                    if (rs.next()) {
                                        id1 = rs.getInt("cnt");
                                    }
                                    id1 = id1 + 1;
                                    rs = st.executeQuery("select * from serverfiles2 where id='" + id + "'");
                                    if (rs.next()) {
                                        st.executeUpdate("insert into serverfiles1 values('" + rs.getString(1) + "','" + rs.getString(2) + "','" + id1 + "','" + rs.getString(4) + "','" + rs.getString(5) + "','" + rs.getString(6) + "','" + rs.getString(7) + "','" + rs.getString(8) + "','0')");
                                        st.executeUpdate("delete from serverfiles2 where id='" + id + "'");
                                    }
                                }
                    %>
                </table>
            </form>

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