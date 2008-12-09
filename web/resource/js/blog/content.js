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

//���BlogContent���ύ
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
		alert("���ⲻ��Ϊ��!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 100)){
		alert("����ĳ��Ȳ��ܴ���100!");
		return false;
	}
	return true;
}

function checkBlogContentCon(){
	var o = document.getElementById("blogContent.content");
	if(Trim(o.value)==""){
		alert("���ݲ���Ϊ��!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 20000)){
		alert("���ݵĳ��Ȳ��ܴ���20000!");
		return false;
	}
	return true;
}

function checkBlogContentWeather(){
	var o = document.getElementById("blogContent.weather");
	if(Trim(o.value)==""){
		alert("��������Ϊ��!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 50)){
		alert("�����ĳ��Ȳ��ܴ���50!");
		return false;
	}
	return true;
}

function checkBlogContentCity(){
	var o = document.getElementById("blogContent.city");
	if(Trim(o.value)==""){
		alert("���в���Ϊ��!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 50)){
		alert("���еĳ��Ȳ��ܴ���50!");
		return false;
	}
	return true;
}

function checkBlogContentItem1(){
	var o = document.getElementById("blogContent.blogItem1Id");
	if(o.value=="0"){
		alert("��ѡ����ϸ1!");
		return false;
	}
	return true;
}

function checkBlogContentItem2(){
	var o = document.getElementById("blogContent.blogItem2Id");
	if(o.value=="0"){
		alert("��ѡ����ϸ2!");
		return false;
	}
	return true;
}

//ɾ��BlogContent
function deleteBlogContent(blogContentId){
	if (confirm("��ȷ��Ҫɾ��Blog��")){ 
		window.location="blogContentAction!logicDeleteBlogContent.action?blogContentId="+blogContentId;
	}
}