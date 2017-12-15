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
	<script
	src="${pageContext.request.contextPath}/resources/js/navbar.js"></script>
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index_style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/navbar_style.css">
</head>
<body>
      <header>
      <div id="rainbow-progress-bar"></div>
        <nav class="transparent">
         <span class="nav-left">
         <a href="index">
          <span id="logo"></span>
          <span id="logo-text">linstagram</span>
          </a>
        </span>
          <span id="search-form" class="form-inline">
            <div class="input-group" id="search-div">
              <span class="input-group-btn"><i class="fa fa-search"></i></span>
              <input type="text" class="form-control transparent" placeholder="Search" id="search-input">
            </div>
          </span>
          <span class="nav-right">
            <a href="" id="explore" class="right-icon-nav disabled" data-toggle="tooltip" data-placement="left" data-original-title="Coming Soon"></a>
            <a href="" id="notification" class="right-icon-nav"></a>
            <a href="profile" id="profile" class="right-icon-nav"></a>
          </span>
        </nav>
        <div id="story-nav">

        </div>
      </header>

    <div class="container" id="top">
      <section>
        <div class="row">
          <div class="col-md-2"></div>
          <div class="col-md-8">
            <!-- start body-section -->
            <div class='card'>
              <div class='top-section'>
                <a href=''>
                  <img class="user-img" src='resources/images/user_login_img.png'>
                </a>
                <a href='' class='user-name'>Peppe</a>
              </div>
              <div class='body-section'>
                <div class="overlay">
                  <span></span>
                </div>
                <img src='https://telegraf.com.ua/files/2017/04/jessica-alba-redbook2-800x533.jpg'>
              </div>
              <div class='action-section'>
                <div class='react'>
                  <a href='#' role='button'><span class='love'></span></a>
                  <a href='#' role='button'><span class='comment'></span></a>
                  <a href='#' role='button'><span class='save'></span></a>
                </div>
                <div class="likes-section">
                  <a href='#'><b>Piace a<span> 100000 persone</span></b></a>
                </div>
                <div class='caption-section'>
                  <a href='#'>Peppe</a><span>Testo del cristiano che ha scritto </span>
                </div>

                <div class='list-comments-section'>
                  <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                  <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                  <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                  <div id="post1" class="collapse">
                    <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                    <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                    <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                    <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                  </div>


                  <a class="show-all-comments" href="#post1" data-toggle="collapse"><span class="show-comments"></span> Carica altri commenti</a><br>


                </div>

                <div class='time-section'>
                  <p>4 ore fa</p>
                </div>

                <div class='comment-section'>
                  <input type='text' class='comment-text' placeholder='Add a comment...'>
                </div>
              </div>
            </div>
          </div>
          <!-- end body-section -->
          <div class="col-md-2"></div>
        </div>
      </section>
      <!-- end section -->

      <section>
        <div class="row">
          <div class="col-md-2"></div>
          <div class="col-md-8">
            <!-- start body-section -->
            <div class='card'>
              <div class='top-section'>
                <a href=''>
                  <img class="user-img" src='resources/images/user_login_img.png'>
                </a>
                <a href='' class='user-name'>Peppe</a>
              </div>
              <div class='body-section'>
                <div class="overlay">
                  <span></span>
                </div>
                <img src='https://pbs.twimg.com/media/B-EZPXFIIAAmcb8.jpg:large'>
              </div>
              <div class='action-section'>
                <div class='react'>
                  <a href='#' role='button'><span class='love'></span></a>
                  <a href='#' role='button'><span class='comment'></span></a>
                  <a href='#' role='button'><span class='save'></span></a>
                </div>
                <div class="likes-section">
                  <a href='#'><b>Piace a<span> 100000 persone</span></b></a>
                </div>
                <div class='caption-section'>
                  <a href='#'>Peppe</a><span>Testo del cristiano che ha scritto </span>
                </div>

                <div class='list-comments-section'>
                  <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                  <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                  <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                  <div id="post2" class="collapse">
                    <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                    <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                    <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                    <a href='#'>Lorenzo</a><span> testo del commento</span><br>
                  </div>


                  <a class="show-all-comments" href="#post2" data-toggle="collapse"><span class="show-comments"></span> Carica altri commenti</a><br>


                </div>

                <div class='time-section'>
                  <p>4 ore fa</p>
                </div>

                <div class='comment-section'>
                  <input type='text' class='comment-text' placeholder='Add a comment...'>
                </div>
              </div>
            </div>
          </div>
          <!-- end body-section -->
          <div class="col-md-2"></div>
        </div>
      </section>
      <!-- end section -->

      <div id="container-floating">

        <div class="nd1 nds" data-toggle="tooltip" data-placement="left" data-original-title="Story">
          <i class="fa fa-clock-o center-icon" aria-hidden="true"></i>
        </div>

        <div class="nd2 nds" data-toggle="tooltip" data-placement="left" data-original-title="Post ">
          <i class="fa fa-picture-o center-icon" aria-hidden="true"></i>
        </div>

        <div class="nd3 nds transparent" data-toggle="tooltip" data-placement="left" data-original-title="Top">
          <a href="#top"><i class="fa fa-arrow-up black-icon" aria-hidden="true"></i></a>
        </div>

        <div id="floating-button" data-toggle="tooltip" data-placement="left" data-original-title="Create">
          <p class="plus">+</p>
        </div>

      </div>


    </div>
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <p><small>&copy; <span><b>Linstagram</b></span>.<br>Project: Application Enterprise.<br>Designed by: Arcuri Brusco Cannella Drago Scarfone</small></p>
          </div>
        </div>
      </div>
    </footer>
    <script
		src="${pageContext.request.contextPath}/resources/js/progressBar.js"></script>
    
    <script type="text/javascript">
      $(document).ready(function() {
        $('.show-all-comments').click(function(e) {
          var target = $(e.target).find('span');
          if (target.hasClass('show-comments')) {
            $(e.target).html('<span class="hidden-comments"></span> Nascondi altri commenti');
          } else {
            $(e.target).html('<span class="show-comments"></span> Carica altri commenti');
          }
        });
        
        $('[data-toggle="tooltip"]').tooltip();
      });

    </script>
    
  </body>

</html>