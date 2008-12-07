function KeyDown(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkLoginForm();
	}
}

//表单验证
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

//表单验证明细
function checkLoginId(){
	var o = document.getElementById("loginId");
	if(Trim(o.value)==""){
		alert("用户ID不能为空!");
		return false;
	}
	
	if(!checkByteLength(Trim(o.value), 6, 20)){
		alert("用户ID的长度必须在6 - 20位之间!");
		return false;
	}
	
	return true;
}

function checkLoginPwd(){
	var o = document.getElementById("password");
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