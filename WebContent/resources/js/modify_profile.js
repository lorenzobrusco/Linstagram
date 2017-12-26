$(document).ready(function() {

	var notysuccess= {
			text: '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Complete!</p> The changes were completed!',
			theme: 'nest',
			type: 'success',
			layout: 'bottomLeft',
			timeout: 2000,
			progressBar: true
	}

	var notyerror={
			text: '<p style="color:black;font-weight:bold;text-transform: uppercase;">Operation Failed!</p> The changes have not been made!',
			theme: 'nest',
			type: 'error',
			layout: 'bottomLeft',
			timeout:4000,
			progressBar: true
	}

	function showResultMessage(result){
		if (result == "OK"){
			new Noty(notysuccess).show();
			setTimeout(location.reload.bind(location), notysuccess.timeout); //wait 2 second and then reload
		}else {
			new Noty(notyerror).show();
		}
	}

	$("#modify-btn").click(function() {
		var name = $("#name").val()
		var surname = $("#surname").val()
		var username = $("#user-name").val()
		var email = $("#email").val()
		var selection = $("#selection").val()
		var date = $("#datepicker").val()
		var checkbox = $("#check").val()
		var bio = $("#bio").val()
		var popup = document.getElementById("popupOK");
		var popupFail = document.getElementById("popupFAIL");

		/* name+" "+ username+" "+ email+ " "+ sesso+ " "+ bio */
		$.ajax({url:"sendInfoProfile", 
			data:{name:name, surname:surname, username:username, email:email, sesso:selection, date:date, bio:bio, privateCheck:checkbox},
			success: function(result) {
				showResultMessage(result);
			}	
		})
	});


	$('.nav-pills').on('click', 'li', function() {
		$('.nav-pills li.active-nav').removeClass('active-nav');
		$(this).addClass('active-nav');
	});

	$("#changePassword").click(function() {
		$.ajax(
				{url:"changePasswordPage", 
					success: function(result) {
						$('#form-container').empty();
						$('#form-container').append(result);
					}	
				})
	});

	$("#changeInfoUser").click(function() {
		$.ajax({url:"changeInfoUserPage", 
			success: function(result) {
				$('#form-container').empty();
				$('#form-container').append(result);
			}	
		})
	});

	$("textarea").keyup(function() {
		if ($(this).val() != "") {
			$("button").prop("disabled", false);
		} else {
			$("button").prop("disabled", true);
		}
	});

	$('#selection').change(function() {
		if($(this).val() != -1) {          
			$('button').removeAttr("disabled");}
		else 
			$('button').prop("disabled", true);
	});

	$('#datepicker').change(function() {
		if($(this).val() != "") {          
			$('button').removeAttr("disabled");}
		else 
			$('button').prop("disabled", true);
	});

	$('input[type="email"]').keyup(function() {
		if($(this).val() != '') {
			$('button').prop('disabled', false);
		}
		else 
			$('button').prop('disabled', true);
	});

	$(':text').on('input', function() {
		if( $(':text').filter(function() { return !!this.value; }).length > 0 ) {
			$('button').prop('disabled', false);
		} else {
			$('button').prop('disabled', true);
		}
	});


$(function () {
	$("#datepicker").datepicker();
});


function verify_check(cb) {
	// .checked considera nel momento in cui si trasforma [quindi si ragiona al contrario]
	if (document.getElementById('checkbox').checked) 
		document.getElementById('check').value = "true";
	else
		document.getElementById('check').value = "false";

	if ($("button").is(":disabled"))
		$('button').prop('disabled', false);
	else
		$('button').prop('disabled', true);
};



//EVENT FOR PASSWORD'S PAGE

$("#modifyPass-btn").click(function() {
	var old_pass = $("#old_password").val();
	var new_pass = $("#new_password").val();
	var repeat_pass = $("#repeat_password").val();

	$.ajax({url:"sendChangePassword", 
		data:{old_pass:old_pass, new_pass:new_pass, repeat_pass:repeat_pass},
		success: function(result) {
			showResultMessage(result);
		}
	})
});

$(':password').on('input', function() {
	if( $(':password').filter(function() { return !!this.value; }).length > 0 ) {
		$('button').prop('disabled', false);
	} else {
		$('button').prop('disabled', true);
	}
});


});
