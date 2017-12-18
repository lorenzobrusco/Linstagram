<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/profile_style.css">
</head>
<body>
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
						<div class="row item-user-info">
							<ul>
								<li><b>@${username}</b></li>
								<li><a href="modifyProfile">modifica profilo</a></li>
								<li></li>
							</ul>
						</div>
						<div class="row item-user-info">
							<ul>
								<li><span>9</span> post</li>
								<li><span>142</span> follower</li>
								<li><span>113</span> profili seguiti</li>
							</ul>
						</div>
						<div class="row item-user-info">
							<ul>
								<li>LORENZO BRUSCO</li>
							</ul>
						</div>
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
								<ul class="nav nav-tabs ">
									<li class="active"><a href="#tab_default_1"
										data-toggle="tab"> Posts </a></li>
									<li><a href="#tab_default_2" data-toggle="tab"> Tags </a>
									</li>
									<li><a href="#tab_default_3" data-toggle="tab">
											Bookmarks </a></li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active" id="tab_default_1">
										<div id="colum">

											<div class="col-md-4 col-sm-6 post-section">
												<div class="post">
													<img class="picture img-responsive" src="resources/images/picture3.jpg">
													<div class="links">
														<a href=""><span class="fa fa-heart"> 10</span></a> <a
															href=""><span class="fa fa-comment"> 10</span></a>
													</div>
												</div>
											</div>

											<div class="col-md-4 col-sm-6 post-section">
												<div class="post">
													<img class="picture img-responsive" src="resources/images/picture3.jpg">
													<div class="links">
														<a href=""><span class="fa fa-heart"> 10</span></a> <a
															href=""><span class="fa fa-comment"> 10</span></a>
													</div>
												</div>
											</div>

											<div class="col-md-4 col-sm-6 post-section">
												<div class="post">
													<img class="picture img-responsive" src="resources/images/picture3.jpg">
													<div class="links">
														<a href=""><span class="fa fa-heart"> 10</span></a> <a
															href=""><span class="fa fa-comment"> 10</span></a>
													</div>
												</div>
											</div>

											<div class="col-md-4 col-sm-6 post-section">
												<div class="post">
													<img class="picture img-responsive" src="resources/images/picture3.jpg">
													<div class="links">
														<a href=""><span class="fa fa-heart"> 10</span></a> <a
															href=""><span class="fa fa-comment"> 10</span></a>
													</div>
												</div>
											</div>

											<div class="col-md-4 col-sm-6 post-section">
												<div class="post">
													<img class="picture img-responsive" src="resources/images/picture3.jpg">
													<div class="links">
														<a href=""><span class="fa fa-heart"> 10</span></a> <a
															href=""><span class="fa fa-comment"> 10</span></a>
													</div>
												</div>
											</div>

											<div class="col-md-4 col-sm-6 post-section">
												<div class="post">
													<img class="picture img-responsive" src="resources/images/picture3.jpg">
													<div class="links">
														<a href=""><span class="fa fa-heart"> 10</span></a> <a
															href=""><span class="fa fa-comment"> 10</span></a>
													</div>
												</div>
											</div>

										</div>
									</div>
									<div class="tab-pane" id="tab_default_2">
										<div class="tags">
											<span></span>
											<div>Tag</div>
											<br>
											<div>
												Devi essere taggato per porter rivedere i post.<br>
											</div>
										</div>
									</div>
									<div class="tab-pane" id="tab_default_3">
										<div class="bookmark">
											<span></span>
											<div>Salva</div>
											<br>
											<div>
												Salva le foto e i video che desideri rivedere. Nessuno
												riceverà <br>una notifica e solo tu potrai vedere cosa
												hai salvato.
											</div>
										</div>
									</div>
								</div>
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
