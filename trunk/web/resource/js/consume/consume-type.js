function KeyDown(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkConsumeTypeForm();
	}
}

//delete consume type
function deleteConsumeType(consumeTypeId){
	if (confirm("��ȷ��Ҫɾ������������")){ 
		window.location="consumeTypeAction!logicDeleteConsumType.action?consumeTypeId="+consumeTypeId;
	}
}

//����֤
function checkConsumeTypeForm(){
	var theForm = document.getElementById("consumeTypeForm");
	
	if(!checkConsumeType()){
		document.getElementById("consumeType.consumeType").focus();
		return;
	}
	
	theForm.submit();
}

//����֤��ϸ
function checkConsumeType(){
	var o = document.getElementById("consumeType.consumeType");
	if(Trim(o.value)==""){
		alert("�������Ͳ���Ϊ��!");
		return false;
	}
	
	if(!checkInputLength(Trim(o.value), 100)){
		alert("�������͵ĳ��Ȳ��ܴ���100!");
		return false;
	}
	return true;
}