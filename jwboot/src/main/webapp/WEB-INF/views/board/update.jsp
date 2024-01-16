<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update...</title>
</head>
<link rel="stylesheet" href="/static/css/style.css">
<body>
	<div class="wrap">
		<h2>UPDATE</h2>
		<form action="/board/update" method="post">
			<table class="tbl_write">
				<tbody>
					<tr>
						<td>
							<input type="text" name="id" value="${board.id}" readonly>
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" name="title" value="${board.title}" required>
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" name="writer" value="${board.writer}" required>
						</td>
					</tr>
					<tr>
						<td>
							<textarea rows="5" cols="60" name="content" required>${board.content}</textarea>
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" name="createdDate" value="<fmt:formatDate value='${board.createdDate}' pattern='yyyy-MM-dd HH:mm:ss'/>" readonly>
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="Submit">
							<input type="reset" value="Reset">
							<a href="/board?id=${board.id}"><button type="button">Cancel</button></a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>