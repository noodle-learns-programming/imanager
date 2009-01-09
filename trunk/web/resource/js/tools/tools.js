function KeyDown(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkEmbedToolsForm();
	}
}

//���EmbedTools���ύ
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
		alert("�������Ʋ���Ϊ��!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 100)){
		alert("�������Ƶĳ��Ȳ��ܴ���100!");
		return false;
	}
	return true;
}

function checkEmbedToolsDescription(){
	var o = document.getElementById("embedTools.toolsDescription");
	if(Trim(o.value)==""){
		alert("���߼�鲻��Ϊ��!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 300)){
		alert("���߼��ĳ��Ȳ��ܴ���300!");
		return false;
	}
	return true;
}

function checkPicture(){
	var o = document.getElementById("picture");
	if(Trim(o.value)==""){
		alert("ͼƬ����Ϊ��!");
		return false;
	}
	return true;
}

function checkEmbedToolsScript(){
	var o = document.getElementById("embedTools.toolsScript");
	if(Trim(o.value)==""){
		alert("Ƕ����벻��Ϊ��!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 300)){
		alert("Ƕ�����ĳ��Ȳ��ܴ���300!");
		return false;
	}
	return true;
}

//ɾ��EmbedTools
function deleteEmbedTools(embedToolsId){
	if (confirm("��ȷ��Ҫɾ���ù�����")){ 
		window.location="toolsAction!logicDeleteEmbedTools.action?embedToolsId="+embedToolsId;
	}
}