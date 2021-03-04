<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<%



String uname = request.getParameter("username");
String password = request.getParameter("password");

if (uname.equals("ajith") && password.equals("05")) {
	response.sendRedirect("BookList.jsp");
	
	
	//out.println("login sucessfully");

} else {
	response.sendRedirect("LoginBook.jsp");
	//out.println("login failed");
}

%>

</body>
</html>