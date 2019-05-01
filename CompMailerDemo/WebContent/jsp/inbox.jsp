<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inbox</title>
<link rel="stylesheet" type="text/css" href="resources/style.css">
<style>


li#id1{
	color: red;
	border: none;
	padding: none;
}

</style>
</head>
<body>
${msg}
<jsp:include page="/header.html"></jsp:include>
<div>
<ul>
	
	<li style="float: left;border: none;margin: 0px"><button type="button" onclick="window.location.href='inbox'; return false;">Refresh</button></li>
	<li><a href="logout" > Logout</a>
	<li><a href=composeform.html>Compose</a></li>
	<li><a href="sentMsgs">Sent</a></li>
	<li id="id1"><p> Hii ${name}</p>

</ul>
</div>
<p style="color: blue;">${Message }</p>

<c:if test="${Messages!=null }">
	<table>
		<tr>
			<th width="5%"><p>ID.</p></th>
			<th width="15%"><p>Sender</p></th>
			<th width="15%"><p>Subject</p></th>
			<th width="60%"><p>Message</p></th>
			<th colspan="2" width="5%"><p>Action</p></th>
		</tr>
		<c:forEach var="temp" items="${Messages}">
			
			<c:url var="delurl" value="/delete.do">
			<c:param name="id" value="${temp.id}"></c:param>
			
			</c:url>
			
		<tr>
			<td><p>${temp.id}</p></td>
			<td><p>${temp.sender}</p></td>
			<td><p>${temp.subject}</p></td>
			<td><p>${temp.message }</p></td>
			<td><p><a href="${delurl}"
						onclick="if(!(confirm('Are you Sure you want to delete this Inbox Message?'))) return false">Delete</a></p></td>
		</tr>
		</c:forEach>
	</table>
</c:if>


</body>
</html>