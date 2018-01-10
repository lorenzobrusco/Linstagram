<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Listagram</title>
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/dropzone.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/index_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/stories.css">

</head>
<body>

<!-- USATO SOLO PER FARE LE PROVE DEL PROFILO UTENTE [infatti fa schifo xD] -->
<!-- magari un giorno, diventerà la pagina della ricerca utente, ma chi lo sa xD -->

	<jsp:include page="./fragment/navbar.jsp"></jsp:include>
	<div class="container" id="top">
		<div class="row">
			<div class="col-md-2"></div>
			
			<c:forEach items="${users}" var="user">
				
				<form role="form" action="userPage">
					<button name="username" value="${user.username }">${user.username }</button>
				</form>
			</c:forEach>
			
		</div>
	
	</div>
	<jsp:include page="./fragment/footer.jsp"></jsp:include>

</body>

</html>
