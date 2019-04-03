<%@page import="java.util.Random"%>
<%@page import="java.util.Calendar"%>
<%@page import="org.apache.commons.fileupload.FileUpload"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ include file="dbconnect.jsp"%>
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
            .style14 {color: #FFFFFF; font-weight: bold; font-size: 110%; }


            -->
        </style>
    </head>
    <%
                String msg = "";
                String act = request.getParameter("act");
                if (act != null) {
                    try {
                        String fname = "", filetype = "", description = "", filepath = "", AbsFile = "";
                        boolean isMultipart = FileUpload.isMultipartContent(request);
                        if (!isMultipart) {
                            request.setAttribute("msg", "Request was not multipart!");
                            request.getRequestDispatcher("Fileupload.jsp").forward(request, response);
                            return;
                        }
                        DiskFileUpload upload = new DiskFileUpload();
                        List items = upload.parseRequest(request);
                        Iterator itr = items.iterator();
                        while (itr.hasNext()) {
                            FileItem item = (FileItem) itr.next();
                            if (item.isFormField()) {
                                String fieldName = item.getFieldName();
                                if (fieldName.equals("fname")) {
                                    fname = item.getString();
                                }
                                if (fieldName.equals("description")) {
                                    description = item.getString();
                                }
                            } else {
                                File f = new File(config.getServletContext().getRealPath("/") + "Files/");
                                if (f.exists() == false) {
                                    f.mkdir();
                                }
                                File ff = new File(config.getServletContext().getRealPath("/") + "Files/" + uname + "\\");
                                if (!ff.exists()) {
                                    ff.mkdirs();
                                }
                                File fullFile = new File(item.getName());
                                File savedFile = new File(getServletContext().getRealPath("/") + "Files/" + uname + "/", fullFile.getName());
                                item.write(savedFile);
                                filepath = request.getContextPath() + "/" + "Files" + "/" + uname + "/" + fullFile.getName().trim();
                                AbsFile = fullFile.getName();
                            }
                        }
                        String fpath = filepath;
                        ResultSet rs = st.executeQuery("select * from filedetails where fname='" + fname + "' and uname='" + uname + "'");
                        if (rs.next()) {
                            msg = "This data is Already Available in that Cloud...";
                        } else {
                            rs = st.executeQuery("select max(ID) as cnt from serverfiles1");
                            int id = 0;
                            if (rs.next()) {
                                id = rs.getInt("cnt");
                            }
                            id = id + 1;
                            ResultSet rs1 = st.executeQuery("select * from serverfiles1");
                            int size = 0;
                            if (rs1.next()) {
                                size=rs1.getRow();
                            }
                            if (id >= 5) {
                                %><script language="">alert("Your Request has been sent to Altered Server...");</script><%
                                Calendar cal = Calendar.getInstance();
                                String caldate = cal.get(Calendar.DATE) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR);
                                rs = st.executeQuery("select max(ID) as cnt from serverfiles2");
                                id = 0;
                                if (rs.next()) {
                                    id = rs.getInt("cnt");
                                }
                                id = id + 1;
                                int n = st.executeUpdate("insert into serverfiles2 values('" + uname + "','" + email + "'," + id + ",'" + caldate + "','" + fname + "','" + description + "','" + AbsFile + "','" + filepath + "','')");
                                if (n == 1) {
                                    st.executeUpdate("insert into convertedfiles values('" + uname + "','" + email + "'," + id + ",'" + caldate + "','" + fname + "','" + description + "','" + AbsFile + "','" + filepath + "')");
                                    msg = "Your Request has been Sent";
                                }

                            } else {
                                Calendar cal = Calendar.getInstance();
                                String caldate = cal.get(Calendar.DATE) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR);

                                int n = st.executeUpdate("insert into serverfiles1 values('" + uname + "','" + email + "'," + id + ",'" + caldate + "','" + fname + "','" + description + "','" + AbsFile + "','" + filepath + "','')");
                                if (n == 1) {
                                    st.executeUpdate("insert into convertedfiles values('" + uname + "','" + email + "'," + id + ",'" + caldate + "','" + fname + "','" + description + "','" + AbsFile + "','" + filepath + "')");
                                    msg = "Your Request has been Sent";
                                }
                            }
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
                        <p align="center"><strong>PDF File Conversion Request</strong></p>

                        <form action="Fileupload.jsp?act=UploadFile" enctype="multipart/form-data" method="post">
                            <table width="540" border="0" align="center" bgcolor="#336699" class="brdr">
                                <tr>
                                    <td height="40" class="style14">File Name </td>
                                    <td class="style14"><input name="fname" type="text" class="txt" title="Enter File Name" required="required"/>                            </td>
                                </tr>

                                <tr>
                                    <td width="195" height="47"><span class="style14">Browse File </span></td>
                                    <td width="333" class="style14"><input type="file" name="file" title="Browse File" required="required"/>                            </td>
                                </tr>
                                <tr>
                                    <td height="88"><span class="style14">Description</span></td>
                                    <td class="style14"><textarea name="description" cols="45" rows="4" title="Enter description" required="required"></textarea>                            </td>
                                </tr>
                                <tr>
                                    <td height="65" colspan="2" align="center"><label>
                                            <input name="Submit" type="submit" class="btn" value="File Upolad" />
                                        </label></td>
                                </tr>
                            </table>
                        </form>
                        <p>&nbsp;</p>
                        <p align="center" class="style3"><%if (msg != null) {
                                        out.println(msg);
                                    }%></p>
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
