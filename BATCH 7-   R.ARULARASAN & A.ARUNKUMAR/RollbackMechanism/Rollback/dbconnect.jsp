<%@page import="java.sql.*" %>
<%
            String url = "jdbc:mysql://localhost:3306/rollback";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement st = con.createStatement();
%>