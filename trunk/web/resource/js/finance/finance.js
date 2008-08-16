function KeyDown(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkConsumeItemForm();
	}
}

//delete consume item
function deleteConsumeItem(consumeItemId){
	if (confirm("你确定要删除消费记录吗？")){ 
		window.location="financeAction!doLogicDeleteConsumItem.action?consumeItemId="+consumeItemId;
	}
}

//表单验证
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

//表单验证明细
function checkConsumeItemName(){
	var o = document.getElementById("consumeItem.itemName");
	if(Trim(o.value)==""){
		alert("消费项目不能为空!");
		return false;
	}
	
	if(!checkInputLength(Trim(o.value), 100)){
		alert("消费项目的长度不能大于100!");
		return false;
	}
	return true;
}

function checkConsumeItemAddress(){
	var o = document.getElementById("consumeItem.address");
	if(Trim(o.value)==""){
		alert("消费地点不能为空!");
		return false;
	}
	
	if(!checkInputLength(Trim(o.value), 100)){
		alert("消费项地点长度不能大于100!");
		return false;
	}
	return true;
}

function checkConsumeItemPrice(){
	var o = document.getElementById("consumeItem.price");
	
	if(Trim(o.value)==""){
		alert("单价不能为空!");
		return false;
	}
	
	if(isNaN(Trim(o.value))){
		alert("单价不是数字!");
		return false;
	}
	
	if(parseFloat(Trim(o.value)) < parseFloat(0) || parseFloat(Trim(o.value)) == parseFloat(0)){
		alert("单价不能小于或等于0!");
		return false;
	}
	
	return true;
}

function checkConsumeItemQuantity(){
	var o = document.getElementById("consumeItem.quantity");
	
	if(Trim(o.value)==""){
		alert("数量不能为空!");
		return false;
	}
	
	if(isNaN(Trim(o.value))){
		alert("数量不是数字!");
		return false;
	}
	if(parseFloat(Trim(o.value)) < parseFloat(0) || parseFloat(Trim(o.value)) == parseFloat(0)){
		alert("数量不能小于或等于0!");
		return false;
	}
	
	return true;
}