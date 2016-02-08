<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="stags" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- link rel="icon" href="/ContentManagementSystem/resources/scripts/lib/bootstrap/favicon.ico">  -->
<title>Your Ultimate Content Management Site</title>
<!-- Bootstrap core CSS -->
<link href="/ContentManagementSystem/resources/scripts/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<!-- link href="jumbotron.css" rel="stylesheet">  -->
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="/ContentManagementSystem/resources/scripts/lib/bootstrap/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<!-- script src="/ContentManagementSystem/resources/scripts/lib/bootstrap/assets/js/ie-emulation-modes-warning.js"></script>  -->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/ContentManagementSystem/resources/scripts/lib/jquery/jquery-2.1.3.min.js"></script>
<script src="/ContentManagementSystem/resources/scripts/lib/bootstrap/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!-- script src="/ContentManagementSystem/resources/scripts/lib/bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>  -->
<script>
	$(document).ready(function() {

		var authValid = "${authValid}";
		if (authValid == "false") {
			$(".navbar-header button").click();
		}
	});
</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
					aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">CMS</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<c:choose>
					<c:when test="${authValid==null || authValid==false}">
						<sform:form class="navbar-form navbar-right" action="login" method="POST" modelAttribute="login">
							<div class="form-group">
								<sform:input type="text" placeholder="User Name" class="form-control" path="userName"
									value="${login.userName}"></sform:input>
							</div>
							<div class="form-group">
								<sform:input type="password" placeholder="Password" class="form-control" path="password"></sform:input>
							</div>
							<button type="submit" class="btn ${loginStyle}" data-toggle="tooltip" title="${loginMessage}">Login</button>
						</sform:form>
					</c:when>
					<c:otherwise>
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">Home</a></li>
							<li class="dropdown active"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
								role="button" aria-expanded="false">Tools <span class="caret"></span>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">Action</a></li>
									<li><a href="#">Another action</a></li>
									<li><a href="#">Something else here</a></li>
									<li class="divider"></li>
									<li class="dropdown-header">Nav header</li>
									<li><a href="#">Separated link</a></li>
									<li><a href="#">One more separated link</a></li>
								</ul></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="active"><a href="logout">Logout</a></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>
	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Hello, world!</h1>
			<p>Manage your content and publish them at ease. Enable targeted content push. Enable e-commerce on
					any content.</p>
			<p>
				<a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a>
			</p>
		</div>
	</div>
	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-4">
				<h2>Create an Publisher Account</h2>
				<p>Manage your content and publish them at ease. Enable targeted content push. Enable e-commerce on
					any content.</p>
				<p>
					<a class="btn btn-default" href="#" role="button">View details &raquo;</a>
				</p>
			</div>
			<div class="col-md-4">
				<h2>Create an Reader Account</h2>
				<p>Open the door to explore limitless content of all kind. Receive exclusive offers and access to
					content of your interest.</p>
				<p>
					<a class="btn btn-default" href="#" role="button">View details &raquo;</a>
				</p>
			</div>
		</div>
		<hr>
		<footer>
			<div class="container">
				<c:choose>
					<c:when test="${userName != null}">
						<p>You are : ${userName}</p>
					</c:when>
					<c:otherwise>
				        <a href="register">Register</a>
				    </c:otherwise>
				</c:choose>
				<p>&copy; QK Learning by doing it!</p>
			</div>
		</footer>
	</div>
	<!-- /container -->
</body>
</html>
