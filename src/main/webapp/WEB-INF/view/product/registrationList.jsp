<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>물품 등록 목록</title>
<link rel="stylesheet" href="css/product.css?ver=1.3">
</head>
<body>
<h2>물품 등록 현황</h2>
<div class="division-line"></div>

<div class="table-container">
<br/>
	<table class="tbl_type">
		<tr>
			<th width="80px">등록 번호</th>
			<th>품목명</th>
			<th>등록일</th>
			<th>폐기 예정일</th>
			<th>보유 여부</th>
		</tr>
		<tbody id="registrationList">
		<c:forEach var="i" items="${list}">
		<tr>
			<td>${i.reg_no}</td>
			<td><strong><a class="regView" href="/product/view?reg_no=${i.reg_no}" >${i.pname}</a></strong></td>
			<td>${i.reg_date}</td>
			<td>${i.due_date}</td>
			<td>
				<!-- DB에 [0: 반출, 1: 보유] 값으로 저장되어 있어,
					 웹 브라우저에서 한글 표기를 위해 아래 코드 사용 -->
				<c:choose>
					<c:when test="${i.isLend == 0}">
						❌
					</c:when>
					<c:when test="${i.isLend == 1}">
						⭕
					</c:when>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>

	<%-- 페이징 처리 --%>
	<div class="page-box">
		<c:if test="${page.showPrev}">
			<a  class = "Search" href="<c:url value='/product/regList?page=${page.beginPage -1}&pageSize=${page.pageSize}'/>">&laquo;</a>
		</c:if>
		<c:forEach var="i" begin="${page.beginPage}" end="${page.endPage}">
			<a class = "Search" href="<c:url value='/product/regList?page=${i}&pageSize=${page.pageSize}'/>">${i}</a>
		</c:forEach>
		<c:if test="${page.showNext}">
			<a  class = "Search" href="<c:url value='/product/regList?page=${page.endPage +1}&pageSize=${page.pageSize}'/>">&raquo;</a>
		</c:if>
	</div>
	<br>
	<div class="selectContainer">
		<div class="selectBar">
			<select id="pno" name="pno">
				<option value="">검색 내용 선택</option>
				<!-- productList를 이용하여 옵션을 동적으로 생성 -->
				<c:forEach items="${productList}" var="product">
					<option value="${product.pno}">${product.pname}</option>
				</c:forEach>
			</select>
			<button type="submit" class="searchBtn" >검색</button>
		</div>
	</div>
</div>
<script>   var rootPath = window.location.origin</script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="${rootPath}/js/product.js"></script>
</body>
</html>