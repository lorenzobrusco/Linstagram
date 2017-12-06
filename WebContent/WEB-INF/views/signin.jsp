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
	href="${pageContext.request.contextPath}/resources/css/home.css">
</head>
<style>
*, *:before, *:after {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	font-family: 'Open Sans', Helvetica, Arial, sans-serif;
	background: #ededed;
}

input, button {
	border: none;
	outline: none;
	background: none;
	font-family: 'Open Sans', Helvetica, Arial, sans-serif;
}

.tip {
	font-size: 20px;
	margin: 40px auto 50px;
	text-align: center;
}

.cont {
	overflow: hidden;
	position: relative;
	width: 900px;
	height: 550px;
	margin: 0 auto 100px;
	background: #fff;
}

.form {
	position: relative;
	width: 640px;
	height: 100%;
	-webkit-transition: -webkit-transform 1.2s ease-in-out;
	transition: -webkit-transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out, -webkit-transform 1.2s
		ease-in-out;
	padding: 50px 30px 0;
}

.sub-cont {
	overflow: hidden;
	position: absolute;
	left: 640px;
	top: 0;
	width: 900px;
	height: 100%;
	padding-left: 260px;
	background: #fff;
	-webkit-transition: -webkit-transform 1.2s ease-in-out;
	transition: -webkit-transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out, -webkit-transform 1.2s
		ease-in-out;
}

.cont.s--signup .sub-cont {
	-webkit-transform: translate3d(-640px, 0, 0);
	transform: translate3d(-640px, 0, 0);
}

button {
	display: block;
	margin: 0 auto;
	width: 260px;
	height: 36px;
	border-radius: 30px;
	color: #fff;
	font-size: 15px;
	cursor: pointer;
}

.img {
	overflow: hidden;
	z-index: 2;
	position: absolute;
	left: 0;
	top: 0;
	width: 260px;
	height: 100%;
	padding-top: 360px;
}

.img:before {
	content: '';
	position: absolute;
	right: 0;
	top: 0;
	width: 900px;
	height: 100%;
	background-image:
		url("https://s3-us-west-2.amazonaws.com/s.cdpn.io/142996/sections-3.jpg");
	background-size: cover;
	-webkit-transition: -webkit-transform 1.2s ease-in-out;
	transition: -webkit-transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out, -webkit-transform 1.2s
		ease-in-out;
}

.img:after {
	content: '';
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.6);
}

.cont.s--signup .img:before {
	-webkit-transform: translate3d(640px, 0, 0);
	transform: translate3d(640px, 0, 0);
}

.img__text {
	z-index: 2;
	position: absolute;
	left: 0;
	top: 50px;
	width: 100%;
	padding: 0 20px;
	text-align: center;
	color: #fff;
	-webkit-transition: -webkit-transform 1.2s ease-in-out;
	transition: -webkit-transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out, -webkit-transform 1.2s
		ease-in-out;
}

.img__text h2 {
	margin-bottom: 10px;
	font-weight: normal;
}

.img__text p {
	font-size: 14px;
	line-height: 1.5;
}

.cont.s--signup .img__text.m--up {
	-webkit-transform: translateX(520px);
	transform: translateX(520px);
}

.img__text.m--in {
	-webkit-transform: translateX(-520px);
	transform: translateX(-520px);
}

.cont.s--signup .img__text.m--in {
	-webkit-transform: translateX(0);
	transform: translateX(0);
}

.img__btn {
	overflow: hidden;
	z-index: 2;
	position: relative;
	width: 100px;
	height: 36px;
	margin: 0 auto;
	background: transparent;
	color: #fff;
	text-transform: uppercase;
	font-size: 15px;
	cursor: pointer;
}

.img__btn:after {
	content: '';
	z-index: 2;
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	border: 2px solid #fff;
	border-radius: 30px;
}

.img__btn span {
	position: absolute;
	left: 0;
	top: 0;
	display: -webkit-box;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-pack: center;
	-ms-flex-pack: center;
	justify-content: center;
	-webkit-box-align: center;
	-ms-flex-align: center;
	align-items: center;
	width: 100%;
	height: 100%;
	-webkit-transition: -webkit-transform 1.2s;
	transition: -webkit-transform 1.2s;
	transition: transform 1.2s;
	transition: transform 1.2s, -webkit-transform 1.2s;
}

