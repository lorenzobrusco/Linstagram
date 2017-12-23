<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/modify_profile.css">

<script type="text/javascript">
	
	$("#modifyPass-btn").click(function() {
		var old_pass = $("#old_password").val();
		var new_pass = $("#new_password").val();
		var repeat_pass = $("#repeat_password").val();
		
		var popup = document.getElementById("popupOK");
		var popupFail = document.getElementById("popupFAIL");
		
		$.ajax({url:"sendChangePassword", 
			data:{old_pass:old_pass, new_pass:new_pass, repeat_pass:repeat_pass},
			success: function(result) {
				if (result == "OK")
					 popup.classList.toggle("show");
				else {
					$('#text').text(result);
					popupFail.classList.toggle("show");
				}
			}
		})
	});
	
	$('.close').on('click', function () {
	  	var popup = document.getElementById("popupOK");
		popup.classList.remove("show"); 
		var popup = document.getElementById("popupFAIL");
		popup.classList.remove("show"); 
	});
	

</script>

<div class='modify-profile'>
	<jsp:include page="./modifyProfileHeader.jsp"></jsp:include>

	<div class="info">
		<form>
			<div class="form-inline-profile">
				<label for="old_password">Old Password</label>
				<div class="input-inline">
					<input type="text" class="form-control" id="old_password">
				</div>
			</div>

			<br> <br>

			<div class="form-inline-profile">
				<label for="new_password">New Password</label>
				<div class="input-inline">
					<input type="text" class="form-control" id="new_password">
				</div>
			</div>
			<div class="form-inline-profile">
				<label for="repeat_password">Repeat Password</label>
				<div class="input-inline">
					<input type="text" class="form-control" id="repeat_password">
				</div>
			</div>

			<jsp:include page="./modifyUserDataPopup.jsp"></jsp:include>


		</form>
	</div>
</div>
<button type="button" class="btn confirm-btn pull-right"
	id="modifyPass-btn">Submit</button>