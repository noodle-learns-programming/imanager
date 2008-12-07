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

//表单验证
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

//表单验证明细
function checkLoginId(){
	var o = document.getElementById("user.loginId");
	
	if(Trim(o.value)==""){
		alert("用户ID不能为空!");
		return false;
	}
	
	if(!checkByteLength(Trim(o.value), 6, 20)){
		alert("用户名的长度必须在6 - 20位之间!");
		return false;
	}
	
	return true;
}

function checkUserName(){
	var o = document.getElementById("user.userName");
	
	if(Trim(o.value)==""){
		alert("用户姓名不能为空!");
		return false;
	}
	
	if(!checkInputLength(Trim(o.value), 20)){
		alert("用户姓名的长度不能大于20!");
		return false;
	}
	
	return true;
}

function checkUserPassword(){
	var o = document.getElementById("user.password");
	
	if(Trim(o.value)==""){
		alert("密码不能为空!");
		return false;
	}
	
	if(!checkByteLength(Trim(o.value), 6, 20)){
		alert("密码的长度必须在6 - 20位之间!");
		return false;
	}
	
	return true;
}

function checkConformPasswordR(){
	var o1 = document.getElementById("user.password");
	var o2 = document.getElementById("conformPassword");
	
	if(Trim(o2.value)==""){
		alert("确认密码不能为空!");
		return false;
	}
	
	if(!checkByteLength(Trim(o2.value), 6, 20)){
		alert("确认密码的长度必须在6 - 20位之间!");
		return false;
	}
	
	if(Trim(o1.value)!=Trim(o2.value)){
		alert("密码跟确认密码不相同!");
		return false;
	}
	
	return true;
}

function checkOldPassword(){
	var o = document.getElementById("oldPassword");
	
	if(Trim(o.value)==""){
		alert("原始密码不能为空!");
		return false;
	}
	
	if(!checkByteLength(Trim(o.value), 6, 20)){
		alert("原始密码的长度必须在6 - 20位之间!");
		return false;
	}
	
	return true;
}

function checkNewPassword(){
	var o = document.getElementById("newPassword");
	
	if(Trim(o.value)==""){
		alert("新密码不能为空!");
		return false;
	}
	
	if(!checkByteLength(Trim(o.value), 6, 20)){
		alert("新密码的长度必须在6 - 20位之间!");
		return false;
	}
	
	return true;
}

function checkConformPasswordP(){
	var o1 = document.getElementById("newPassword");
	var o2 = document.getElementById("conformPassword");
	
	if(Trim(o2.value)==""){
		alert("确认密码不能为空!");
		return false;
	}
	
	if(!checkByteLength(Trim(o2.value), 6, 20)){
		alert("确认密码的长度必须在6 - 20位之间!");
		return false;
	}
	
	if(Trim(o1.value)!=Trim(o2.value)){
		alert("密码跟确认密码不相同!");
		return false;
	}
	
	return true;
}