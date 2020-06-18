<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프리렌서 조회</title> 

<link rel="stylesheet" href='../../css/update.css' />
</head>
<body>

			
			

<div id="list">
 <h2 class="bList_title"> 총00건</h2>
<table border="8">
    <tr>
       <th width="5%" height="15%">순번</th>
       <th width="16%">사용일</th>
       <th width="20%">사용내역</th>
       <th width="10%">사용금액</th>
       <th width="10%">승인금액</th>
       <th width="20%">처리상태</th>
       <th width="16%">등록일</th>
   </tr>
   <c:forEach items="${list}" var="item">
  <tr>
    <td>${item.expenseNo}</td> 
    <td>${item.useDate}</td> 
    <td><a href="detail?no=${item.expenseNo}">${item.name}</a>
    </td> 
    <td>${item.usePrice}</td> 
    <td>${item.approvePrice}</td>
    <td>${item.processStatus}</td>
    <td>${item.registrationDate}</td>
  </tr>
</c:forEach>
</table>
</div>
<!-- 업데이트 종료 -->

<script src="../../script/addEvent.js"></script>
</body>
</html>