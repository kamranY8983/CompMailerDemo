<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sent Msgs</title>

<link rel="stylesheet" type="text/css" href="resources/style.css">
</head>
<body>
${msg}
<jsp:include page="/header.html"></jsp:include>

<div>
<ul>
	
	<li style="float: left;border: none;margin: 0px"><button type="button" onclick="window.location.href='sentMsgs'; return false;">Refresh</button></li>
	<li><a href="logout" > Logout</a>
	<li><a href=composeform.html>Compose</a></li>
	<li><a href="inbox">Inbox</a></li>
	<li id="id1"><p> Hii ${name}</p>

</ul>
</div>

<div>
${Message }

	<table>
		<tr>
			<th width="5%">ID.</th>
			<th width="15%">To</th>
			<th width="15%">Subject</th>
			<th width="60%">Message</th>
			<th colspan="2" width="5%">Action</th>
		</tr>
		<c:forEach var="temp" items="${Messages}">
			
			<c:url var="delurl" value="/delete.do">
			<c:param name="id" value="${temp.id}"></c:param>
			
			</c:url>
			
		<tr>
			<td>${temp.id}</td>
			<td>${temp.sender}</td>
			<td>${temp.subject}</td>
			<td>${temp.message }</td>
			<td><a href="${delurl}"
						onclick="if(!(confirm('Are you Sure you want to delete this Sent Message?'))) return false">Delete</a></td>
		</tr>
		</c:forEach>
	</table>

</div>
</body>
</html>