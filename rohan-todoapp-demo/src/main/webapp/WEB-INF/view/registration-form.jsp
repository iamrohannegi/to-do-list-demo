<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
	<head>
		<title>Registration Form</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/form-page.css"/>
		<style>
			.error{
				color:red;
			}
		</style>
	</head>
	
	<body>
		<div class="wrapper">
			<section class="form-container">
				<h2>Registration</h2>
				<c:if test="${registrationError != null}">
					<p class="error">${registrationError}</p>
				</c:if>
				
				<form:form action="${pageContext.request.contextPath}/register/processRegistration" 
						   method="POST" modelAttribute="newUser">
						   
					<span>User Name : </span><form:input path="userName" placeholder="username" class="field"/>
					<form:errors path="userName" class="error"/>
					<br><br>
					<span>Password : </span><form:password path="password" placeholder="password" class="field"/>
					<form:errors path="password" class="error"/>
					<br><br>

					<input type="submit" value="Register" class="button field"/>
					
					
				</form:form>
			</section>
			<a href="${pageContext.request.contextPath}/loginPage" class="button">Back to Login</a>
		</div>	
	</body>
</html>