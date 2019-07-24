<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/form-page.css"/>
		<style>
			.error{
				color:red;
			}
		</style>
	</head>
	
	<body>
		<div class="wrapper">
			<section class="form-container">
				<h2>Login Page</h2>
				
				<c:if test="${param.error != null && param.logout== null}">																																										
					<p class="form-message error">Invalid username or password</p>
				</c:if>
			
				<c:if test="${param.logout != null && param.error == null}">
					<span class="form-message logout">Logged out successfully.</span>
				</c:if>
				
				<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
					<span>User name : </span><input type="text" placeholder="username" name="username" class="field"/>
					<br><br>
					<span>Password : </span><input type="password" placeholder="password" name="password" class="field"/>
					<br><br>
					
					<input type="submit" value="Login" class="button field">
				</form:form>
			</section>
	
			<a href="${pageContext.request.contextPath}/register/registrationForm" class="button">
				Register 
			</a>
		</div>
	</body>
</html>