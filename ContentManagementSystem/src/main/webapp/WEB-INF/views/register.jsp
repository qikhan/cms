<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="stags" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.qk.cms.view.RegistrationStatus" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Registration form Template | PrepBootstrap</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" type="text/css" href="/ContentManagementSystem/resources/scripts/lib/bootstrap/css/bootstrap.min.css" />
    <!-- link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css" /> -->

    <script type="text/javascript" src="/ContentManagementSystem/resources/scripts/lib/jquery/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="/ContentManagementSystem/resources/scripts/lib/bootstrap/js/bootstrap.min.js"></script>

	<style>
	.fieldError {
		  border-color : red;
	}
	
	</style>
	<script>
		$(document).ready(function() {
			//var status = "${status}";
			//if (status === "<%=RegistrationStatus.INPUT%>") {				
			//	alert("Input mode");
			//}
		});
		
		function confirmValidation($obj1, $obj2) {

			 if($obj1.val() != $obj2.val()) {
				if ($obj1.val() != "" || $obj2.val() != "") {
					var label1 = $("label[for='" + $obj1.attr("id") + "']").text();
					var label2 = $("label[for='" + $obj2.attr("id") + "']").text();
					$obj1.addClass("fieldError");
					$obj1.attr('title' , "Must match value of " + label2);
					$obj2.addClass("fieldError");
					$obj2.attr('title' , "Must match value of " + label1);
				}	
			}
			else {
				$obj1.removeClass("fieldError");
				$obj1.attr('title', '');
				$obj2.removeClass("fieldError");
				$obj2.attr('title', '');
			}
		}
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
					<ul class="nav navbar-nav">
						<li class="active"><a href="/ContentManagementSystem">Home</a></li>
					</ul>
		</div>
		<!--/.navbar-collapse -->
	</div>
</nav>
<div class="container">

<div class="page-header" style="display: block;">
    <h1>Welcome to CMS Registration</h1>
    <small>Please fill all required field. You will receive an email confirmation.</small>
</div>

<!-- Registration form - START -->
<div class="container">
	<% 
	RegistrationStatus status = (RegistrationStatus) request.getAttribute("status");	
	if (RegistrationStatus.INPUT == status) { %>
    <div class="row">
        <sform:form role="form" action="register" modelAttribute="cmsUser">
        <% if (RegistrationStatus.ERROR == status) {%>
		    <div class="row">    
		    	<div class="col-lg-5 col-md-push-1" style="display:block">
		            <div class="col-md-12">
		                <div class="alert alert-danger">
		                    <span class="glyphicon glyphicon-remove"></span><strong>Error! Please check all page inputs.</strong>
		                </div>
		            </div>
		        </div>
		    </div>
		<% } %>    
            <div class="col-lg-6">
                <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
                <div class="form-group">
                    <label for="InputName">User Name</label>
                    <div class="input-group">
                        <sform:input type="text" class="form-control" name="InputName" id="InputName" placeholder="User Name" required="true" path="userName"></sform:input>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputEmail">Email</label>
                    <div class="input-group">
                        <sform:input type="email" class="form-control" id="InputEmail" name="InputEmail" 
                        placeholder="Email" required="true" path="email" onblur="confirmValidation($(this), $('#InputEmailConfirm'))"></sform:input>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputEmailConfirm">Confirm Email</label>
                    <div class="input-group">
                        <input type="email" class="form-control" id="InputEmailConfirm" name="InputEmail" 
                        placeholder="Confirm Email" required onblur="confirmValidation($(this), $('#InputEmail'))"></input>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputPassword">Password</label>
                    <div class="input-group">
                        <sform:input type="password" class="form-control" id="InputPassword" name="InputPassword" 
                        placeholder="Password" required="true" path="password" onblur="confirmValidation($(this), $('#InputPasswordConfirm'))"></sform:input>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>
                <div class="form-group">
                    <label for="InputPasswordConfirm">Confirm Password</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="InputPasswordConfirm" name="InputPassword" 
                        placeholder="Confirm Password" required onblur="confirmValidation($(this), $('#InputPassword'))"></input>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                    </div>
                </div>                
                <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-right">
            </div>
        </sform:form>
    </div>
    <% }
	else if (RegistrationStatus.SUCCESS == status) { %>
    <div class="row">    
    	<div class="col-lg-5 col-md-push-1" style="display:block">
            <div class="col-md-12">
                <div class="alert alert-success">
                    <strong><span class="glyphicon glyphicon-ok"></span> You have Successfully registarted.</strong>
                </div>
            </div>
        </div>
    </div>
    <% } %>
</div>
<!-- Registration form - END -->

</div>

</body>
</html>