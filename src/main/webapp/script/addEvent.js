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
	var addMultiple = document.getElementById("addForm");
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
