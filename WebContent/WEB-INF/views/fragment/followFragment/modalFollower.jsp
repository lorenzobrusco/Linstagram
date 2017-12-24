<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<script src="./resources/js/modal_follow_event.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal_follow_style.css">

</head>
<body>

<!-- Modal -->
	<div class="modal fade" id="modalFollower" role="dialog">
  		<div class="modal-dialog">
	  
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title"><b>Followers</b></h4>
		      </div>
		      <div class="modal-body">
	      		<ul>
        			<c:forEach items="${user.followers}" var="follow">
        				<li id="users">
        					<div id="user_enter">
        						<form role="form" action="userPage">
	        						<button id="button_form" name="usernameOther" value="${follow.username }">
		        						<div id="button_user">
			        						<div id="img_div">
			        							<c:choose>
				        							<c:when test="${empty follow.photoProfile }">
				        								<img id="img" src="https://www.1plusx.com/app/mu-plugins/all-in-one-seo-pack-pro/images/default-user-image.png"></img>
				       								</c:when>
				       								<c:otherwise>
			        									<img id="img" src="${follow.photoProfile }"></img>
			        								</c:otherwise>
			        							</c:choose>
			        						</div>
			        							
			        						<div id="info_div">
			        							<div id="username${follow.id }"><b>${follow.username }</b></div>
			        							<div id="name">${follow.name } ${follow.surname }</div>
			        						</div>
		        						</div>
	        						</button>
        						</form>
        						<c:if test="${follow.username != userSession.username }">
	        						<div id="button${follow.id }" class="pull-right">
	        							<button name="${follow.id }" value="${follow.username }" id="unfollow-btn">Unfollow</button>
	        						</div>
        						</c:if>
        					</div>
        				</li>
        			</c:forEach>
       			</ul>
		      </div>
<!-- 		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div> -->
		      
		    </div>
	    
  		</div>
	</div>
  
</body>