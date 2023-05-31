<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="contextPath" value="${ pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>회원 가입창</title>
<body>
<form method="post"  action="${contextPath}/member/addMember.do"> <!-- 가끔 프로젝트가 바뀌는 경우가 있어서? 이걸 한다고함. 아주 가끔이라고 하는데... 굳이해야하는지...  -->
<h1  style="text-align:center">회원 가입창</h1>
<table  align="center">
    <tr>
       <td width="200"><p align="right">아이디</td>
       <td width="400"><input type="text" name="id"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">비밀번호</td>
        <td width="400"><input type="password"  name="pwd"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">이름</td>
        <td width="400"><p><input type="text"  name="name"></td>
    </tr>
    <tr>
        <td width="200"><p align="right">이메일</td>
        <td width="400"><p><input type="text"  name="email"></td>
    </tr>
    <tr>
        <td width="200"><p>&nbsp;</p></td>
        <td width="400">
          <input type="submit" value="가입하기">
          <input type="reset" value="다시입력">
       </td>
    </tr>
</table>
</form>
</body>
</html>