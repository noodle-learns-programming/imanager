//检查ContactType的提交
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
		alert("姓名不能为空!");
		return false;
	}
	return true;
}

function checkContactPinyin(){
	var o = document.getElementById("contactItem.pinyin");
	if(Trim(o.value)==""){
		alert("拼音不能为空!");
		return false;
	}
	return true;
}
function checkContactType(){
	var o = document.getElementById("contactItem.contactType.contactTypeId");
	if(o.value=="0"){
		alert("请选择联系类型!");
		return false;
	}
	return true;
}

//删除ContactType
function deleteContactItem(contactItemId){
	if (confirm("你确定要删除联系人详细吗？")){ 
		window.location="contactItemAction!logicDeleteContactItem.action?contactItemId="+contactItemId;
	}
}