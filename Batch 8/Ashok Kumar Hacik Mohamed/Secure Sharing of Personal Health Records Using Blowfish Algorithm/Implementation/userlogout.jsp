<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%
    session.removeAttribute("userlogid");
    session.removeAttribute("username");
    response.sendRedirect("userpage.html");


%>
