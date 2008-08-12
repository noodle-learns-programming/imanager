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
		document.getElementById("contactItem.contactTypeId").focus();
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

function checkContactType(){
	var o = document.getElementById("contactType.contactType");
	if(Trim(o.value)==""){
		alert("联系类型不能为空!");
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
	var o = document.getElementById("contactItem.contactTypeId");
	if(o.value=="0"){
		alert("请选择联系类型!");
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