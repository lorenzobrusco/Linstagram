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

<header>
	<div id="rainbow-progress-bar"></div>
	<div id="navbar-mobile">
		<span id="logo-container"><a href="index" id="logo"></a></span>
		<div class="search-bar">
			<input type="text" class="form-control transparent" placeholder="Search" id="search-input">
		</div>
		<div class="bottom-nav-menu">
			<ul id="horizontal-list">
				<li class="active-menu-item"><a href="index" id="home-mobile"
					class="item-mobile"></a></li>
				<li><a href="profile" role="button" id="profile-mobile"
					class="item-mobile"></a></li>
				<li><a href="#create-post-modal" id="add-mobile"
					class="item-mobile"></a></li>
				<li><a href="" id="notification-mobile" class="item-mobile"></a>
				</li>
			</ul>
		</div>
	</div>
	<nav id="navbar" class="transparent">
		<span class="nav-left"> <a href="index"> <span id="logo"></span>
				<span id="logo-text">linstagram</span>
		</a>
		</span> <span id="search-form" class="form-inline">
			<div class="input-group" id="search-div">
				<input type="text" class="form-control transparent" placeholder="Search" id="search-input-desktop">
			</div>
		</span> <span class="nav-right"> <a href="" id="explore"
			class="right-icon-nav disabled" data-toggle="tooltip"
			data-placement="left" data-original-title="Coming Soon"></a> <a
			href="#"" id="notification" class="right-icon-nav"><span class="arrow hide"></span></a>
			<a href="#" role="button" id="profile" class="right-icon-nav"></a>
		</span>
	</nav>
</header>

<div id="notification_list" class="hide"></div>

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
							trigger : "manual", //click
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

						//TODO not work	

						/*$('#search-input-desktop').focusin(function() {
							$("#search-div").css("width", "70%");
						});

						$('#search-input-desktop').focusout(function() {
							$("#search-div").css("width", "50%");
						});*/

						//TODO search on mobile	
						
						
						$('#search-input-desktop').keyup(function() {
							var text = $("#search-input-desktop").val();
							console.log(text);

							$.ajax({
								url : "research",
								type : "POST",
								data : {
									text : text
								},
								success : function(result) {
									console.log(result);
									var hint= {
											data: [],
											getValue: "name",
											template: {
												type: "iconLeft",
												fields: {
													iconSrc: "icon"
												}
											}
										};
									$.each(result,function( key, value ){
										//console.log(key);
										var title = value.title;
										var subtitle = value.subtitle;
										var iconUrl = value.iconUrl;
										var item_body="<b>"+title+"<b><br>"+ subtitle;
										//console.log("<b>"+title+"<b><br>"+ subtitle+"-"+ iconUrl);
										hint.data.push("{name: "+item_body+", icon:"+ iconUrl +"}");
									});
									console.log(hint);

								}
							});

						});

					});
	
	
	
</script>
<script
	src="${pageContext.request.contextPath}/resources/js/follow_event/follow_event_profile_public.js"></script>