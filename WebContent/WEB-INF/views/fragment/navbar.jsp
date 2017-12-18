<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/navbar_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/popover_style.css">

<script src="${pageContext.request.contextPath}/resources/js/navbar.js"></script>
</head>
<body>

	<header>
		<div id="rainbow-progress-bar"></div>
		<nav class="transparent">
			<span class="nav-left"> <a href="index.html"> <span
					id="logo"></span> <span id="logo-text">linstagram</span>
			</a>
			</span> <span id="search-form" class="form-inline">
				<div class="input-group" id="search-div">
					<span class="input-group-btn"><i class="fa fa-search"></i></span> <input
						type="text" class="form-control transparent" placeholder="Search"
						id="search-input">
				</div>
			</span> <span class="nav-right"> <a href="" id="explore"
				class="right-icon-nav disabled" data-toggle="tooltip"
				data-placement="left" data-original-title="Coming Soon"></a> <a
				href="" id="notification" class="right-icon-nav"></a> <a href="#"
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

//Popover 
$(document).ready(function () {
	  var title_popover = "<img class='user-img' src='resources/images/user_login_img.png'/><b><span id='username-popover'>${username}</span></b>";
	  var content_popover = "<a href='profile' class='popover-option' id='profile-option-popover'><i class='fa fa-user'></i>Profile</a> <hr> <a href='logout' class='popover-option' id='logout-option-popover'><i class='fa fa-sign-out'></i>Logout</a>";
	  
	  $('#profile').popover({
	    title: title_popover,
	    content: content_popover,
	    html: true,
	    placement: "bottom",
	    trigger: "manual",
	    container: "header"
	  }).on("mouseenter", function () {
	    var _this = this;
	    $(this).popover("show");
	    $(".popover").on("mouseleave", function () {
	      $(_this).popover('hide');
	    });
	  }).on("mouseleave", function () {
	    var _this = this;
	    setTimeout(function () {
	      if (!$(".popover:hover").length) {
	        $(_this).popover("hide");
	      }
	    }, 300);
	  });

	});

</script>
</html>