<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="/market/resources/js/script.js"></script>
<style>

</style>
</head>
<body>
<!-- session != userid 차이에 따라 구매자 헤더 판매자 헤더가 나뉨 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container">
    <div class="row w-100">
      <div class="col-4">
        <p class="mb-0 fw-bold">수상한 유저1</p>
      </div>
      <div class="col-8 d-flex align-items-center">
        <div class="col-4 p-0 text-center border-end">
          <p class="mb-0">상품 사진</p>
          <img src="https://via.placeholder.com/60" alt="상품 이미지" class="rounded mt-2" style="width: 60px; height: 60px;">
        </div>
        <div class="col-4 p-0 text-center border-end">
          <p class="mb-0">상품 가격</p>
          <p class="mb-0 fw-bold">₩12,000</p>
        </div>
        <div class="col-4 p-0 text-center">
          <p class="mb-0">상품 이름</p>
          <p class="mb-0">당근 라디오</p>
        </div>
      </div>
    </div>
    <hr>
    <div class="row">
      테스트<!-- 구매자 or 판매자용 액션버튼 모음 -->
    </div>
  </div>
</nav>
</body>
</html>



