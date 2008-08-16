function KeyDown(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkConsumeItemForm();
	}
}

//delete consume item
function deleteConsumeItem(consumeItemId){
	if (confirm("��ȷ��Ҫɾ�����Ѽ�¼��")){ 
		window.location="financeAction!doLogicDeleteConsumItem.action?consumeItemId="+consumeItemId;
	}
}

//����֤
function checkConsumeItemForm(){
	var theForm = document.getElementById("consumeItemForm");
	
	if(!checkConsumeItemName()){
		document.getElementById("consumeItem.itemName").focus();
		return;
	}
	
	if(!checkConsumeItemAddress()){
		document.getElementById("consumeItem.address").focus();
		return;
	}
	
	if(!checkConsumeItemPrice()){
		document.getElementById("consumeItem.price").focus();
		return;
	}
	
	if(!checkConsumeItemQuantity()){
		document.getElementById("consumeItem.quantity").focus();
		return;
	}
	
	theForm.submit();
}

//����֤��ϸ
function checkConsumeItemName(){
	var o = document.getElementById("consumeItem.itemName");
	if(Trim(o.value)==""){
		alert("������Ŀ����Ϊ��!");
		return false;
	}
	
	if(!checkInputLength(Trim(o.value), 100)){
		alert("������Ŀ�ĳ��Ȳ��ܴ���100!");
		return false;
	}
	return true;
}

function checkConsumeItemAddress(){
	var o = document.getElementById("consumeItem.address");
	if(Trim(o.value)==""){
		alert("���ѵص㲻��Ϊ��!");
		return false;
	}
	
	if(!checkInputLength(Trim(o.value), 100)){
		alert("������ص㳤�Ȳ��ܴ���100!");
		return false;
	}
	return true;
}

function checkConsumeItemPrice(){
	var o = document.getElementById("consumeItem.price");
	
	if(Trim(o.value)==""){
		alert("���۲���Ϊ��!");
		return false;
	}
	
	if(isNaN(Trim(o.value))){
		alert("���۲�������!");
		return false;
	}
	
	if(parseFloat(Trim(o.value)) < parseFloat(0) || parseFloat(Trim(o.value)) == parseFloat(0)){
		alert("���۲���С�ڻ����0!");
		return false;
	}
	
	return true;
}

function checkConsumeItemQuantity(){
	var o = document.getElementById("consumeItem.quantity");
	
	if(Trim(o.value)==""){
		alert("��������Ϊ��!");
		return false;
	}
	
	if(isNaN(Trim(o.value))){
		alert("������������!");
		return false;
	}
	if(parseFloat(Trim(o.value)) < parseFloat(0) || parseFloat(Trim(o.value)) == parseFloat(0)){
		alert("��������С�ڻ����0!");
		return false;
	}
	
	return true;
}