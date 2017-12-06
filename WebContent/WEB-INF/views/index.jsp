<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/salvattore.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/home_style.css">
</head>
<body>

	<h1 class="col-sm-12 centered">Prototype Homepage Linstagram</h1>

	<div id="grid" data-columns>
		<div class="card">
			<img class="picture" src="${pageContext.request.contextPath}/resources/images/picture3.jpg">
			<div class="content">
				<a href="#"
					class="fa fa-bookmark-o bookmark-lbl float-right menu-icon"></a>
				<!-- Author info (avatar and name) -->
				<h3 class="author">
					<img class="avatar" src="${pageContext.request.contextPath}/resources/images/avatar.jpg" /> <a href="#">Sheldon
						Cooper</a>
				</h3>
				<hr class="separator" />
				<!-- Post description -->
				<p class="text">Lorem ipsum dolor sit amet, consectetuer
					adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa.
					Cum sociis natoque penatibus et magnis dis parturient montes,
					nascetur ridiculus mus. Donec quam felis, ultricies nec.</p>
			</div>
			<!-- Like and comment icon -->
			<div class="icon-bar">
				<ul class="menu-content">
					<li><a href="#"
						class="fa fa-comment-o comment-lbl float-right menu-icon"></a> <span
						class="comment-value float-right stat-value">830</span></li>
					<li><a href="#" class="fa fa-heart-o like-lbl menu-icon"></a>
						<span class="like-value stat-value">472</span></li>
				</ul>
			</div>
		</div>

		<div class="card">
			<img class="picture" src="${pageContext.request.contextPath}/resources/images/picture5.jpg">
			<div class="content">
				<a href="#"
					class="fa fa-bookmark-o bookmark-lbl float-right menu-icon"></a>
				<!-- Author info (avatar and name) -->
				<h3 class="author">
					<img class="avatar" src="${pageContext.request.contextPath}/resources/images/avatar.jpg" /> <a href="#">Sheldon
						Cooper</a>
				</h3>
				<hr class="separator" />
				<!-- Post description -->
				<p class="text">Nam quam nunc, blandit vel, luctus pulvinar,
					hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus.
					Donec vitae sapien ut libero venenatis faucibus.</p>
			</div>
			<!-- Like and comment icon -->
			<div class="icon-bar">
				<ul class="menu-content">
					<li><a href="#"
						class="fa fa-comment-o comment-lbl float-right menu-icon"></a> <span
						class="comment-value float-right stat-value">830</span></li>
					<li><a href="#" class="fa fa-heart-o like-lbl menu-icon"></a>
						<span class="like-value stat-value">472</span></li>
				</ul>
			</div>
		</div>

		<div class="card">
			<img class="picture" src="${pageContext.request.contextPath}/resources/images/picture.jpg">
			<div class="content">
				<a href="#"
					class="fa fa-bookmark-o bookmark-lbl float-right menu-icon"></a>
				<!-- Author info (avatar and name) -->
				<h3 class="author">
					<img class="avatar" src="${pageContext.request.contextPath}/resources/images/avatar.jpg" /> <a href="#">Sheldon
						Cooper</a>
				</h3>
				<hr class="separator" />
				<!-- Post description -->
				<p class="text">Aenean vulputate eleifend tellus.</p>
			</div>
			<!-- Like and comment icon -->
			<div class="icon-bar">
				<ul class="menu-content">
					<li><a href="#"
						class="fa fa-comment-o comment-lbl float-right menu-icon"></a> <span
						class="comment-value float-right stat-value">830</span></li>
					<li><a href="#" class="fa fa-heart-o like-lbl menu-icon"></a>
						<span class="like-value stat-value">472</span></li>
				</ul>
			</div>
		</div>


		<!-- LINE TWO -->

		<div class="card">
			<img class="picture" src="${pageContext.request.contextPath}/resources/images/picture.jpg">
			<div class="content">
				<a href="#"
					class="fa fa-bookmark-o bookmark-lbl float-right menu-icon"></a>
				<!-- Author info (avatar and name) -->
				<h3 class="author">
					<img class="avatar" src="${pageContext.request.contextPath}/resources/images/avatar.jpg" /> <a href="#">Sheldon
						Cooper</a>
				</h3>
				<hr class="separator" />
				<!-- Post description -->
				<p class="text">Donec sodales sagittis magna. Sed consequat, leo
					eget bibendum sodales, augue velit cursus nunc.</p>
			</div>
			<!-- Like and comment icon -->
			<div class="icon-bar">
				<ul class="menu-content">
					<li><a href="#"
						class="fa fa-comment-o comment-lbl float-right menu-icon"></a> <span
						class="comment-value float-right stat-value">830</span></li>
					<li><a href="#" class="fa fa-heart-o like-lbl menu-icon"></a>
						<span class="like-value stat-value">472</span></li>
				</ul>
			</div>
		</div>

		<div class="card">
			<img class="picture" src="${pageContext.request.contextPath}/resources/images/picture2.jpg">
			<div class="content">
				<a href="#"
					class="fa fa-bookmark-o bookmark-lbl float-right menu-icon"></a>
				<!-- Author info (avatar and name) -->
				<h3 class="author">
					<img class="avatar" src="${pageContext.request.contextPath}/resources/images/avatar.jpg" /> <a href="#">Sheldon
						Cooper</a>
				</h3>
				<hr class="separator" />
				<!-- Post description -->
				<p class="text">Li Europan lingues es membres del sam familie.
					Lor separat existentie es un myth. Por scientie, musica, sport etc,
					litot Europa usa li sam vocabular.</p>
			</div>
			<!-- Like and comment icon -->
			<div class="icon-bar">
				<ul class="menu-content">
					<li><a href="#"
						class="fa fa-comment-o comment-lbl float-right menu-icon"></a> <span
						class="comment-value float-right stat-value">830</span></li>
					<li><a href="#" class="fa fa-heart-o like-lbl menu-icon"></a>
						<span class="like-value stat-value">472</span></li>
				</ul>
			</div>
		</div>

		<div class="card">
			<img class="picture" src="${pageContext.request.contextPath}/resources/images/picture4.jpg">
			<div class="content">
				<a href="#"
					class="fa fa-bookmark-o bookmark-lbl float-right menu-icon"></a>
				<!-- Author info (avatar and name) -->
				<h3 class="author">
					<img class="avatar" src="${pageContext.request.contextPath}/resources/images/avatar.jpg" /> <a href="#">Sheldon
						Cooper</a>
				</h3>
				<hr class="separator" />
				<!-- Post description -->
				<p class="text">Li Europan lingues es membres del sam familie.
					Lor separat existentie es un myth. Por scientie, musica, sport etc,
					litot Europa usa li sam vocabular. Li lingues differe solmen in li
					grammatica, li pronunciation e li plu commun vocabules. Omnicos
					directe al desirabilite de un nov lingua franca: On refusa
					continuar payar custosi traductores. At solmen va esser necessi far
					uniform grammatica, pronunciation e plu sommun paroles. Ma quande
					lingues coalesce, li grammatica del resultant lingue es plu simplic
					e regulari quam ti del coalescent lingues. Li nov lingua franca va
					esser plu simplic e regulari quam li existent Europan lingues. It
					va esser tam simplic quam Occidental in fact, it va esser
					Occidental. A un Angleso it va semblar un simplificat Angles, quam
					un skeptic Cambridge amico dit me que Occidental es.Li Europan
					lingues es membres del sam familie. Lor separat existentie es un
					myth. Por scientie, musica, sport etc, litot Europa usa li sam
					vocabular. Li lingues differe solmen in li grammatica, li
					pronunciation e li plu commun vocabules. Omnicos directe al
					desirabilite de un nov lingua franca: On refusa continuar payar
					custosi traductores. At solmen va esser necessi far uniform
					grammatica, pronunciation e plu sommun paroles.</p>
			</div>
			<!-- Like and comment icon -->
			<div class="icon-bar">
				<ul class="menu-content">
					<li><a href="#"
						class="fa fa-comment-o comment-lbl float-right menu-icon"></a> <span
						class="comment-value float-right stat-value">830</span></li>
					<li><a href="#" class="fa fa-heart-o like-lbl menu-icon"></a>
						<span class="like-value stat-value">472</span></li>
				</ul>
			</div>
		</div>

		<div class="card">
			<img class="picture" src="${pageContext.request.contextPath}/resources/images/picture5.jpg">
			<div class="content">
				<a href="#"
					class="fa fa-bookmark-o bookmark-lbl float-right menu-icon"></a>
				<!-- Author info (avatar and name) -->
				<h3 class="author">
					<img class="avatar" src="${pageContext.request.contextPath}/resources/images/avatar.jpg" /> <a href="#">Sheldon
						Cooper</a>
				</h3>
				<hr class="separator" />
				<!-- Post description -->
				<p class="text">Nam quam nunc, blandit vel, luctus pulvinar,
					hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus.
					Donec vitae sapien ut libero venenatis faucibus.</p>
			</div>
			<!-- Like and comment icon -->
			<div class="icon-bar">
				<ul class="menu-content">
					<li><a href="#"
						class="fa fa-comment-o comment-lbl float-right menu-icon"></a> <span
						class="comment-value float-right stat-value">830</span></li>
					<li><a href="#" class="fa fa-heart-o like-lbl menu-icon"></a>
						<span class="like-value stat-value">472</span></li>
				</ul>
			</div>
		</div>

		<div class="card">
			<img class="picture" src="${pageContext.request.contextPath}/resources/images/picture6.jpg">
			<div class="content">
				<a href="#"
					class="fa fa-bookmark-o bookmark-lbl float-right menu-icon"></a>
				<!-- Author info (avatar and name) -->
				<h3 class="author">
					<img class="avatar" src="${pageContext.request.contextPath}/resources/images/avatar.jpg" /> <a href="#">Sheldon
						Cooper</a>
				</h3>
				<hr class="separator" />
				<!-- Post description -->
				<p class="text">Li Europan lingues es membres del sam familie.
					Lor separat existentie es un myth. Por scientie, musica, sport etc,
					litot Europa usa li sam vocabular.</p>
			</div>
			<!-- Like and comment icon -->
			<div class="icon-bar">
				<ul class="menu-content">
					<li><a href="#"
						class="fa fa-comment-o comment-lbl float-right menu-icon"></a> <span
						class="comment-value float-right stat-value">830</span></li>
					<li><a href="#" class="fa fa-heart-o like-lbl menu-icon"></a>
						<span class="like-value stat-value">472</span></li>
				</ul>
			</div>
		</div>

		<div class="card">
			<img class="picture" src="${pageContext.request.contextPath}/resources/images/picture3.jpg">
			<div class="content">
				<a href="#"
					class="fa fa-bookmark-o bookmark-lbl float-right menu-icon"></a>
				<!-- Author info (avatar and name) -->
				<h3 class="author">
					<img class="avatar" src="${pageContext.request.contextPath}/resources/images/avatar.jpg" /> <a href="#">Sheldon
						Cooper</a>
				</h3>
				<hr class="separator" />
				<!-- Post description -->
				<p class="text">Li Europan lingues es membres del sam familie.
					Lor separat existentie es un myth. Por scientie, musica, sport etc,
					litot Europa usa li sam vocabular.</p>
			</div>
			<!-- Like and comment icon -->
			<div class="icon-bar">
				<ul class="menu-content">
					<li><a href="#"
						class="fa fa-comment-o comment-lbl float-right menu-icon"></a> <span
						class="comment-value float-right stat-value">830</span></li>
					<li><a href="#" class="fa fa-heart-o like-lbl menu-icon"></a>
						<span class="like-value stat-value">472</span></li>
				</ul>
			</div>
		</div>



	</div>
	<!--  END CONTAINER FLUID #grid -->
	<script
		src="${pageContext.request.contextPath}/resources/js/salvattore.js"></script>

</body>