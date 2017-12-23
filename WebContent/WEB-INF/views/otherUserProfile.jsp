<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Listagram</title>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="resources/js/userPhotoProfile.js"></script>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/profile_style.css">


</head>
<body>

<!-- PROFILO DEGLI ALTRI UTENTI [non quello nella sessione, cio� loggato] -->

	<jsp:include page="./fragment/navbar.jsp"></jsp:include>
	<div class="container profile">
		<section class="profile">
			<div class="header-user-info">
				<div class="row">
					<div class="col-sm-2"></div>
					<div class="col-sm-3">
						<div class="user-img-container">
							<div class="user-img">
								<img src="resources/images/user_login_img.png" class="img-circle">
							</div>
						</div>
					</div>
					<div class="col-sm-5">
						<c:choose>
							<c:when test="${user.privateProfile == true }">
								<div class="row item-user-info">
									<ul>
										<li><b>@${user.username}</b></li>
										<li></li>
									</ul>
								</div>
								<div class="row item-user-info" style="padding-bottom:0% !important">
									<ul>
									 <c:choose>
									  <c:when test ="!empty ${user.name}  || !empty ${user.surname}">
											<li>${user.name} ${user.surname}</li>
										</c:when>
										<c:otherwise>
											<li><b>Name</b> & <b>Surname</b> Unknow ..</li>
										</c:otherwise>
									</c:choose>
									</ul>
								</div>
								<div class="row item-user-info">
									<ul>
										<li><button>Follow</button></li>
									</ul>
								</div>
							</c:when>
							<c:otherwise>
								<jsp:include page="./fragment/userProfileFragment/pagePublic.jsp"></jsp:include>
							</c:otherwise>
						</c:choose>
					</div>

					<div class="col-sm-2"></div>
				</div>
			</div>
			
			<div class="posts-user-info">
				<div class="row">
					<div class="tabbable-panel">
						<div class="col-md-2"></div>
						<div class="col-md-8">
							<div class="tabbable-line">
								<c:choose>
									<c:when test="${user.privateProfile == false }">
									
										<ul class="nav nav-tabs ">
											<li class="active"><a href="#tab_default_1"
												data-toggle="tab"> Posts </a></li>
											<li><a id="tags" href="#tab_default_2" data-toggle="tab"> Tags </a> </li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active" id="tab_default_1">
												<div id="colum">
													<jsp:include page="./fragment/userProfileFragment/postSection.jsp"></jsp:include>
		
												</div>
											</div>
											<div class="tab-pane" id="tab_default_2">
												<div id="tag">
													<div class="tags">
														<span></span>
														<div>Tag</div>
														<br>
														<div id="contentTag">
															Devi essere taggato per porter rivedere i post.<br>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<ul class="nav nav-tabs ">
											<li ><a href="#tab_default_1"
												data-toggle="tab"> </a></li>
										</ul>
										<div class="tab-content">
											<div class="tab-pane active" id="tab_default_1">
												<div id="colum">
													<div class="private">
														<span></span>
														<div>This Account is Private</div>
														<br>
														<div id="contentTag">
															Follow this account to see their photo.<br>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
 <jsp:include page="./fragment/footer.jsp"></jsp:include>
</body>
</html>