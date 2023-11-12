<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productdetail</title>
<style>
  /* 이미지를 화면 가운데로 정렬하는 스타일 */
  body, html {
    height: 100%;
    margin: 0;
  }

  .image-container {
     height: 350px; /* 적절한 높이 값으로 설정 */
 	 width: 350px; /* 적절한 너비 값으로 설정 */
    display: flex;
    justify-content: center;
    align-items: center;
    border: 1px solid #f5f5dc;
  
  }

  #centered-image {
    max-width: 100%;
    max-height: 100%;
  }

  /* 기타 스타일들 */
  #article-profile .space-between {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .sold-out {
    position: absolute;
    color: white;
    background-color: black;
    padding: 2px 5px;
    font-size: 12px;
    font-weight: bold;
  }

  #article-description {
    padding: 32px 0;
    width: 68%;
    margin: 0 auto;
    border-bottom: 1px solid #eee6c4;
  }

  #article-profile {
    width: 68%;
    margin: 0 auto;
  }
  .report{
  color:black;
  border-bottom: 1px solid black;
  font-size: 10pt;
  }
   .heart-container {
  
        top: 10px;
        right: 10px;
        width: 20px;
        height: 20px;
        border:1px solid black;
    }
    .heart{
    width:70px;
    height: 70px;
    }
  /* 기존 CSS 코드 */

  /* 새로운 CSS 코드 */
  .image-navigation {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

.image-navigation .nav-button {
  background-color: #fff;
  border: 1px solid #eee6c4;
  color: #666;
  cursor: pointer  font-size: 18px;
  margin: 0 10px;
  padding: 10px 15px;
  border-radius: 5px;
 
}

.image-navigation .nav-button:hover {
  background-color: #eee6c4;
  color: #fff;
}
 .witch{
 margin-left: 28%;
 }
</style>
</head>
<body>
<%@ include file="../include/header.jsp"  %>
<div class="jumbotron">
  <div class="container">
    <h1 class="display-4">상품 상세보기</h1>
  </div>
</div>
<main role="main">
  <div class="container ">
  <form action="/joongo/Product/ModifyForm.do?pageNum=${pageNum}" method="post">
    <div class="row">
    <div class="image-navigation witch">
    <button class="nav-button prev">&laquo; </button>
    </div>
      <!-- 이미지를 화면 가운데로 정렬하는 부분 -->
      <div class="image-container">
     	   <img src="/joongo/resources/image/${productView.thumbnail }" id="centered-image" class="resized-image">
     	  <c:forEach items="${productImg }" var="img">
     	   <img src="/joongo/resources/image/${img.imageurl }" id="centered-image" class="resized-image">
     	  </c:forEach>
      </div>
      <div class="image-navigation">
	 <button class="nav-button next"> &raquo;</button>
	</div>
    </div>

    <section id="article-profile">
      <p></p>
      <div class="space-between">
        <div style="display: flex;">
          <div id="article-profile-image">
            <img alt="유저 사진"
                 src="https://d1unjqcospf8gs.cloudfront.net/assets/users/default_profile_80-c649f052a34ebc4eee35048815d8e4f73061bf74552558bb70e07133f25524f9.png">
          </div>
          <div id="article-profile-left">
            <div><p></p></div>
            <div id="nickname">${sellerid != null ? sellerid : "TEST"} 
            <span class="sold-out">판매완료</span>
            </div>
            <div id="region-name">${productView.plocation } </div>
          </div>
        </div>
        <div id="article-profile-right">
          <dl id="temperature-wrap">
            <dt>매너점수</dt>
            <dd class="text-color-04 ">
              37.8 <span>점</span>
            </dd>
          </dl>
           <div class="heart-container">
 	 	<img src="../resources/image/beanheart.png" class="heart empty-heart" >
 	 	<img src="../resources/image/heart.png" class="heart filled-heart" style="display: none;">
	</div>
        </div>
      </div>
    </section>
    <section id="article-description">
      <h1  id="article-title" style="margin-top:0px;">${productView.ptitle }</h1>
      <input type="hidden" name="ptitle" id="ptitle" value="${productView.ptitle}">
      <p id="article-category">
       ${productView.cname }
       <input type="hidden" name="cid" id="cid" value="${productView.cid }">
      </p>
      
      <p id="article-price" style="font-size:18px; font-weight:bold;">
        ${productView.price }원
         <input type="hidden" name="price" id="price" value="${productView.price }">
      </p>
      <div id="article-detail">
        <p>${productView.pcontent } </p>
        <input type="hidden" name="pcontent" id="pcontent" value="${productView.pcontent}">
      </div>
      <br>
      <p id="article-counts">
        관심 10 ∙채팅 ${productView.chatcnt } ∙조회수 ${productView.viewcnt }
      </p>
      <p><a href="" class="report">이 게시물 신고하기</a></p>
      	<div class="form-group row"> 
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-info" value="수정하기">
					<input type="button" class="btn btn-secondary" onclick="history.back();"
						   value="취소">
				</div></div>
				<input type="hidden" name="pid" id="pid" value="${pid }">
            <input type="hidden" name="sellerid" id="sellerid" value="${sellerid }">
    </section>
    </form>
  </div>
</main>

<%@ include file="../include/footer.jsp"  %>
<script>

$(document).ready(function () {
	  var currentIndex = 0;
	  var imageElements = $(".image-container .resized-image");

	  function updateImageVisibility() {
	    imageElements.hide();
	    $(imageElements[currentIndex]).show();
	  }

	  updateImageVisibility();

	  $(".nav-button.next").on("click", function (e) {
		  e.preventDefault();
	    if (currentIndex < imageElements.length - 1) {
	      currentIndex++;
	    } else {
	      currentIndex = 0;
	    }
	    updateImageVisibility();
	  });

	  $(".nav-button.prev").on("click", function (e) {
		  e.preventDefault();
	    if (currentIndex > 0) {
	      currentIndex--;
	    } else {
	      currentIndex = imageElements.length - 1;
	    }
	    updateImageVisibility();
	  });
	});
</script>
</body>
</html>
