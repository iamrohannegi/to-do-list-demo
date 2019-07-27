<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
	<head>
		<title>To-Do Demo - Rohan Negi</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login-style.css"/>
	
	</head>
	
	<body>
		<h1>Registration</h1>
		<hr><br>
		
		<!-- Back to login button -->
		<a href="${pageContext.request.contextPath}/loginPage" class="button redirect">Back to Login</a>
		
		<!-- Registration Form -->
		<form:form action="${pageContext.request.contextPath}/register/processRegistration" 
				   method="POST" modelAttribute="newUser">
			
			<c:if test="${registrationError != null}">
				<p class="form-message error">${registrationError}</p>
			</c:if>	   
			
			<c:if test="${registrationSuccessful != null}">
				<p class="form-message registered">${registrationSuccessful}</p> 
			</c:if>
			
			<span>User Name </span><span class="error"><form:errors path="userName"/></span>
			<form:input path="userName" placeholder="username" class="field" maxlength="50"/>
			<br><br>
			
			<span>Password </span><span class="error"><form:errors path="password"/></span>
			<form:password path="password" placeholder="password" class="field" maxlength="60"/>
			<br><br>

			<input type="submit" value="Register" class="button form-submit"/>
					
		</form:form>
		
			
	</body>
</html>