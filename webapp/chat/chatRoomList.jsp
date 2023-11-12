<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<style>
.chat-list {
  display: flex;
  flex-direction: column;
}

.chat-room {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 16px;
  box-sizing: border-box;
  cursor: pointer;
  border-bottom: 1px solid #eaeaea;
}

.chat-room:hover {
  background-color: #f5f5f5;
}

.room-id {
  margin-right: 16px;
  font-weight: bold;
}

.room-users {
  margin-right: 16px;
  font-size: 14px;
  color: #8c8c8c;
}

.last-message {
  margin-right: 16px;
  font-size: 14px;
  color: #8c8c8c;
}

.timestamp {
  font-size: 12px;
  color: #8c8c8c;
}

</style>
<title>채팅 리스트</title>
<script>
  function openChatroom(id1,id2,number) {//id1 id2 number 받아야됨
    const form = document.createElement("form");
    var uniqueWindowName = "chatroom_" + id1 + "_" + id2 + "_" + number;
    
    form.setAttribute("method", "post");
    form.setAttribute("action", "/joongo/chat/chat.jsp");
    form.setAttribute("target", uniqueWindowName);

    //First_user(판매자 데이터 보내는곳)
    const inputId1 = document.createElement("input");
    inputId1.setAttribute("type", "hidden");
    inputId1.setAttribute("name", "id1");
    inputId1.setAttribute("value", id1);

  	//Second_user(구매자 데이터 보내는곳)
    const inputId2 = document.createElement("input");
    inputId2.setAttribute("type", "hidden");
    inputId2.setAttribute("name", "id2");
    inputId2.setAttribute("value", id2);
    
    const sessionId = document.createElement("input");
    sessionId.setAttribute("type", "hidden");
    sessionId.setAttribute("name", "sid");
    sessionId.setAttribute("value","${sessionScope.mid}");
    
    //물품 번호 데이터 보내는곳
    const inputNumber = document.createElement("input");
    inputNumber.setAttribute("type", "hidden");
    inputNumber.setAttribute("name", "number");
    inputNumber.setAttribute("value", number);
    
    //input hidden 데이터를 form에 넣어둔다음
    form.appendChild(inputId1);
    form.appendChild(inputId2);
    form.appendChild(sessionId);
    form.appendChild(inputNumber);
    
    //새로운 팝업을 여는곳 post 전송함
    document.body.appendChild(form);
    var newWindow = window.open("", uniqueWindowName, "width=600,height=760");
    form.submit();
    document.body.removeChild(form);
  }
</script>
</head>
<body>
<%@ include file="../include/header.jsp" %>
<script>
	/* alert(); */
</script>
<div class="jumbotron">
	<div class="container">
		<h2 class="display-4">채팅목록</h2>
	</div>
</div>

<main role="main">
	<div class="container">
		<form action="/joongo/Chat/ChatList.do" method="post">
			<input type="hidden" name="pageNum" value="${pageNum}">
			<c:if test="${!empty sessionScope.msg }">
				<p class="alert alert-danger p-5">
				<c:out value="${sessionScope.msg }"/>
			</c:if>
		<!-- 게시물이 없는 경우 -->
		<c:if test="${empty ChatList}">
			<div class="row">
			등록된 채팅이 없습니다.</div>
		</c:if>
		
		<!-- 그렇지 않은 경우 -->
		<c:if test="${!empty ChatList }">
		<div class="chat-list">
		  <c:forEach var="chat" items="${ChatList}">
		    <div class="chat-room" onclick="openChatroom('${chat.first_user_id}', '${chat.second_user_id}', '${chat.product_id}')">
		      <div class="room-id">${chat.chat_room_id}</div>
		      <div class="room-users">${chat.first_user_id}, ${chat.second_user_id}</div>
<%-- 		      <div class="last-message">${chat.last_message}</div> --%>
<%-- 		      <div class="timestamp">${chat.timestamp}</div> --%>
		    </div>     
		  </c:forEach> 
		</div>
		<br>
			<%-- 페이징 --%>
			<ul class="pagination justify-content-center">
				<!-- 이전 버튼 -->
				<c:if test="${prev}">
				<li class="page-item">
					<a href="/joongo/Chat/ChatList.do?pageNum=${start -1 }&mid=${sessionScope.mid}" class="page-link">&laquo;Previous</a></c:if>
				<!-- 페이지 번호 버튼 -->
				<c:forEach begin="${start}" end="${end }" var="i">
				<c:url var="link" value="/joongo/Chat/ChatList.do?pageNum=${i }&mid=${sessionScope.mid}"/>
				<li class="page-item ${pageNum==i?'active':'' }">
					<a href="/joongo/Chat/ChatList.do?pageNum=${i}&mid=${sessionScope.mid}" class="page-link">${i }</a>
				</c:forEach>
				<!-- 다음 버튼 -->
				<c:if test="${next}">
				<li class="page-item">
					<a href="/joongo/Chat/ChatList.do?pageNum=${end+1}&mid=${sessionScope.mid}" class="page-link">Next&raquo;</a></li></c:if>	
			</ul>
		</c:if>
		</form>
	</div>
</main>
<%@ include file="../include/footer.jsp" %>
</body>
</html>