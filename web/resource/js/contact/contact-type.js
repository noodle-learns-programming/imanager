function KeyDown(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkContactTypeForm();
	}
}

//���ContactType���ύ
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
		alert("��ϵ���Ͳ���Ϊ��!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 50)){
		alert("��ϵ���͵ĳ��Ȳ��ܴ���50!");
		return false;
	}
	return true;
}

//ɾ��ContactType
function deleteContactType(contactTypeId){
	if (confirm("��ȷ��Ҫɾ����ϵ������")){ 
		window.location="contactTypeAction!logicDeleteContactType.action?contactTypeId="+contactTypeId;
	}
}