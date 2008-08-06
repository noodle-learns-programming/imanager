
//检查BlogItem1的提交
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
		alert("中文名不能为空!");
		return false;
	}
	return true;
}

function checkBlogItem1Eng(){
	var o = document.getElementById("blogItem1.itemEng");
	if(Trim(o.value)==""){
		alert("拼音不能为空!");
		return false;
	}
	return true;
}

//删除BlogItem2

var itemId;
		
function deleteBlogItem1(blogItem1Id){
	itemId = blogItem1Id;
	BlogItem.isItem1HasItem2(blogItem1Id, callback);
}

function callback(flag){
	if(flag == "true"){
		alert("该一级类型下还存在二级类型，不能删除！");
	}else if(flag == "false"){
		if (confirm("你确定要删除一级类型吗？")){ 
			window.location="blogItemAction!logicDeleteBlogItem1.action?blogItem1Id="+itemId;
		}
	}else{
		alert("系统异常！");
	}
}

//检查BlogItem2的提交
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
		alert("请选择一级类型!");
		return false;
	}
	return true;
}

function checkBlogItem2Chn(){
	var o = document.getElementById("blogItem2.itemChn");
	if(Trim(o.value)==""){
		alert("中文名不能为空!");
		return false;
	}
	return true;
}

function checkBlogItem2Eng(){
	var o = document.getElementById("blogItem2.itemEng");
	if(Trim(o.value)==""){
		alert("拼音不能为空!");
		return false;
	}
	return true;
}

//删除BlogItem2
function deleteBlogItem2(blogItem2Id){
	if (confirm("你确定要删除二级类型吗？")){ 
		window.location="blogItemAction!logicDeleteBlogItem2.action?blogItem2Id="+blogItem2Id;
	}
}