function KeyDown(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkConsumeTypeForm();
	}
}

//delete consume type
function deleteConsumeType(consumeTypeId){
	if (confirm("你确定要删除消费类型吗？")){ 
		window.location="consumeTypeAction!logicDeleteConsumType.action?consumeTypeId="+consumeTypeId;
	}
}

//表单验证
function checkConsumeTypeForm(){
	var theForm = document.getElementById("consumeTypeForm");
	
	if(!checkConsumeType()){
		document.getElementById("consumeType.consumeType").focus();
		return;
	}
	
	theForm.submit();
}

//表单验证明细
function checkConsumeType(){
	var o = document.getElementById("consumeType.consumeType");
	if(Trim(o.value)==""){
		alert("消费类型不能为空!");
		return false;
	}
	
	if(!checkInputLength(Trim(o.value), 100)){
		alert("消费类型的长度不能大于100!");
		return false;
	}
	return true;
}