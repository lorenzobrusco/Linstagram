<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/signin_style.css">
</head>

<body>
  <div class="row" id="container">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
      <div id="logo-container">
        <span id="logo"></span>
        <h1 id="logo-text">linstagram</h1>
      </div>
      <div class="login-wrap">
        <div class="login-html">
          <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
          <label for="tab-1" class="tab">Sign In</label> <input id="tab-2" type="radio" name="tab" class="sign-up"> <label for="tab-2" class="tab">Sign Up</label>

          <div class="login-form">
            <form class="sign-in-htm" action="signInAttempt" method="post">
              <div class="group">
                <label for="user" class="label">Username</label> <input id="user-singin" name="username" type="text" class="input" required>
              </div>
              <div class="group">
                <label for="pass" class="label">Password</label> <input id="pass" name="password" type="password" class="input" data-type="password" required>
              </div>
              <!-- <div class="group">
<input id="check" type="checkbox" class="check" checked>
<label for="check"><span class="icon"></span> Keep me
Signed in</label>
</div> -->
              <div class="group">
                <input type="submit" class="button" value="Sign In">
              </div>
              <div class="hr"></div>
              <div class="group">
                <input class="button facebook" value="Sign in with Facebook">
              </div>
              <!-- <div class="foot-lnk">
<a href="#forgot">Forgot Password?</a>
</div> -->
            </form>

            <form action="signUpAttempt" method="post" class="sign-up-htm">
              <div class="group">
                <label for="user" class="label">Username</label> <input id="user" name="username" type="text" class="input" required>
              </div>
              <div class="group">
                <label for="email" class="label">Email Address</label> <input id="email" type="email" name="email" class="input" required>
              </div>
              <div class="group">
                <label for="password-field" class="label">Password</label> <input name="password" type="password" class="input" data-type="password" id="password-field" minlength=6 required>
              </div>
              <div class="group">
                <label for="password-repeat" class="label">Repeat
                    Password</label> <input type="password" class="input" data-type="password" id="password-repeat" minlength=6 required>
                <div class="error-message hide" id="error-message-pass">Passwords do not match.</div>
              </div>
              <div class="hr"></div>
              <div class="group">
                <input type="submit" class="button" id="signup-btn" value="Sign Up">
              </div>
              <div class="foot-lnk"></div>
            </form>

          </div>
        </div>
      </div>
    </div>
  </div>
  <!--  Container close   -->
</body>
<script type="text/javascript">
  $(document).ready(function() {
    var input = $('input');
    input.on("focusin", function() {
      $(".login-html").addClass("isFocused");
    });

    input.on("focusout", function() {
      $(".login-html").removeClass("isFocused");
    });

    var pass = $("#password-field");
    var pass_repeat = $("#password-repeat");

    $("#signup-btn").on("click", function(e) {
      if (pass.val() != pass_repeat.val()) {
        $("#error-message-pass").removeClass("hide");
        pass.addClass("input-with-error");
        pass_repeat.addClass("input-with-error");
        pass.focus();
        //	e.preventDefault();
      } else {
        $("#error-message-pass").addClass("hide");
        pass.removeClass("input-with-error");
        pass_repeat.removeClass("input-with-error");
      }
    });

  });

</script>

</html>
