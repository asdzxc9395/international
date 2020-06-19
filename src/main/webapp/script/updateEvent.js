var doubleSubmitFlag = false;
function doubleSubmitCheck(){
	if(doubleSubmitFlag){
		return doubleSubmitFlag;
	}else{
		doubleSubmitFlag = true;
		return false;
	}
}

function update() {
	var addMultiple = document.getElementById("updateForm");
		if(doubleSubmitCheck()) return;
		let rowDiv = document.createElement("div");
		
		
		
		let contents = '';

		contents +='<h2>경비 등록/ 수정</h2>';
		contents +='<h2>사용내역</h2>';
		contents +='<table border="8">';
		contents +='<tr>';
		contents +='<td>사용내역</td>';
		contents +='<td><select name="name">';
		contents +='<option value="" selected> 선택</option>';
		contents +='<option value="식대(야근)">식대(야근)</option>';
		contents +='<option value="택시비(야근)">택시비(야근)</option>';
		contents +='<option value="택시비(회식)">택시비(회식)</option>';
		contents +='<option value="사무용품구매">사무용품구매</option>';
		contents +='<option value="교육비">교육비</option>';
		contents +='<option value="접대비">접대비</option>';
		contents +='</select></td> ';
		contents +='</tr>';
		contents +='<tr>';
		contents +='<td>사용일</td>';
		contents +='<td><input name="useDate" type="date" value="${useDate}"></td>';
		contents +='</tr>';
		contents +='<tr>';
		contents +='<td>금액</td>';
		contents +='<td><input name="usePrice" type="text" value="${expense.usePrice}"></td>';
		contents +='</tr>';
		contents +='<tr>';
		contents +='<td>영수증</td>';
		contents +='<td><input name="imageFile" type="file"></td>';
		contents +=' </tr>';
		contents +='</table>';
		contents +='<h2>청구내역</h2>';
		contents +='<table border="8">';
		contents +='<tr>';
		contents +='<td>사용내역</td>';
		contents +='<td><input name="processStatus" type="text" value="${expense.processStatus}"></td> ';
		contents +='</tr>';
		contents +='<tr>';
		contents +='<td>사용일</td>';
		contents +='<td><input name="processDate" type="date" value="${expense.processDate}" id="currentDate">';
		contents +='</td>';
		contents +='</tr>';
		contents +='<tr>';
		contents +='<td>금액</td>';
		contents +='<td><input name="approvePrice" type="text" value="${expense.approvePrice}"></td>';
		contents +='</tr>';
		contents +='<tr>';
		contents +='<td>영수증</td>';
		contents +='<td><input name="remark" type="text" value="${expense.remark}"></td>';
		contents +='</tr>';
		contents +='</table>';

		contents +='<h2>영수증</h2>';
		contents +="<img src='${pageContext.servletContext.contextPath}'/upload/expense/${expense.receipt}' height='150'><br>";
		contents +='<button>저장</button>';
		contents +='<a href="delete?no=${expense.expenseNo}">삭제</a>';
		contents +='<a href="list">닫기</a>';

		rowDiv.innerHTML = contents;
		addMultiple.parentNode.appendChild(rowDiv);
	};
