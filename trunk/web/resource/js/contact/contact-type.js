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
	return true;
}

//ɾ��ContactType
function deleteContactType(contactTypeId){
	if (confirm("��ȷ��Ҫɾ����ϵ������")){ 
		window.location="contactTypeAction!logicDeleteContactType.action?contactTypeId="+contactTypeId;
	}
}