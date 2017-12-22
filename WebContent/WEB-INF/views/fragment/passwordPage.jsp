<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modify_profile.css">

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
	<div class="photo">
		<div class="form-inline-profile">
			<div class="input-inline">
				<img class="user-img" src="resources/images/user_login_img.png">
				<label for="name">PEPPE PEPPE</label><br>
			</div>
		</div>
	</div>
	<div class="info">
		<form>
			<div class="form-inline-profile">
				<label for="old_password">Old Password</label>
				<div class="input-inline">
					<input type="text" class="form-control" id="old_password">
				</div>
			</div>
			
			<br>
			<br>
			
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
					
			<div class="popup popupOK">
				<span class="popuptext alert" id="popupOK">
				  <a  onclick="close" class="close">&times;</a>
				  <strong>Success!</strong> Le modifiche sono state effettuate.
				</span>
			</div>
			<div class="popup popupFAIL">
				<span class="popuptext alert" id="popupFAIL">
				  <a  onclick="close" class="close">&times;</a>
				  <br>
				  <br>
				  <a id="text"></a>
				</span>
			</div>
					
						
		</form>
	</div>
</div>
<button type="button" class="btn btn-outline-primary pull-right" id="modifyPass-btn" style="margin: 0% 10% 2% 0%;">Submit</button>
