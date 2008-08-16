function KeyDown(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkContactTypeForm();
	}
}

//检查ContactType的提交
function checkContactTypeForm(){
	var theForm = document.getElementById("contactTypeForm");
	
	if(!checkContactType()){
		document.getElementById("contactType.contactType").focus();
		return;
	}
	
	theForm.submit();
}

function checkContactType(){
	var o = document.getElementById("contactType.contactType");
	if(Trim(o.value)==""){
		alert("联系类型不能为空!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 50)){
		alert("联系类型的长度不能大于50!");
		return false;
	}
	return true;
}

//删除ContactType
function deleteContactType(contactTypeId){
	if (confirm("你确定要删除联系类型吗？")){ 
		window.location="contactTypeAction!logicDeleteContactType.action?contactTypeId="+contactTypeId;
	}
}