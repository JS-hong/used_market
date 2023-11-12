<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
a {
    text-decoration: none;
}
.wrap {
    padding: 40px 0;
    background-color: #A8C0D6;
}
.wrap .chat {
    display: flex;
    align-items: flex-start;
    padding: 20px;
}
.wrap .chat .icon {
    position: relative;
    overflow: hidden;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    background-color: #eee;
}
.wrap .chat .icon i {
    position: absolute;
    top: 10px;
    left: 50%;
    font-size: 2.5rem;
    color: #aaa;
    transform: translateX(-50%);
}
.wrap .chat .textbox {
    position: relative;
    display: inline-block;
    max-width: calc(100% - 70px);
    padding: 10px;
    margin-top: 7px;
    font-size: 13px;
    border-radius: 10px;
}
.wrap .chat .textbox::before {
    position: absolute;
    display: block;
    top: 0;
    font-size: 1.5rem;
}
.wrap .ch1 .textbox {
    margin-left: 20px;
    background-color: #ddd;
}
.wrap .ch1 .textbox::before {
    left: -15px;
    content: "◀";
    color: #ddd;
}
.wrap .ch2 {
    flex-direction: row-reverse;
}
.wrap .ch2 .textbox {
    margin-right: 20px;
    background-color: #F9EB54;
}
.wrap .ch2 .textbox::before {
    right: -15px;
    content: "▶";
    color: #F9EB54;
}
.wrap .ch1 .textbox {
    margin-left: 20px;
    background-color: #ddd;
}
.wrap .ch1 .textbox::before {
    left: -15px;
    content: "◀";
    color: #ddd;
    text-align: left;
}
#chatroom {
  height: 600px;
  overflow: auto;
}
</style>
<meta charset="UTF-8">
<title>채팅방</title>
<script>
  // Loading chat room
  window.onload = function() {//onload에서 채팅방 내역 불러와야됨 id1=판매자 id2=구매자
	var id1 = '<%= request.getParameter("id1") %>'; 
	var id2 = '<%= request.getParameter("id2") %>';
	var sessionID = '<%= request.getParameter("sid") %>';
	var number = '<%= request.getParameter("number") %>';
    var str = id1 + "_" + id2 + "_" + number;
	console.log(sessionID);
    if (id1 != null && id2 != null && number != null) {
        // 채팅방 정보를 사용하여 데이터 로드 및 처리
    }   
    //document.getElementById("roomTitle").innerText = "채팅방: " + str;
    var webSocket = new WebSocket("ws://localhost:8083/joongo/chat/" + str); //str = 채팅방 고유번호
    
    webSocket.onmessage = function(event) {//메세지 입력시 행동    
    	  var incomingMessage = JSON.parse(event.data);
    	  sendMessage(incomingMessage); //자 JSON 형태로 sendMessage로 배달이요
    };   
    
    var chatForm = document.getElementById("chatForm"); 
    function scrollToBottom(element) {
        element.scrollTop = element.scrollHeight;
    }
    //채팅을 입력했을때 실행하는 이벤트 리스너
	chatForm.addEventListener('submit', function(e) {
      e.preventDefault(); // 기본 submit 동작 막기
      var messageInput = document.getElementById("messageInput"); 
      var userId = sessionID; //myUserID 설정하는곳   
      var rId = str;
      var message = 
		{
    		roomId: rId,
			userId: userId,
			text: messageInput.value
		};//메세지 데이터를 JSON 형태로 가공
		webSocket.send(JSON.stringify(message));
		messageInput.value = "";
    });
    
    //AJAX 통신 메시지들 가져오기
	if (str != null) {
	    const data = new URLSearchParams();
	    console.log(str);
	    data.append('chat_room_id', str);

	    // fetch 요청
    	fetch('/joongo/Chat/MessageList.do', {
        	method: 'POST',
        	headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        	},
        	body: data
    	})
	        .then(response => response.json())
	        .then(messages => {
	            displayMessages(messages);
	        })
	        .catch(error => {
	            console.error('Error fetching chat messages:', error);
	        });
	}
    
    
    function sendMessage(message) {  
    	console.log(message);
     	var chatElement = $("<div></div>").addClass("chat");
       	var iconElement = $("<div></div>").addClass("icon").appendTo(chatElement);
       	var icon = $("<i></i>").addClass("fa-solid fa-user").appendTo(iconElement);
       	var textboxElement = $("<div></div>").addClass("textbox").text(message.text).appendTo(chatElement);
        
       	if (message.userId === sessionID) {//myUserID 에는 로그인 되어있는 세션 ID를 넣어주면됨
            chatElement.addClass("ch2"); // 자신의 메시지
          } else {
            chatElement.addClass("ch1"); // 상대방의 메시지
          }
       	$("#chatroom").append(chatElement);
        scrollToBottom(chatroom); // 맨 아래로 스크롤
    }

    function displayMessages(messages) {
        // 받은 메시지 데이터에서 각 메시지 처리
        messages.forEach((message) => {
            const chatElement = $("<div></div>").addClass("chat");
            const iconElement = $("<div></div>").addClass("icon").appendTo(chatElement);
            const icon = $("<i></i>").addClass("fa-solid fa-user").appendTo(iconElement);
            const textboxElement = $("<div></div>").addClass("textbox").text(message.chat_message).appendTo(chatElement); // 'text' 부분 사용

            if (message.user_id === sessionID) { // 로그인된 세션 ID와 비교
                chatElement.addClass("ch2"); // 자신의 메시지
            } else {
                chatElement.addClass("ch1"); // 상대방의 메시지
            }
            $("#chatroom").append(chatElement);
            scrollToBottom(chatroom); // 맨 아래로 스크롤
        });
    }
 };

</script>
</head>
<body>
<%@ include file="../include/chat_header.jsp" %>
	<div class="wrap" id="chatroom">

	</div>
<%@ include file="../include/chat_footer.jsp" %>
</body>
</html>