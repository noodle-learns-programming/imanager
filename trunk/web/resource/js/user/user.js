function KeyDownR(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkRegisterForm();
	}
}

function KeyDownI(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkUserInfoForm();
	}
}

function KeyDownP(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkUserPasswordForm();
	}
}

//����֤
function checkRegisterForm(){
	var theForm = document.getElementById("registerForm");
	
	if(!checkLoginId()){
		document.getElementById("user.loginId").focus();
		return;
	}
	
	if(!checkUserName()){
		document.getElementById("user.userName").focus();
		return;
	}
	
	if(!checkUserPassword()){
		document.getElementById("user.password").focus();
		return;
	}
	
	if(!checkConformPasswordR()){
		document.getElementById("conformPassword").focus();
		return;
	}
	
	theForm.submit();
}

function checkUserInfoForm(){
	var theForm = document.getElementById("userInfoForm");
	
	if(!checkUserName()){
		document.getElementById("user.userName").focus();
		return;
	}
	
	theForm.submit();
}

function checkUserPasswordForm(){
	var theForm = document.getElementById("userPasswordForm");
	
	if(!checkOldPassword()){
		document.getElementById("oldPassword").focus();
		return;
	}
	
	if(!checkNewPassword()){
		document.getElementById("newPassword").focus();
		return;
	}
	
	if(!checkConformPasswordP()){
		document.getElementById("conformPassword").focus();
		return;
	}
	
	theForm.submit();
}

//����֤��ϸ
function checkLoginId(){
	var o = document.getElementById("user.loginId");
	
	if(Trim(o.value)==""){
		alert("�û�ID����Ϊ��!");
		return false;
	}
	
	if(!checkByteLength(Trim(o.value), 6, 20)){
		alert("�û����ĳ��ȱ�����6 - 20λ֮��!");
		return false;
	}
	
	return true;
}

function checkUserName(){
	var o = document.getElementById("user.userName");
	
	if(Trim(o.value)==""){
		alert("�û���������Ϊ��!");
		return false;
	}
	
	if(!checkInputLength(Trim(o.value), 20)){
		alert("�û������ĳ��Ȳ��ܴ���20!");
		return false;
	}
	
	return true;
}

function checkUserPassword(){
	var o = document.getElementById("user.password");
	
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

function checkConformPasswordR(){
	var o1 = document.getElementById("user.password");
	var o2 = document.getElementById("conformPassword");
	
	if(Trim(o2.value)==""){
		alert("ȷ�����벻��Ϊ��!");
		return false;
	}
	
	if(!checkByteLength(Trim(o2.value), 6, 20)){
		alert("ȷ������ĳ��ȱ�����6 - 20λ֮��!");
		return false;
	}
	
	if(Trim(o1.value)!=Trim(o2.value)){
		alert("�����ȷ�����벻��ͬ!");
		return false;
	}
	
	return true;
}

function checkOldPassword(){
	var o = document.getElementById("oldPassword");
	
	if(Trim(o.value)==""){
		alert("ԭʼ���벻��Ϊ��!");
		return false;
	}
	
	if(!checkByteLength(Trim(o.value), 6, 20)){
		alert("ԭʼ����ĳ��ȱ�����6 - 20λ֮��!");
		return false;
	}
	
	return true;
}

function checkNewPassword(){
	var o = document.getElementById("newPassword");
	
	if(Trim(o.value)==""){
		alert("�����벻��Ϊ��!");
		return false;
	}
	
	if(!checkByteLength(Trim(o.value), 6, 20)){
		alert("������ĳ��ȱ�����6 - 20λ֮��!");
		return false;
	}
	
	return true;
}

function checkConformPasswordP(){
	var o1 = document.getElementById("newPassword");
	var o2 = document.getElementById("conformPassword");
	
	if(Trim(o2.value)==""){
		alert("ȷ�����벻��Ϊ��!");
		return false;
	}
	
	if(!checkByteLength(Trim(o2.value), 6, 20)){
		alert("ȷ������ĳ��ȱ�����6 - 20λ֮��!");
		return false;
	}
	
	if(Trim(o1.value)!=Trim(o2.value)){
		alert("�����ȷ�����벻��ͬ!");
		return false;
	}
	
	return true;
}