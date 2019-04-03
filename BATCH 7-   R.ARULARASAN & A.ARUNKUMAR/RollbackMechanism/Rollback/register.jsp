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
    <script language="javascript">
        function mobilevalidation()
        {
            if(isNaN(document.register.mobile.value))
            {
                alert("Enter the Mobile Number in digits");
                document.register.mobile.focus();
                return false;
            }
            if((document.register.mobile.value)<10)
            {
                alert("Enter the valid Mobile Numbers");
                document.register.mobile.focus();
                return false;
            }
           
            return true;
        }
    </script>
    <%@include file="dbconnect.jsp"%>
    <%
                String btn = "", msg = "";
                try {
                    String fname = "", mobile = "", email = "", addr = "", city = "", state = "", uname = "", pword = "";
                    btn = request.getParameter("Submit");
                    if (btn != null) {
                        fname = request.getParameter("fname");
                        mobile = request.getParameter("mobile");
                        email = request.getParameter("email");
                        addr = request.getParameter("addr");
                        city = request.getParameter("city");
                        state = request.getParameter("state");
                        uname = request.getParameter("uname");
                        pword = request.getParameter("pwd");
                        ResultSet rs = st.executeQuery("select * from userregister where uname ='" + uname + "' and mobile = '" + mobile + "'");
                        if (rs.next()) {
                            msg = "Already Registered Candidate";
                        } else {
                            int n = st.executeUpdate("insert into userregister values('" + fname + "','" + mobile + "','" + email + "','" + addr + "','" + city + "','" + state + "','" + uname + "','" + pword + "')");
                            if (n == 1) {
                                msg = "Candidate Details Registered...";
                            }
                        }
                    }
                } catch (Exception ex) {
                    out.println(ex.getMessage());
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
                        <p align="center"><strong>User Registration </strong></p>
                        <form method="post" action="" onsubmit="return mobilevalidation()">
                            <table width="540" border="0" align="center" bordercolor="#252525" bgcolor="#336699" class="brdr">
                                <tr>
                                    <td width="174" height="40" class="style13"> Name </td>
                                    <td width="356"><input name="fname" type="text" class="txt" required="required" title="Enter First Name" />                              </td>
                                </tr>

                                <tr>
                                    <td height="42"><span class="style13">Mobile Number </span></td>
                                    <td><input name="mobile" type="mobile" class="txt" required="required" title="Enter Mobile No."/>                              </td>
                                </tr>
                                <tr>
                                    <td height="37" class="style13">E-Mail ID </td>
                                    <td><input name="email" type="text" class="txt" required="required" title="Enter E-Mail ID"/>                              </td>
                                </tr>
                                <tr>
                                    <td height="95" class="style13">Address</td>
                                    <td><label>
                                            <textarea name="addr" cols="30" rows="5" required="required"></textarea>
                                        </label></td>
                                </tr>
                                <tr>
                                    <td height="39" class="style13">City</td>
                                    <td><input name="city" type="text" class="txt" required="required" title="Enter City" />                              </td>
                                </tr>
                                <tr>
                                    <td height="38" class="style13">State</td>
                                    <td><select name="state" title="Select State">
                                            <option>--Select--</option>
                                            <option>Tamilnadu</option>
                                            <option>Andhrapradesh</option>
                                            <option>Kerala</option>
                                            <option>Maharastra</option>
                                            <option>Karnataka</option>
                                        </select>                              </td>
                                </tr>
                                <tr>
                                    <td height="50" class="style13">User Name </td>
                                    <td><input name="uname" type="text" class="txt" required="required" title="Enter User Name"/>                              </td>
                                </tr>
                                <tr>
                                    <td height="51" class="style13">Password</td>
                                    <td><input name="pwd" type="password" class="txt" required="required" title="Enter Password"/>                              </td>
                                </tr>
                                <tr>
                                    <td height="40" colspan="2" align="center" class="style13"><label>
                                            <input name="Submit" type="submit" class="btn" value="Register" />
                                        </label></td>
                                </tr>
                            </table>
                            <p>&nbsp;</p>
                                <p align="center" class="style3"><%if (msg != null) {
                                          out.println(msg);
                                      }%></p>
                        </form>
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
