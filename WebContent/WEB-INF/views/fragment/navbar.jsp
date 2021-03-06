<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.ico"
	type="image/x-icon">
<link rel="icon"	href="${pageContext.request.contextPath}/resources/images/favicon.ico"
	type="image/x-icon">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/navbar_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/popover_style.css">
<script src="${pageContext.request.contextPath}/resources/js/lib/sockjs-0.3.4.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/lib/stomp.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/search_event.js"></script>

<header>
	<div id="rainbow-progress-bar"></div>
	<div id="navbar-mobile">
		<span id="logo-container"><a href="${pageContext.request.contextPath}/" id="logo"></a></span>
		<div class="search-bar">
			<input type="text" class="form-control transparent search-input"
				placeholder="Search" id="search-input-mobile">
		</div>
		<div class="bottom-nav-menu">
			<ul id="horizontal-list">
				<li class="active-menu-item"><a href="${pageContext.request.contextPath}/" id="home-mobile"
					class="item-mobile"></a></li>

				<li><a href="profile" role="button" id="profile-mobile"
					class="item-mobile"></a></li>

				<li><div id="add-mobile" class="item-mobile"></div></li>

				<li><a href="explore" id="explore-mobile" class="item-mobile"></a></li>

				<li><div id="notification-mobile" class="item-mobile"></div></li>
			</ul>
		</div>
		
		<div id="snackbar">
			<a href="#story-modal" id="create-story-btn" >
				<i class="fa fa-clock-o fa-2x" aria-hidden="true"> </i>
			</a>
			<a href="#create-post-modal" id="create-post-btn">
				<i class="fa fa-picture-o fa-2x" aria-hidden="true"> </i>
			</a>
		</div>
		
	</div>
	<nav id="navbar" class="transparent">
		<span class="nav-left"> <a href="${pageContext.request.contextPath}/"> <span id="logo"></span>
				<span id="logo-text">linstagram</span>
		</a>
		</span> <span id="search-form" class="form-inline">
			<div class="input-group" id="search-div">
				<input type="text" class="form-control transparent search-input"
					placeholder="Search" id="search-input-desktop">
			</div>
		</span> <span class="nav-right"> <a href="explore" id="explore"
			class="right-icon-nav"></a> <a href="#" id="notification"
			class="right-icon-nav"><span class="arrow hide"></span></a> <a
			href="#" role="button" id="profile" class="right-icon-nav"></a>
		</span>
	</nav>
</header>

<div id="notification_list" class="hide"></div>
<div id="notification_list_mobile" class="hide"></div>
<div id="hint-list-container">
	<span id="hint-list" class="hide"></span>
</div>


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


						/**
						 * Open the web socket connection and subscribe the "/notify" channel.
						 */
						function connect() {

							// Create and init the SockJS object
							var socket = new SockJS(
									'${pageContext.request.contextPath}/ws');
							var stompClient = Stomp.over(socket);

							// Subscribe the '/notify' channell
							stompClient.connect({}, function(frame) {
								stompClient.subscribe('/user/queue/notify',
										function(notification) {
											updateNotificationBadge();
										});
							});

							return;
						} // function connect

						/**
						 * Display the notification message.
						 */
						function updateNotificationBadge() {
							$
									.ajax({
										url : "notificationToSee",
										type : "POST",
										success : function(result) {
											if (result <= 0){
												$(".badge").remove();
											}
											if (result > 0) {
												var badge_notification = "<span class='badge'>"
														+ result + "</span>"
												$(".badge").remove();
												$("#notification").append(
														badge_notification);
												$("#notification-mobile")
														.append(
																badge_notification);
											}
										}
									});
						}

						// =========================================

						if ($("#navbar-mobile").css('display') != "none") {
							var localpathname = window.location.pathname;
							var res = localpathname.split("/");
							if (res[2] == "explore") {
								$("#horizontal-list li").removeClass(
									"active-menu-item");
								$("#explore-mobile").parent().addClass(
									"active-menu-item");
								$("#add-mobile").parent().css("display", "none");
							}
							else if (res[2] == "profile") {
								$("#horizontal-list li").removeClass(
										"active-menu-item");
								$("#profile-mobile").parent().addClass(
										"active-menu-item");
								$("#add-mobile").parent().css("display", "none");
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
						}).click(function(e) {
							e.preventDefault();
							$('#profile').popover('show');
							e.stopPropagation();
						});

						function closeProfilePopover() {
							$('#profile').popover('hide');
						}

						$('body').click(function(e) {
							closeProfilePopover();
						});

						window.addEventListener("scroll", closeProfilePopover);

						$('[data-toggle="tooltip"]').tooltip();

						 connect();
						 updateNotificationBadge();
					});
</script>