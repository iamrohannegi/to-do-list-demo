<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<! DOCTYPE>

<html>
	<head>
		<title>To do app</title>
	</head>
	<body>
		<h2>Update entry</h2>
		<br><hr>
		<form:form action="processUpdatedEntry" modelAttribute="entry" method="POST">
			<form:hidden path="id"/>
			
			<p><form:errors path="entryText"/></p>
			Entry: <form:input path="entryText"/>
			
			<input type="submit" value="Submit"/>
			
		</form:form>
	</body>
</html>


<!-- NOTES -->
<!-- 
	CREATE ENTRY FORM
	CREATE PROPER CONTROLLERS FOR UPDATE DELETE REQUEST.
	TRY TO FIGURE OUT A BETTER WAY TO DO THIS.





-->