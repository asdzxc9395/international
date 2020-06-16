<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프리렌서 조회</title> 
</head>
<body>
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
    <td><a id="detailForm" name="${item.expenseNo}">${item.name}</a>
    
    <script>
var doubleSubmitFlag = false;
function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}





window.onload = function() {
var addMultiple = document.getElementById("detailForm");
addMultiple.onclick = function() {
	if(doubleSubmitCheck()) return;

	let rowDiv = document.createElement("div");
	
	let contents = '';

	contents +='<h2>경비 등록/ 수정</h2>';
	contents +='<table border="8">';
	contents += ' <tr>';
	contents +=  '     <td>사용내역</td>';
	contents +=	 '      <td><select name="name">';
	contents +=	 '   <option value="" selected> 선택</option>';
	contents +=	 '   <option value="식대(야근)">식대(야근)</option>';
	contents +=	 '   <option value="택시비(야근)">택시비(야근)</option>';
	contents +=  '  <option value="택시비(회식)">택시비(회식)</option>';
	contents +=  '  <option value="사무용품구매">사무용품구매</option>';
	contents +=  '  <option value="교육비">교육비</option>';
	contents +=	 '   <option value="접대비">접대비/option>';
	contents +=	'   </select></td> ';
	contents += '  </tr>';
	contents += '   <tr>';
	contents += '      <td>사용일</td>';
	contents += '      <td><input name="useDate" type="date"></td>';
	contents +=	'   </tr>';
	contents +=	'    <tr>';
	contents +=	'       <td>금액</td>';
	contents +=	'       <td><input name="usePrice" type="text"></td>';
	contents +=	'   </tr>';
	contents +=	'    <tr>';
	contents +=	'       	<td>영수증</td>';
	contents +=	'    	<td><input name="imageFile" type="file"></td>';
	contents +=	'  </tr>';
	contents +=	'  </table>'; 
	contents +=	'<a href="list">닫기</a>';
		contents +=	'<button>저장</button>';
	rowDiv.innerHTML = contents;	
	addMultiple.parentNode.appendChild(rowDiv);
}};
</script>
    </td> 
    <td>${item.usePrice}</td> 
    <td>${item.approvePrice}</td>
    <td>${item.processStatus}</td>
    <td>${item.registrationDate}</td>
  </tr>
</c:forEach>
</table>
<form action='add' method='post' enctype='multipart/form-data'>
<a id="addForm">제이쿼리등록</a>
</form>
<script src="../../script/addEvent.js"></script>
</body>
</html>