<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
		<title>To-Do Demo - Rohan Negi</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login-style.css"/>

	</head>
	
	<body>
		<h1>Login</h1>
		<hr><br>
		
		<!-- Registration Button -->
		<a href="${pageContext.request.contextPath}/register/registrationForm" class="button redirect">
				Register 
		</a>	
		
		<!-- Login Form -->	
		<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
			<c:if test="${param.error != null && param.logout== null}">																																										
				<p class="form-message error">Invalid username or password</p>
			</c:if>
			
			<c:if test="${param.logout != null && param.error == null}">
				<p class="form-message logout">Logged out successfully.</p>
			</c:if>
			
			
			<span>User Name </span><input type="text" placeholder="username" name="username" class="field" maxlength="50"/>
			<br><br>
			<span>Password </span><input type="password" placeholder="password" name="password" class="field" maxlength="60"/>
			<br><br>
			
			<input type="submit" value="Login" class="button form-submit">
		</form:form>

	</body>
</html>