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
		document.getElementById("contactItem.contactTypeId").focus();
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

function checkContactType(){
	var o = document.getElementById("contactType.contactType");
	if(Trim(o.value)==""){
		alert("��ϵ���Ͳ���Ϊ��!");
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
	var o = document.getElementById("contactItem.contactTypeId");
	if(o.value=="0"){
		alert("��ѡ����ϵ����!");
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