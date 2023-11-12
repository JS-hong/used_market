<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import="market.vo.productVO,java.util.*" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productlist</title>
<style >
.overlay{position: relative;}
.sold-out {
  position: absolute;
  top: 10px;
  right: 10px;
  color:white;
  background-color: black;
  padding: 2px 5px;
  font-size: 12px;
  font-weight: bold;
}

.categories-column {
  flex: 0 0 auto;
  width: 170px; /* Change 100px to your desired width */
  margin-left: 15pt;
  left:15pt;
}

li.selected {
  position: relative;
  padding-right: 5px; /* Adjust this value as needed */
}
.categories-column li a {
  color: black;
}
input[name="keyword"] {
  width: 500px; 

}
  .search-container {
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .select-wrapper {
    position: absolute;
    left: 0;
  }

a {
  
  text-decoration: none;
  display: inline-block;
  padding: 5px;
  border-radius: 5px;
}
.atag:hover {
  background-color: #ffffdd;
   text-decoration: none;
}
.tmd{
margin-right:20px;
margin-left: 30px;
margin-bottom: 30px;
}
ul {
  list-style-type: none;
}
.imagecontainer{
	width:280px;
  height: 300px;
}
.thumbnail-image {
  max-width: 100%;
  max-height: 100%;

}

    
</style>

</head>
<body>
	<%@ include file="../include/header.jsp"  %>
	
	
	<main role="main">
	<form action="/joongo/Product/List.do?category=${category}" method="post">
	<div class="jumbotron" style="align-content: center;">
		<div class="row mb-3">
			<div class="col-12" >
			  <div class="search-container">
        <div class="select-wrapper">
			<select name="location" id="location">
				<option value="" >전체</option>
				<option>내 주소 1</option>
				<option>내 주소 2</option>
			</select>
			</div>
				<input type="text" name="keyword" id="keyword"
					value="${!empty keyword ? keyword :''}"> 
				<input type="submit" class="btn btn-info btn-sm" value="검색" style="margin-left: 7px;">
				
				<div class="text-right">
			<a href="/joongo/Product/WriteForm.do" class="btn btn-info btn-sm">
				글쓰기</a>
				</div>
			</div>
		</div>
	</div>
</div>
	<div class="row main-container" >
				<div class="categories-column">
				<h4>CATEGORY</h4>
					<ul >
						<li ><a href="/joongo/Product/List.do?&pageNum=1&category=0&location=${location }&keyword=${keyword }" class="atag">
							전체</a></li>
						<c:forEach items="${categoryList }" var="category">
						<c:set var="cid" value="${category.cid }"/>
						<li><a href="/joongo/Product/List.do?&pageNum=1&category=${cid }&location=${location }&keyword=${keyword }" class="atag">
							${category.cname }</a></li>
						</c:forEach>
					</ul>
					<input type="hidden" name="category" id="category" value="${category }">
				</div>
	
	
		<div class="container" align="center">			
        <div class="col">
        <h5 align="left" style="margin-bottom: 20pt">${cname }</h5>
          <div class="row">
          <c:forEach items="${productList }" var="pvo">
			<div class="col-md-3 tmd"><!-- 여기서부터 foreach -->
			<a href="/joongo/Product/View.do?pid=${pvo.pid }&id=${pvo.sellerid}&pageNum=${pageNum}&category=${category}&location=${location }&keyword=${keyword }" 
				style="color:black;text-decoration: none;" class="atag">
				<div class="overlay">
					<div class="imagecontainer">
					<img src="/joongo/resources/image/${pvo.thumbnail }" class="img-thumbnail">
					</div>
					<span class="sold-out">판매완료</span></div>	
					<h5>${pvo.ptitle }</h5>
					<h5>${pvo.price }</h5>
					<p>${pvo.plocation }</p>
					<p>조회수 ${pvo.viewcnt } ∙ 채팅 ${pvo.chatcnt }</p>
				</div>
			</a>
		</c:forEach>
			</div>
		</div>
	
		</div>
		</div>
			<ul class="pagination justify-content-center">
				<!-- 이전 버튼 -->
				<c:if test="${prev}">
				<li class="page-item">
					<a href="/joongo/Product/List.do?pageNum=${start -1 }&category=${category }&location=&keyword=${keyword }" class="page-link">&laquo;Previous</a></c:if>
				<!-- 페이지 번호 버튼 -->
				<c:forEach begin="${start}" end="${end }" var="i">
				<c:url var="link" value="/joongo/Product/List.do?pageNum=${i }&category=${category }&location=&keyword=${keyword }"/>
				<li class="page-item ${pageNum==i?'active':'' }">
					<a href="/joongo/Product/List.do?pageNum=${i }&category=${category }&location=&keyword=${keyword }" class="page-link">${i }</a>
				</c:forEach>
				<!-- 다음 버튼 -->
				<c:if test="${next}">
				<li class="page-item">
					<a href="/joongo/Product/List.do?pageNum=${end + 1 }&category=${category }&location=&keyword=${keyword }" class="page-link">Next&raquo;</a></li></c:if>
			
			
			</ul>
		</form>
	</main>
	<%@ include file="../include/footer.jsp"  %>
</body>
</html>