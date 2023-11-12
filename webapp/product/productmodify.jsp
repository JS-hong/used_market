<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="joongo.vo.productVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prouctAdd.jsp</title>
</head>
<body>

<%@ include file="../include/header.jsp" %>
<div class="jumbotron">
	<div class="container">
		<h1 class="display-4">
			상품 수정</h1></div></div>

<main role="main">
	<div class="container">
		
		<form action="/joongo/Product/Modify.do" method="post" class="form-horizontal" enctype="multipart/form-data">
			<div class="form-group row"> 
				<label class="col-sm-2">제목</label>
				<input type="hidden" name="pid" id="pid" value="${pid }">
				<div class="col-sm-3">
					<input type="text" class="form-control" name="ptitle" id="ptitle" value="${ptitle }">
				</div></div>
		
			<div class="form-group row"> 
				<label class="col-sm-2">카테고리</label>
				<div class="col-sm-3">
					<select class="form-control" name="cid" id="cid" >
					<c:forEach items="${categoryList }" var="category">
					<c:set var="selected" value="${cid eq category.cid ? 'selected' : ''}" />
						<option value="${category.cid }" ${selected }>${category.cname }</option>
					</c:forEach>
					</select>
				</div></div> 
				
			<div class="form-group row"> 
				<label class="col-sm-2">지역</label>
				<div class="col-sm-3">
					<select class="form-control" name="plocation" id="plocation">
						<option>내 주소 1
						<option>내 주소 2
						<option>내 주소 3
					</select>
				</div></div>

		
			<div class="form-group row"> 
				<label class="col-sm-2">가격</label>
				<div class="col-sm-3">
					<%
    String price = request.getParameter("price");
    int priceAsInt = 0;
    if (price != null) {
        try {
            priceAsInt = Integer.parseInt(price);
        } catch (NumberFormatException e) {
            // 문자열을 숫자로 변환하는 동안 예외 처리
        }
    }
%>
<input type="number" class="form-control" value="<%= priceAsInt %>" min="0" step="1000" name="price" id="price">
				</div></div>
		
			<div class="form-group row"> 
				<label class="col-sm-2">내용</label>
				<div class="col-sm-5">
					<textarea class="form-control"  name="pcontent" id="pcontent"
							  cols="50" rows="2">${pcontent }</textarea>
				</div></div>
		
	
			<div class="form-group row"> 
				<label class="col-sm-2">이미지</label>
				<div class="col-sm-5">
					<input multiple="multiple" type="file" class="form-control" name="images" id="images">
				</div></div>
		
			<div class="form-group row"> 
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-info" value="수정">
					<input type="button" class="btn btn-secondary" onclick="history.back();"
						   value="취소">
				</div></div>
		</form></div></main>

<%@ include file="../include/footer.jsp" %>
	
</body>
</html>









