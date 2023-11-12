<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="joongo.vo.*" import="java.util.*" import="joongo.dao.*"%>
<jsp:useBean id="avo" class="joongo.vo.AddrVO"/>
<jsp:useBean id="adao" class="joongo.dao.AddrDAO"/>
<%
request.setCharacterEncoding("UTF-8");

String mid = (String) session.getAttribute("mid"); // 세션에 있는 mid 값 가져오기
String addr2 = request.getParameter("addr2"); // 세션에 있는 mid 값 가져오기

avo.setMid(mid);
avo.setAddr2(addr2);

    if (adao.update(avo)) {
        application.log("수정 성공");
        response.sendRedirect("addrList.jsp");
    } else {
        application.log("수정 실패");
    }
    out.println("mid : " + mid); // mid값을 출력
    out.println("addr2 : " + addr2); // addr2 값을 출력
%>


