<%-- 
    Document   : index
    Created on : Mar 19, 2011, 9:05:43 AM
    Author     : shreeram
--%>

<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="javax.servlet.jsp.JspWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="SearchDB.*" %>

<%

if (request.getParameter("Book") != null) {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    new SearchDatabase().SearchandReturnBook(bout, request.getParameter("Book"), false);
    String print = bout.toString();
    out.write(print);
} else if (request.getParameter("User") != null) {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    new SearchDatabase().SearchandReturnBook(bout, request.getParameter("User"), true);
    String print = bout.toString();
    out.write(print);
} else {
    out.write("Empty query");
    System.out.println("Empty query");
}

%>