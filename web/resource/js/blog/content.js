function KeyDown(evt){
	evt = evt || window.event;
	var kc = evt.keyCode;
	if(kc==13){
		checkBlogContentForm();
	}
}

function getBlogItem2List(){
	var blogItem1Id = document.getElementById("blogContent.blogItem1Id").value;
	BlogItem.getBlogItem1List(blogItem1Id, callback);
}

function callback(str){
	DWRUtil.removeAllOptions("blogContent.blogItem2Id");
	DWRUtil.addOptions("blogContent.blogItem2Id", str);
}

//检查BlogContent的提交
function checkBlogContentForm(){
	var theForm = document.getElementById("blogContentForm");
	
	if(!checkBlogContentTitle()){
		document.getElementById("blogContent.title").focus();
		return;
	}
	
	if(!checkBlogContentCon()){
		document.getElementById("blogContent.content").focus();
		return;
	}

	if(!checkBlogContentWeather()){
		document.getElementById("blogContent.weather").focus();
		return;
	}
	
	if(!checkBlogContentCity()){
		document.getElementById("blogContent.city").focus();
		return;
	}

	if(!checkBlogContentItem1()){
		document.getElementById("blogContent.blogItem1Id").focus();
		return;
	}

	if(!checkBlogContentItem2()){
		document.getElementById("blogContent.blogItem2Id").focus();
		return;
	}
	
	theForm.submit();
}

function checkBlogContentTitle(){
	var o = document.getElementById("blogContent.title");
	if(Trim(o.value)==""){
		alert("标题不能为空!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 100)){
		alert("标题的长度不能大于100!");
		return false;
	}
	return true;
}

function checkBlogContentCon(){
	var o = document.getElementById("blogContent.content");
	if(Trim(o.value)==""){
		alert("内容不能为空!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 20000)){
		alert("内容的长度不能大于20000!");
		return false;
	}
	return true;
}

function checkBlogContentWeather(){
	var o = document.getElementById("blogContent.weather");
	if(Trim(o.value)==""){
		alert("天气不能为空!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 50)){
		alert("天气的长度不能大于50!");
		return false;
	}
	return true;
}

function checkBlogContentCity(){
	var o = document.getElementById("blogContent.city");
	if(Trim(o.value)==""){
		alert("城市不能为空!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 50)){
		alert("城市的长度不能大于50!");
		return false;
	}
	return true;
}

function checkBlogContentItem1(){
	var o = document.getElementById("blogContent.blogItem1Id");
	if(o.value=="0"){
		alert("请选择明细1!");
		return false;
	}
	return true;
}

function checkBlogContentItem2(){
	var o = document.getElementById("blogContent.blogItem2Id");
	if(o.value=="0"){
		alert("请选择明细2!");
		return false;
	}
	return true;
}

//删除BlogContent
function deleteBlogContent(blogContentId){
	if (confirm("你确定要删除Blog吗？")){ 
		window.location="blogContentAction!logicDeleteBlogContent.action?blogContentId="+blogContentId;
	}
}