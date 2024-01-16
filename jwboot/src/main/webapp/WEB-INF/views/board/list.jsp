<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardList...</title>
<link rel="stylesheet" href="/static/css/style.css">
<body>
	<div class="wrap">
		<h2>List</h2>
		<table class="tbl_write">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boardList}" var="board">
					<tr>
						<td>${board.id}</td>
						<td><a href="/board?id=${board.id}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td><fmt:formatDate value="${board.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a href="/"><button>Home</button></a>
			<a href="/board/write"><button>Write</button></a>
		</div>
	</div>
</body>
</html>