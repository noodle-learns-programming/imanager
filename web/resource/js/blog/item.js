
//���BlogItem1���ύ
function checkBlogItem1Submit(){
	var theForm = document.getElementById("blogItem1Form");
	
	if(!checkBlogItem1Chn()){
		document.getElementById("blogItem1.itemChn").focus();
		return;
	}
	
	if(!checkBlogItem1Eng()){
		document.getElementById("blogItem1.itemEng").focus();
		return;
	}
	
	theForm.submit();
}

function checkBlogItem1Chn(){
	var o = document.getElementById("blogItem1.itemChn");
	if(Trim(o.value)==""){
		alert("����������Ϊ��!");
		return false;
	}
	return true;
}

function checkBlogItem1Eng(){
	var o = document.getElementById("blogItem1.itemEng");
	if(Trim(o.value)==""){
		alert("ƴ������Ϊ��!");
		return false;
	}
	return true;
}

//ɾ��BlogItem2

var itemId;
		
function deleteBlogItem1(blogItem1Id){
	itemId = blogItem1Id;
	BlogItem.isItem1HasItem2(blogItem1Id, callback);
}

function callback(flag){
	if(flag == "true"){
		alert("��һ�������»����ڶ������ͣ�����ɾ����");
	}else if(flag == "false"){
		if (confirm("��ȷ��Ҫɾ��һ��������")){ 
			window.location="blogItemAction!logicDeleteBlogItem1.action?blogItem1Id="+itemId;
		}
	}else{
		alert("ϵͳ�쳣��");
	}
}

//���BlogItem2���ύ
function checkBlogItem2Submit(){
	var theForm = document.getElementById("blogItem2Form");
	
	if(!checkBlogItem2IdSelect()){
		document.getElementById("blogItem2.blogItem1Id").focus();
		return false;
    }
	
	if(!checkBlogItem2Chn()){
		document.getElementById("blogItem2.itemChn").focus();
		return false;
	}
	
	if(!checkBlogItem2Eng()){
		document.getElementById("blogItem2.itemEng").focus();
		return false;
	}
}

function checkBlogItem2IdSelect(){
	var o = document.getElementById("blogItem2.blogItem1Id");
	if(o.value=="0"){
		alert("��ѡ��һ������!");
		return false;
	}
	return true;
}

function checkBlogItem2Chn(){
	var o = document.getElementById("blogItem2.itemChn");
	if(Trim(o.value)==""){
		alert("����������Ϊ��!");
		return false;
	}
	return true;
}

function checkBlogItem2Eng(){
	var o = document.getElementById("blogItem2.itemEng");
	if(Trim(o.value)==""){
		alert("ƴ������Ϊ��!");
		return false;
	}
	return true;
}

//ɾ��BlogItem2
function deleteBlogItem2(blogItem2Id){
	if (confirm("��ȷ��Ҫɾ������������")){ 
		window.location="blogItemAction!logicDeleteBlogItem2.action?blogItem2Id="+blogItem2Id;
	}
}