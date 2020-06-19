<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>경비 등록/수정</title>

<link rel="stylesheet" href='../../css/update.css' />
</head>
<body>

<form action='search' method='get'>
			<a>등록년월:</a>
			<input name='registrationDate' type='date' id="registrationDate">
			<a>사용내역:</a>
			<select id="name" name="name">
		  		<option value="" selected> 선택</option>
    			<option value="식대(야근)">식대(야근)</option>
			    <option value="택시비(야근)">택시비(야근)</option>
			    <option value="택시비(회식)">택시비(회식)</option>
			    <option value="사무용품구매">사무용품구매</option>
    			<option value="교육비">교육비</option>
   			    <option value="접대비">접대비</option>
			</select><br>
			
			<a>처리상태:</a>
			<select id="processStatus" name="processStatus">
		  		<option value="" selected> 선택</option>
			    <option value="접수">접수</option>
			    <option value="승인">승인</option>
			    <option value="지급완료">지급완료</option>
    			<option value="반려">반려</option>
			</select>
			<button>검색</button>
			<a href="../expense/detail?no=0">초기화</a>
			</form>

<div id="list">
 <h2 class="bList_title"> 총${size}건</h2>
 
 <form id="form1" name="form1" method="post" enctype="multipart/form-data">
    <button type="button" onclick="doExcelDownloadProcess()">엑셀다운로드</button>
</form>

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
  <tr>
  <th width="5%" height="15%">합계</th>
       <th width="16%"></th>
       <th width="20%"></th>
       <td width="10%">${usePrice}</td>
       <td width="10%">${approvePrice}</td>
       <th width="20%"></th>
       <th width="16%"></th>
  </tr>
</table>
</div>
<div id="add">
<form action='add' method='post' enctype='multipart/form-data'>
<a id="addForm">등록</a>
</form> 
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
       <td>처리상태</td>
       <td><select name="processStatus">
		  		<option value="" selected> 선택</option>
			    <option value="접수">접수</option>
			    <option value="승인">승인</option>
			    <option value="지급완료">지급완료</option>
    			<option value="반려">반려</option>
			</select> </td> 
   </tr>
    <tr>
       <td>처리일시</td>
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
<a href="../expense/detail?no=0">닫기</a>
</form>
</div>
<script type="text/javascript">
    function doExcelDownloadProcess(){
        var f = document.form1;
        f.action = "downloadExcelFile";
        f.submit();
    }
</script>
<script src="../../script/addEvent.js"></script>
</body>
</html>