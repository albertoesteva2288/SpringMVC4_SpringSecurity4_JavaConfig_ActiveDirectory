<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url var="cat"        value="/resources/ConfusedCat_MyWar_404.jpg"  />

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>AD Authenticated User</title>
	</head>
	<body>
		Dear <strong>AD Authenticated User: ${user}</strong>You did it!
		<a href="<c:url value='/logout' />">Logout</a>
		
		<DIV>
			<IMG src="${cat}" style="width:900px; margin-top: 50px;" />
		</DIV>
	</body>
</html>