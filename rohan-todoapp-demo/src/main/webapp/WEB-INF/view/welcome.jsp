<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
	<head>
		<title>To-Do Demo - Rohan Negi</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
	</head>
	
	<body>
		<h1>To-Do List</h1>
		<hr><br>
		
		<h2>Tasks</h2>
				
		<!-- List of entries -->
		<table>
			<c:forEach var="entry" items="${entries}">
				<c:url var="updateLink" value="/updateEntry">
					<c:param name="entryId" value="${entry.id}"/>
				</c:url>
				<c:url var="deleteLink" value="/deleteEntry">
					<c:param name="entryId" value="${entry.id}"/>
				</c:url>
				<tr>
					<td class="entryText">${entry.entryText}<td>
					<td class="actionButtons"><a class="button" href="${updateLink}">Update</a> <a class="button" href="${deleteLink}">Delete</a><td>
				<tr>
			</c:forEach>
		</table>
		
		<!--  Add new entry -->
		<form:form action="processNewEntry" modelAttribute="entry" method="POST">
			<form:hidden path="id"/>
			
			<p class="error"><form:errors path="entryText"/></p>
			<form:input path="entryText" class="entryText" placeholder="Add a new entry...(maxlength:40)" maxlength="40"/>
			
			
			<input type="submit" class="button" value="Add"/>
			
		</form:form>
		
		
		<!--  Logout -->
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" class="button logout" value="Logout"/>
		</form:form>
		
		
		
	</body>
</html>	