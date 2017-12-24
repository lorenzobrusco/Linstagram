<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modifyUserDataPopup_style.css">

<div class="popup popupOK">
	<span class="popuptext alert" id="popupOK"> <a onclick="close"
		class="close">&times;</a> <strong>Success!</strong> 
		Le modifiche sono state effettuate.
	</span>
</div>
<div class="popup popupFAIL">
	<span class="popuptext alert" id="popupFAIL">
	 <a onclick="close" class="close">&times;</a> <br> <br> <a id="text"></a>
	</span>
</div>