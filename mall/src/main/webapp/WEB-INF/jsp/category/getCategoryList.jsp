<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
		<ul>
			<c:forEach var="category" items="${categoryList}">
				<li>
					<a href="${pageContext.request.contextPath}/category/getCategory?categoryNo=${category.categoryNo}">${category.categoryName}</a>
				</li>
			</c:forEach>
			<li>
				<a href="/">메인으로</a>
			</li>
		</ul>
	</div>

</body>
</html>