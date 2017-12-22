$(document).ready(function() {
	$("#modify-btn").click(function() {
		var name = $("#name").val()
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
			data:{name:name, username:username, email:email, sesso:selection, date:date, bio:bio, privateCheck:checkbox},
			success: function(result) {
				if (result == "OK")
					 popup.classList.toggle("show");
				else {
					$('#text').text(result);
					popupFail.classList.toggle("show");
				}
		}	
		})
	})

	$('.close').on('click', function () {
	  	var popup = document.getElementById("popupOK");
		popup.classList.remove("show"); 
		var popup = document.getElementById("popupFAIL");
		popup.classList.remove("show"); 
	})
	

	
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
};

