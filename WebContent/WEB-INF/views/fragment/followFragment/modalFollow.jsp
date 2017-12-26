<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ taglib uri="/WEB-INF/custom-functions.tld" prefix="cf" %> --%>


<!DOCTYPE html>
<html>
<head>
<title>Listagram</title>
<script src="./resources/js/follow_event/modal_follow_event.js"></script>
</head>
<body>

<!-- Modal -->
	<div class="modal fade" id="modalFollowing" role="dialog">
  		<div class="modal-dialog">
	  
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title"><b>Followings</b></h4>
		      </div>
		      <div class="modal-body">
	      		<ul>
        			<c:forEach items="${followings}" var="follow">
        				<li id="users">
        					<div id="user_enter">
        						<form role="form" action="userPage">
	        						<button id="button_form" name="usernameOther" value="${follow.username }">
		        						<div id="button_user">
			        						<div id="img_div">
	        									<img id="img" src="${follow.photoProfile }"></img>
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