function KeyDown(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkLoginForm();
	}
}

//����֤
function checkLoginForm(){
	var theForm = document.getElementById("loginForm");
	
	if(!checkLoginId()){
		document.getElementById("loginId").focus();
		return;
	}
	
	if(!checkLoginPwd()){
		document.getElementById("password").focus();
		return;
	}
	
	theForm.submit();
}

//����֤��ϸ
function checkLoginId(){
	var o = document.getElementById("loginId");
	if(Trim(o.value)==""){
		alert("�û�ID����Ϊ��!");
		return false;
	}
	
	if(!checkByteLength(Trim(o.value), 6, 20)){
		alert("�û�ID�ĳ��ȱ�����6 - 20λ֮��!");
		return false;
	}
	
	return true;
}

function checkLoginPwd(){
	var o = document.getElementById("password");
	if(Trim(o.value)==""){
		alert("���벻��Ϊ��!");
		return false;
	}
	
	if(!checkByteLength(Trim(o.value), 6, 20)){
		alert("����ĳ��ȱ�����6 - 20λ֮��!");
		return false;
	}
	
	return true;
}