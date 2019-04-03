<%@page import="org.apache.poi.xwpf.converter.pdf.PdfConverter"%>
<%@page import="org.apache.poi.xwpf.converter.pdf.PdfOptions"%>
<%@page import="org.apache.poi.xwpf.usermodel.XWPFDocument"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
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
    <%
                String uname="",fname = "", absfile = "", fpath = "";
                uname = request.getParameter("uname");
                fname = request.getParameter("fname");
                absfile = request.getParameter("absfile");
                fpath = request.getParameter("fpath");
    %>
    <%@include file="dbconnect.jsp"%>
    <%
                String b1, msg = null;
                b1 = request.getParameter("Submit");
                if (b1 != null) {
                    
                    try {
                        //long start = System.currentTimeMillis();

                       // 1) Load DOCX into XWPFDocument
                        InputStream is = new FileInputStream(new File(getServletContext().getRealPath("/")+  "Files\\" +uname+ "\\"+absfile));
                        out.println(new File(getServletContext().getRealPath("/")+  "Files\\" +uname+ "\\"+absfile));
                        String filesplit[]=absfile.split(".");
                        out.println("klsadlkasm"+filesplit[0]);
                        XWPFDocument document = new XWPFDocument(is);
                        
                        // 2) Prepare Pdf options
                        PdfOptions options = PdfOptions.create();

                        // 3) Convert XWPFDocument to Pdf
                        
                        OutputStream out1 = new FileOutputStream(new File(getServletContext().getRealPath("/")+  "Files\\" +uname+ "\\"+absfile));
                        PdfConverter.getInstance().convert(document, out1, options);

                        //System.out.println("Generate pdf/Helloworld.pdf with "
                                //+ (System.currentTimeMillis() - start) + "ms");

                    } catch (Throwable e) {
                        e.printStackTrace();
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
                                <li class="cat-item"><a href="FileConversion.jsp">Close</a> </li>
                            </ul>
                        </div>
                        <div class="style2" id="Layer1">Rollback Mechanism</div>
                    </div>
                    <div id="main">
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p>&nbsp;</p>
                        <p align="center"><strong>PDF File Conversion</strong></p>
                        <form method="post" action="">
                            <table width="386" border="0" align="center" bgcolor="#336699" class="brdr">
                                <tr>
                                    <td width="127" height="52"><span class="style13">File Name </span></td>
                                    <td width="215"><span class="style13"><%=fname%> </span></td>
                                </tr>
                                <tr>
                                    <td height="60"><span class="style13">Document Name</span></td>
                                    <td><span class="style13"><%=absfile%></span></td>
                                </tr>
                                <tr>
                                    <td height="60" colspan="2" align="center"><label>
                                            <input name="Submit" type="submit" class="btn" value="Convert" />
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
