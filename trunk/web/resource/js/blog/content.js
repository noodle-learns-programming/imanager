function getBlogItem2List(){
	var blogItem1Id = document.getElementById("blogContent.blogItem1Id").value;
	BlogItem.getBlogItem1List(blogItem1Id, callback);
}

function callback(str){
	DWRUtil.removeAllOptions("blogContent.blogItem2Id");
	DWRUtil.addOptions("blogContent.blogItem2Id", str);
}

//���BlogContent���ύ
function checkBlogContentSubmit(){
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
	return true;
}

function checkBlogContentCon(){
	var o = document.getElementById("blogContent.content");
	if(Trim(o.value)==""){
		alert("���ݲ���Ϊ��!");
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