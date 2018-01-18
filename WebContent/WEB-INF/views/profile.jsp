<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon-32x32.png" />
<link rel="icon" href="${pageContext.request.contextPath}/resources/images/favicon.icon" type="image/x-icon" />

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

<script src="./resources/js/eventLoadPost.js"></script>
<script src="./resources/js/follow_event/follower_event.js"></script>
<script src="./resources/js/follow_event/reload_user_follow_popup.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/profile_style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/modal_follow_style.css">
	
	<!-- NOTY -->	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/noty/noty.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/noty/themes/nest.css">
<script
	src="${pageContext.request.contextPath}/resources/js/lib/noty.min.js"></script>

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
								<img src='${user.photoProfile}' class="img-circle">
							</div>
						</div>
					</div>
					<div class="col-sm-5">
						<div class="row item-user-info">
							<ul>
								<li><b>@${user.username}</b></li>
								<li><a class="btn btn-default" href="modifyProfile" id="modify-profile-btn">Edit Profile</a></li>
								<li><a class="btn btn-danger" href="logout" id="logout-mobile-btn">Logout</a></li>
							</ul>
							<input id="username_hidden" type="hidden" name="username_hidden"
								value="${user.username}" />
						</div>
						<div class="row item-user-info">
							<ul>
								<li><span><b>${postsCount}</b></span> post</li>
								<li id="follower" data-toggle="modal"
									data-target="#modalFollower"><span><b>${numberOfFollowers}</b></span>
									follower</li>
								<li id="following" data-toggle="modal"
									data-target="#modalFollowing"><span><b id="count_following">${numberOfFollowings}</b></span>
									profiles followed</li>
							</ul>
						</div>
						<div class="row item-user-info">
							<ul>
								<c:set var="name" value="${user.name}" />
								<c:set var="surname" value="${user.surname}" />
								<c:if test="${ empty name  && empty surname}">
									<li><i>Name & Surname unknown</i></li>
								</c:if>
								<c:if test="${ not empty name  || not empty surname}">
									<li>${user.name} ${user.surname}</li>
								</c:if>
							</ul>
						</div>
						<div class="row item-user-info">
							<ul>
								<%-- <c:set var="bio" value="${user.biography}" /> --%>
								<c:if test="${not empty user.biography}">
									<!-- <hr> -->
									<li>${user.biography }</li>
								</c:if>
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
									<li class="active"><a id="post_user" href="#tab_default_1"
										data-toggle="tab"> Posts </a></li>
									<li>
										<a id="tags" href="#tab_default_2" data-toggle="tab">
											Tags </a></li>
									<li><a id="bookmarks" href="#tab_default_3"
										data-toggle="tab"> Bookmarks </a></li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active" id="tab_default_1">
										<div id="colum">
											<jsp:include
												page="./fragment/userProfileFragment/postSection.jsp"></jsp:include>

										</div>
									</div>
									<div class="tab-pane" id="tab_default_2">
										<div id="tag"></div>
									</div>
									<div class="tab-pane" id="tab_default_3">
										<div id="bookmark"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	
	<div id="loading" class="fixed-bottom hide text-center"><img class="img-fluid" alt="" src="${pageContext.request.contextPath}/resources/images/loader.gif"></div>
	
	<jsp:include page="./fragment/footer.jsp"></jsp:include>
	<jsp:include page="./fragment/followFragment/modalFollow.jsp"></jsp:include>
	<jsp:include page="./fragment/followFragment/modalFollower.jsp"></jsp:include>
	
</body>

<script>
//Inifinity scroll
var entered=false;

function lazyLoadContent(active_tab){
	$("#loading").removeClass("hide");
	
	console.log(active_tab);
	var url="";
	var append_element="";
	if( active_tab=="Posts") {
		url="postPhoto";
		append_element = "#colum";
	}
	else if( active_tab=="Tags") {
		url="taggedPhoto";
		append_element = "#tag";
	}
	else if( active_tab=="Bookmarks") {
		url="bookmarkPhoto";
		append_element = "#bookmark";
	}
	else{
		console.error("error active tab");
		return;
	}
	
	var listSize = $(append_element).children(".post-section").length;
	console.log(listSize);
	
	setTimeout(function(){

		$.ajax({
			url:url, 
			data:{
				username:'${user.username}',
				lastIndex:listSize },
			success: function(result) {
				console.log(listSize);
				//console.log(result);
				//console.log(append_element);
				var html = $.parseHTML(result);
				console.log(html);
				var empty = false;
				for(var i=0; i < html.length;i++){
					if($(html[i]).hasClass("empty_post") || $(html[i]).hasClass("bookmark") || $(html[i]).hasClass("tags")){
							empty=true;
					}
				}
				if($(".empty_post").length != 0) {
					$(".empty_post").remove();
				}
				if($(".bookmark").length != 0) {
					$(".bookmark").remove();
				}
				if($(".tags").length != 0) {
					$(".tags").remove();
				}

				if(!empty || listSize == 0)
					$(append_element).append(result);
				$("#loading").addClass("hide");
				entered=false;
			}	
		}) 		 
	},1000);
}

$(document).ready(function(){
	var lastScrollTop = 0;
	
	$(window).scroll(function(){
	
		var st = $(this).scrollTop();
	
		if (st > lastScrollTop){
	
			if (($(document).height() -  $(window).height()) <=  $(window).scrollTop()+10 && !entered) {
				entered=true;
				var active_tab=$(".nav  > li.active").text().replace(/\s/g, '');
				lazyLoadContent(active_tab);
				console.log("Active tab scroll:"+active_tab);
			}
		}
		lastScrollTop = st;
	});
});

</script>

</html>
