//���ContactType���ύ
function checkContactItemForm(){
	var theForm = document.getElementById("contactItemForm");
	
	if(!checkContactName()){
		document.getElementById("contactItem.name").focus();
		return;
	}
	
	if(!checkContactPinyin()){
		document.getElementById("contactItem.pinyin").focus();
		return;
	}
	
	if(!checkContactType()){
		document.getElementById("contactItem.contactType.contactTypeId").focus();
		return;
	}
	
	theForm.submit();
}

function checkContactName(){
	var o = document.getElementById("contactItem.name");
	if(Trim(o.value)==""){
		alert("��������Ϊ��!");
		return false;
	}
	return true;
}

function checkContactPinyin(){
	var o = document.getElementById("contactItem.pinyin");
	if(Trim(o.value)==""){
		alert("ƴ������Ϊ��!");
		return false;
	}
	return true;
}
function checkContactType(){
	var o = document.getElementById("contactItem.contactType.contactTypeId");
	if(o.value=="0"){
		alert("��ѡ����ϵ����!");
		return false;
	}
	return true;
}

//ɾ��ContactType
function deleteContactItem(contactItemId){
	if (confirm("��ȷ��Ҫɾ����ϵ����ϸ��")){ 
		window.location="contactItemAction!logicDeleteContactItem.action?contactItemId="+contactItemId;
	}
}