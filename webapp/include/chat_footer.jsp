<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>footer</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css" 
integrity="sha384-KyZXEAg3QhqLMpG8r+Knujsl7/n7l7g8r+tolvJi73stellenmq8jmqfptrw/9OD" 
crossorigin="anonymous">
<style>
body {
  font-family: Arial, sans-serif;
}

.input-container {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: #f5f5f5;
  padding: 15px;
  box-shadow: 0 -2px 3px rgba(0, 0, 0, 0.1);
}

.input-group {
  display: flex;
  align-items: center;
  border-radius: 25px;
  background-color: #ffffff;
  overflow: hidden;
  padding: 0;
}

#messageInput {
  flex-grow: 1;
  border: none;
  outline: none;
  padding: 10px 15px;
  font-size: 14px;
}

.send-btn {
  background-color: #ff9900;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 12px 20px;
  font-size: 18px;
  color: #ffffff;
  border-radius: 0 25px 25px 0;
}
</style>
<script>

</script>
</head>
<body>
  <div class="input-container">
    <form id="chatForm">
      <div class="input-group">
        <input type="text" id="messageInput" placeholder="메시지 입력">
        <button type="submit" class="send-btn">
          <i class="fas fa-paper-plane">전송</i>
        </button>
      </div>
</body>
</html>