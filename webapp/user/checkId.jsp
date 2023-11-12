<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.sql.*, joongo.dao.MemberDAO"%>
<%
String mid = request.getParameter("mid"); // DB 연결 객체 생성
Connection conn = null; 
try {
Class.forName("oracle.jdbc.OracleDriver");
String url = "jdbc:oracle:thin:@localhost:1521:xe";
String user = "tr";
String password = "1234";
conn = DriverManager.getConnection(url, user, password);
// DAO의 chkId 메소드 호출하여 아이디 중복 검사 수행하여 결과를 반환
MemberDAO dao = new MemberDAO(conn);
boolean isDuplicate = dao.chkId(mid);

out.println("{\"isDuplicate\": " + isDuplicate + "}"); // JSON 형식으로 결과 반환
} catch (ClassNotFoundException | SQLException e) {
e.printStackTrace();
} finally {
// DB 연결 해제
if (conn != null) {
try {
conn.close();
} catch (SQLException e) {
e.printStackTrace();
}
}
}
%>
