<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<! DOCTYPE>

<html>
	<head>
		<title>To-Do Demo - Rohan Negi</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
	</head>
	<body>
		<h1>Update Entry</h1>
		<hr><br>
		
		<!-- Entry Form -->
		<form:form action="processUpdatedEntry" modelAttribute="entry" method="POST">
			<form:hidden path="id"/>
			
			<p class="error"><form:errors path="entryText"/></p>
			<form:input path="entryText" class="entryText" maxlength="40" />
			
			<input type="submit" class="button" value="Update"/>
			
		</form:form>
		
		<!--  Logout -->
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" class="button logout" value="Logout"/>
		</form:form>
	
	</body>

</html>

