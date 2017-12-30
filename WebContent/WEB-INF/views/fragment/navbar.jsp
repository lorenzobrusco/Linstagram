<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/navbar_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/popover_style.css">

</head>
<body>

	<header>
		<div id="rainbow-progress-bar"></div>
		<div id="navbar-mobile">
			<span id="logo-container"><a href="index" id="logo"></a></span>
			<div class="search-bar">
				<input type="text" class="form-control transparent"
					placeholder="Search" id="search-input">
			</div>
			<div class="bottom-nav-menu">
				<ul id="horizontal-list">
					<li class="active-menu-item"><a href="index" id="home-mobile"
						class="item-mobile"></a></li>
					<li><a href="profile" role="button" id="profile-mobile"
						class="item-mobile"></a></li>
					<li><a href="#create-post-modal" id="add-mobile"
						class="item-mobile"></a></li>
					<li><a href="" id="notification-mobile" class="item-mobile"></a></li>
				</ul>
			</div>
		</div>
		<nav id="navbar" class="transparent">
			<span class="nav-left"> <a href="index"> <span id="logo"></span>
					<span id="logo-text">linstagram</span>
			</a>
			</span> <span id="search-form" class="form-inline">
				<div class="input-group" id="search-div">
					<input type="text" class="form-control transparent"
						placeholder="Search" id="search-input-desktop">
				</div>
			</span> <span class="nav-right"> <a href="" id="explore"
				class="right-icon-nav disabled" data-toggle="tooltip"
				data-placement="left" data-original-title="Coming Soon"></a> <a
				href="#" id="notification" class="right-icon-nav"></a> <a href="#"
				role="button" id="profile" class="right-icon-nav"></a>
			</span>
		</nav>
	</header>
</body>

<script>
	let progressBar = document.querySelector("#rainbow-progress-bar");
	window.onload = function() {
		progressBar.style.display = 'none';
	};

	//Popover (script that use EL)
	$(document)
			.ready(
					function() {

						//mobile navbar event
						if ($("#navbar-mobile").css('display') != "none") {
							var localpathname = window.location.pathname;
							var res = localpathname.split("/");
							if (res[2] == "profile") {
								$("#horizontal-list li").removeClass(
										"active-menu-item");
								$("#profile-mobile").parent().addClass(
										"active-menu-item");
								$("#add-mobile").parent()
										.css("display", "none");
							}
						}

						var title_popover = "<img class='user-img' src='${user.photoProfile}'/><b><span id='username-popover'>${user.username}</span></b>";
						var content_popover = "<a href='profile' class='popover-option' id='profile-option-popover'><i class='fa fa-user'></i>Profile</a> <hr> <a href='logout' class='popover-option' id='logout-option-popover'><i class='fa fa-sign-out'></i>Logout</a>";

						$('#profile').popover({
							title : title_popover,
							content : content_popover,
							html : true,
							placement : "bottom",
							trigger : "manual",
							container : "header"
						}).on("mouseenter", function() {
							var _this = this;
							$(this).popover("show");
							$(".popover").on("mouseleave", function() {
								$(_this).popover('hide');
							});
						}).on("mouseleave", function() {
							var _this = this;
							setTimeout(function() {
								if (!$(".popover:hover").length) {
									$(_this).popover("hide");
								}
							}, 300);
						});

						//<c:forEach items="${notifications}" var="notification">
						//</c:forEach>
						var content_notification_popover = "<div id='notification_list'>";
							content_notification_popover += "<div class='user_from_notification'>"
								content_notification_popover += "<img src='${pageContext.request.contextPath}/resources/images/user_login_img.png'>"
							content_notification_popover += "</div>"
							content_notification_popover += "<div class='context_notification'>"
								content_notification_popover += "<span>Qui ci va il testo</span>"
							content_notification_popover += "</div>"
							content_notification_popover += "<div class='follow_btn_notification'>"
								content_notification_popover += "<button class='btn btn-primary'>Follow</button>"
							content_notification_popover += "</div>"
						
						content_notification_popover += "</div>"
						$('#notification').popover({
							selector : "#notification",
							content : content_notification_popover,
							html : true,
							placement : "bottom",
							trigger : "manual",
							container : "header"	
						}).on("mouseenter", function() {
							var _this = this;
							$(this).popover("show");
							$(".popover").on("mouseleave", function() {
								$(_this).popover('hide');
							});
						}).on("mouseleave", function() {
							var _this = this;
							setTimeout(function() {
								if (!$(".popover:hover").length) {
									$(_this).popover("hide");
								}
							}, 300);
						});
						$('[data-toggle="tooltip"]').tooltip();

						$('[data-toggle="tooltip"]').tooltip();

						//TODO not work	

						$('#search-input-desktop').focusin(function() {
							$("#search-div").css("width", "70%");
							console.log("ok");
						});

						$('#search-input-desktop').focusout(function() {
							$("#search-div").css("width", "50%");
						});

					});
</script>
</html>