.img__btn span.m--in {
	-webkit-transform: translateY(-72px);
	transform: translateY(-72px);
}

.cont.s--signup .img__btn span.m--in {
	-webkit-transform: translateY(0);
	transform: translateY(0);
}

.cont.s--signup .img__btn span.m--up {
	-webkit-transform: translateY(72px);
	transform: translateY(72px);
}

h2 {
	width: 100%;
	font-size: 26px;
	text-align: center;
}

label {
	display: block;
	width: 260px;
	margin: 25px auto 0;
	text-align: center;
}

label span {
	font-size: 12px;
	color: #cfcfcf;
	text-transform: uppercase;
}

input {
	display: block;
	width: 100%;
	margin-top: 5px;
	padding-bottom: 5px;
	font-size: 16px;
	border-bottom: 1px solid rgba(0, 0, 0, 0.4);
	text-align: center;
}

.forgot-pass {
	margin-top: 15px;
	text-align: center;
	font-size: 12px;
	color: #cfcfcf;
}

.submit {
	margin-top: 40px;
	margin-bottom: 20px;
	background: #d4af7a;
	text-transform: uppercase;
}

.fb-btn {
	border: 2px solid #d3dae9;
	color: #8fa1c7;
}

.fb-btn span {
	font-weight: bold;
	color: #455a81;
}

.sign-in {
	-webkit-transition-timing-function: ease-out;
	transition-timing-function: ease-out;
}

.cont.s--signup .sign-in {
	-webkit-transition-timing-function: ease-in-out;
	transition-timing-function: ease-in-out;
	-webkit-transition-duration: 1.2s;
	transition-duration: 1.2s;
	-webkit-transform: translate3d(640px, 0, 0);
	transform: translate3d(640px, 0, 0);
}

.sign-up {
	-webkit-transform: translate3d(-900px, 0, 0);
	transform: translate3d(-900px, 0, 0);
}

.cont.s--signup .sign-up {
	-webkit-transform: translate3d(0, 0, 0);
	transform: translate3d(0, 0, 0);
}

.icon-link {
	position: absolute;
	left: 5px;
	bottom: 5px;
	width: 32px;
}

.icon-link img {
	width: 100%;
	vertical-align: top;
}

.icon-link--twitter {
	left: auto;
	right: 5px;
}
</style>
<body>
	<div class="cont">
		<div class="form sign-in">
			<h2>Welcome back,</h2>
			<label> <span>Email</span> <input type="email" />
			</label> <label> <span>Password</span> <input type="password" />
			</label>
			<p class="forgot-pass">Forgot password?</p>
			<button type="button" class="submit">Sign In</button>
			<button type="button" class="fb-btn">
				Connect with <span>facebook</span>
			</button>
		</div>
		<div class="sub-cont">
			<div class="img">
				<div class="img__text m--up">
					<h2>New here?</h2>
					<p>Sign up and discover great amount of new opportunities!</p>
				</div>
				<div class="img__text m--in">
					<h2>One of us?</h2>
					<p>If you already has an account, just sign in. We've missed
						you!</p>
				</div>
				<div class="img__btn">
					<span class="m--up">Sign Up</span> <span class="m--in">Sign
						In</span>
				</div>
			</div>
			<div class="form sign-up">
				<h2>Time to feel like home,</h2>
				<label> <span>Name</span> <input type="text" />
				</label> <label> <span>Email</span> <input type="email" />
				</label> <label> <span>Password</span> <input type="password" />
				</label>
				<button type="button" class="submit">Sign Up</button>
				<button type="button" class="fb-btn">
					Join with <span>facebook</span>
				</button>
			</div>
		</div>
	</div>

</body>


<script>
	document.querySelector('.img__btn').addEventListener('click', function() {
		document.querySelector('.cont').classList.toggle('s--signup');
	});
</script>

</html>
