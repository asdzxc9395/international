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
 <a href="form">등록</a>
 <button id="aaa">연습</button>
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
<div id="add">
<jsp:include page="../expense/form.jsp" />
</div>

<!-- 업데이트 -->
<div id='update'>
<form action='update' method='post' enctype='multipart/form-data'>
<input name="expenseNo" type="hidden" value="${expense.expenseNo}">
<h2>사용내역</h2>
<table border="8">
    <tr>
       <td>사용내역</td>
       <td><select name="name">
    <option value="" selected> 선택</option>
    <option value="식대(야근)">식대(야근)</option>
    <option value="택시비(야근)">택시비(야근)</option>
    <option value="택시비(회식)">택시비(회식)</option>
    <option value="사무용품구매">사무용품구매</option>
    <option value="교육비">교육비</option>
    <option value="접대비">접대비</option>
    </select></td> 
   </tr>
    <tr>
       <td>사용일</td>
       <td><input name="useDate" type="date" value="${expense.useDate}"></td>
   </tr>
    <tr>
       <td>금액</td>
       <td><input name="usePrice" type="text" value="${expense.usePrice}"></td>
   </tr>
    <tr>
       	<td>영수증</td>
    	<td><input name="imageFile" type="file"></td>
  </tr>
</table>
<h2>청구내역</h2>
<table border="8">
    <tr>
       <td>사용내역</td>
       <td><input name="processStatus" type="text" value="${expense.processStatus}"></td> 
   </tr>
    <tr>
       <td>사용일</td>
       <td><input name="processDate" type="date" value="${expense.processDate}" id="currentDate">
       <script>
    document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10);
</script> 
       </td>
   </tr>
    <tr>
       <td>금액</td>
       <td><input name="approvePrice" type="text" value="${expense.approvePrice}"></td>
   </tr>
    <tr>
       	<td>영수증</td>
    	<td><input name="remark" type="text" value="${expense.remark}"></td>
  </tr>
</table>

<h2>영수증</h2>
<img src='${pageContext.servletContext.contextPath}/upload/expense/${expense.receipt}' height='150'><br>
<button>저장</button>
<a href="delete?no=${expense.expenseNo}">삭제</a>
<a href="list">닫기</a>
</form>
</div>
<!-- 업데이트 종료 -->

<script src="../../script/addEvent.js"></script>
</body>
</html>