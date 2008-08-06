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
		alert("用户名不能为空!");
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
	return true;
}