function KeyDown(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkEmbedToolsForm();
	}
}

//检查EmbedTools的提交
function checkEmbedToolsForm(){
	var theForm = document.getElementById("embedToolsForm");
	
	if(!checkEmbedToolsName()){
		document.getElementById("embedTools.toolsName").focus();
		return;
	}
	
	if(!checkEmbedToolsDescription()){
		document.getElementById("embedTools.toolsDescription").focus();
		return;
	}

	if(!checkPicture()){
		document.getElementById("picture").focus();
		return;
	}
	
	if(!checkEmbedToolsScript()){
		document.getElementById("embedTools.toolsScript").focus();
		return;
	}
	
	theForm.submit();
}

function checkEmbedToolsName(){
	var o = document.getElementById("embedTools.toolsName");
	if(Trim(o.value)==""){
		alert("工具名称不能为空!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 100)){
		alert("工具名称的长度不能大于100!");
		return false;
	}
	return true;
}

function checkEmbedToolsDescription(){
	var o = document.getElementById("embedTools.toolsDescription");
	if(Trim(o.value)==""){
		alert("工具简介不能为空!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 300)){
		alert("工具简介的长度不能大于300!");
		return false;
	}
	return true;
}

function checkPicture(){
	var o = document.getElementById("picture");
	if(Trim(o.value)==""){
		alert("图片不能为空!");
		return false;
	}
	return true;
}

function checkEmbedToolsScript(){
	var o = document.getElementById("embedTools.toolsScript");
	if(Trim(o.value)==""){
		alert("嵌入代码不能为空!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 300)){
		alert("嵌入代码的长度不能大于300!");
		return false;
	}
	return true;
}

//删除EmbedTools
function deleteEmbedTools(embedToolsId){
	if (confirm("你确定要删除该工具吗？")){ 
		window.location="toolsAction!logicDeleteEmbedTools.action?embedToolsId="+embedToolsId;
	}
}