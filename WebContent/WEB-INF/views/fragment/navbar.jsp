<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/navbar_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/popover_style.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.ico"
	type="image/x-icon">
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.ico"
	type="image/x-icon">
<script src="${pageContext.request.contextPath}/resources/js/navbar.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/search_event.js"></script>

<header>
	<div id="rainbow-progress-bar"></div>
	<div id="navbar-mobile">
		<span id="logo-container"><a href="index" id="logo"></a></span>
		<div class="search-bar">
			<input type="text" class="form-control transparent search-input" placeholder="Search">
		</div>
		<div class="bottom-nav-menu">
			<ul id="horizontal-list">
				<li class="active-menu-item"><a href="index" id="home-mobile" class="item-mobile"></a></li>
					
				<li><a href="profile" role="button" id="profile-mobile" class="item-mobile"></a></li>
					
				<li><a href="#create-post-modal" id="add-mobile" class="item-mobile"></a></li>
					
				<li><a href="#" id="notification-mobile" class="item-mobile"></a></li>
			</ul>
		</div>
	</div>
	<nav id="navbar" class="transparent">
		<span class="nav-left"> <a href="index"> <span id="logo"></span>
				<span id="logo-text">linstagram</span>
		</a>
		</span> <span id="search-form" class="form-inline">
			<div class="input-group" id="search-div">
				<input type="text" class="form-control transparent search-input" placeholder="Search">
			</div>
		</span> 
		<span class="nav-right"> 
			<a href="" id="explore" class="right-icon-nav disabled" data-toggle="tooltip"
			data-placement="left" data-original-title="Coming Soon"></a>
			 
			<a href="#" id="notification" class="right-icon-nav"><span class="arrow hide"></span></a>
			<a href="#" role="button" id="profile" class="right-icon-nav"></a>
		</span>
	</nav>
</header>

<div id="notification_list" class="hide"></div>
<div id="notification_list_mobile" class="hide"></div>
<div id="hint-list" class="hide"></div>

<script
	src="${pageContext.request.contextPath}/resources/js/notification.js"></script>
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
						}).click(function(e){
							e.preventDefault();
							$('#profile').popover('show');
							e.stopPropagation();
						});
						
						function closeProfilePopover(){
							$('#profile').popover('hide');
						}
						
						$('body').click(function (e) {
							closeProfilePopover();
						});
						
						window.addEventListener("scroll", closeProfilePopover);
						
						$('[data-toggle="tooltip"]').tooltip();

						/*$('#search-input-desktop').focusin(function() {
							$("#search-div").css("width", "70%");
						});

						$('#search-input-desktop').focusout(function() {
							$("#search-div").css("width", "50%");
						});*/


					});
	
	
	
</script>