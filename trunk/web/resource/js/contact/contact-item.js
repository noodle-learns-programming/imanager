//���ContactType���ύ
function checkContactItemForm(){
	var theForm = document.getElementById("contactItemForm");
	
	if(!checkContactName()){
		document.getElementById("contactItem.name").focus();
		return;
	}
	
	if(!checkContactPinyin()){
		document.getElementById("contactItem.pinyin").focus();
		return;
	}
	
	if(!checkContactType()){
		document.getElementById("contactItem.contactType.contactTypeId").focus();
		return;
	}
	
	if(!checkEnglishName()){
		document.getElementById("contactItem.englishName").focus();
		return;
	}
	
	if(!checkNickName()){
		document.getElementById("contactItem.nickName").focus();
		return;
	}
	
	if(!checkNativePlace()){
		document.getElementById("contactItem.nativePlace").focus();
		return;
	}
	
	if(!checkAddress()){
		document.getElementById("contactItem.address").focus();
		return;
	}
	
	if(!checkZip()){
		document.getElementById("contactItem.zip").focus();
		return;
	}
	
	if(!checkSchool()){
		document.getElementById("contactItem.school").focus();
		return;
	}
	
	if(!checkSchoolAddress()){
		document.getElementById("contactItem.schoolAddress").focus();
		return;
	}
	
	if(!checkCompany()){
		document.getElementById("contactItem.company").focus();
		return;
	}
	
	if(!checkCompanyAddress()){
		document.getElementById("contactItem.companyAddress").focus();
		return;
	}
	
	if(!checkMobile1()){
		document.getElementById("contactItem.mobile1").focus();
		return;
	}
	
	if(!checkMobile1City()){
		document.getElementById("contactItem.mobile1City").focus();
		return;
	}
	
	if(!checkMobile2()){
		document.getElementById("contactItem.mobile2").focus();
		return;
	}
	
	if(!checkMobile2City()){
		document.getElementById("contactItem.mobile2City").focus();
		return;
	}
	
	if(!checkFamilyPhone1()){
		document.getElementById("contactItem.familyPhone1").focus();
		return;
	}
	
	if(!checkFamilyPhone1City()){
		document.getElementById("contactItem.familyPhone1City").focus();
		return;
	}
	
	if(!checkFamilyPhone2()){
		document.getElementById("contactItem.familyPhone2").focus();
		return;
	}
	
	if(!checkFamilyPhone2City()){
		document.getElementById("contactItem.familyPhone2City").focus();
		return;
	}
	
	if(!checkWorkPhone1()){
		document.getElementById("contactItem.workPhone1").focus();
		return;
	}
	
	if(!checkWorkPhone1City()){
		document.getElementById("contactItem.workPhone1City").focus();
		return;
	}
	
	if(!checkWorkPhone2()){
		document.getElementById("contactItem.workPhone2").focus();
		return;
	}
	
	if(!checkWorkPhone2City()){
		document.getElementById("contactItem.workPhone2City").focus();
		return;
	}
	
	if(!checkEmail1()){
		document.getElementById("contactItem.email1").focus();
		return;
	}
	
	if(!checkEmail1Note()){
		document.getElementById("contactItem.email1Note").focus();
		return;
	}
	
	if(!checkEmail2()){
		document.getElementById("contactItem.email2").focus();
		return;
	}
	
	if(!checkEmail2Note()){
		document.getElementById("contactItem.email2Note").focus();
		return;
	}
	
	if(!checkQq1()){
		document.getElementById("contactItem.qq1").focus();
		return;
	}
	
	if(!checkQq1Note()){
		document.getElementById("contactItem.qq1Note").focus();
		return;
	}
	
	if(!checkQq2()){
		document.getElementById("contactItem.qq2").focus();
		return;
	}
	
	if(!checkQq2Note()){
		document.getElementById("contactItem.qq2Note").focus();
		return;
	}
	
	if(!checkMsn1()){
		document.getElementById("contactItem.msn1").focus();
		return;
	}
	
	if(!checkMsn1Note()){
		document.getElementById("contactItem.msn1Note").focus();
		return;
	}
	
	if(!checkMsn2()){
		document.getElementById("contactItem.msn2").focus();
		return;
	}
	
	if(!checkMsn2Note()){
		document.getElementById("contactItem.msn2Note").focus();
		return;
	}
	
	if(!checkYahoo1()){
		document.getElementById("contactItem.yahoo1").focus();
		return;
	}
	
	if(!checkYahoo1Note()){
		document.getElementById("contactItem.yahoo1Note").focus();
		return;
	}
	
	if(!checkYahoo2()){
		document.getElementById("contactItem.yahoo2").focus();
		return;
	}
	
	if(!checkYahoo2Note()){
		document.getElementById("contactItem.yahoo2Note").focus();
		return;
	}
	
	if(!checkAliwang1()){
		document.getElementById("contactItem.aliwang1").focus();
		return;
	}
	
	if(!checkAliwang1Note()){
		document.getElementById("contactItem.aliwang1Note").focus();
		return;
	}
	
	if(!checkAliwang2()){
		document.getElementById("contactItem.aliwang2").focus();
		return;
	}
	
	if(!checkAliwang2Note()){
		document.getElementById("contactItem.aliwang2Note").focus();
		return;
	}
	
	if(!checkWeb1()){
		document.getElementById("contactItem.web1").focus();
		return;
	}
	
	if(!checkWeb1Note()){
		document.getElementById("contactItem.web1Note").focus();
		return;
	}
	
	if(!checkWeb2()){
		document.getElementById("contactItem.web2").focus();
		return;
	}
	
	if(!checkWeb2Note()){
		document.getElementById("contactItem.web2Note").focus();
		return;
	}
	
	if(!checkOther1()){
		document.getElementById("contactItem.other1").focus();
		return;
	}
	
	if(!checkOther1Note()){
		document.getElementById("contactItem.other1Note").focus();
		return;
	}
	
	if(!checkOther2()){
		document.getElementById("contactItem.other2").focus();
		return;
	}
	
	if(!checkOther2Note()){
		document.getElementById("contactItem.other2Note").focus();
		return;
	}
	
	if(!checkNote()){
		document.getElementById("contactItem.note").focus();
		return;
	}
	
	theForm.submit();
}

function checkContactName(){
	var o = document.getElementById("contactItem.name");
	if(Trim(o.value)==""){
		alert("��������Ϊ��!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 20)){
		alert("�����ĳ��Ȳ��ܴ���20!");
		return false;
	}
	return true;
}

function checkContactPinyin(){
	var o = document.getElementById("contactItem.pinyin");
	if(Trim(o.value)==""){
		alert("ƴ������Ϊ��!");
		return false;
	}
	if(!checkInputLength(Trim(o.value), 20)){
		alert("ƴ���ĳ��Ȳ��ܴ���20!");
		return false;
	}
	return true;
}

function checkContactType(){
	var o = document.getElementById("contactItem.contactType.contactTypeId");
	if(o.value=="0"){
		alert("��ѡ����ϵ����!");
		return false;
	}
	return true;
}

function checkEnglishName(){
	var o = document.getElementById("contactItem.englishName");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("Ӣ�����ĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkNickName(){
	var o = document.getElementById("contactItem.nickName");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("�ǳƵĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkNativePlace(){
	var o = document.getElementById("contactItem.nativePlace");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 100)){
			alert("����ĳ��Ȳ��ܴ���100!");
			return false;
		}
	}
	return true;
}

function checkAddress(){
	var o = document.getElementById("contactItem.address");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 100)){
			alert("��ϵ��ַ�ĳ��Ȳ��ܴ���100!");
			return false;
		}
	}
	return true;
}

function checkZip(){
	var o = document.getElementById("contactItem.zip");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 10)){
			alert("�ʱ�ĳ��Ȳ��ܴ���10!");
			return false;
		}
	}
	return true;
}

function checkSchool(){
	var o = document.getElementById("contactItem.school");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 100)){
			alert("�Ͷ�ѧУ�ĳ��Ȳ��ܴ���100!");
			return false;
		}
	}
	return true;
}

function checkSchoolAddress(){
	var o = document.getElementById("contactItem.schoolAddress");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 100)){
			alert("ѧУ��ַ�ĳ��Ȳ��ܴ���100!");
			return false;
		}
	}
	return true;
}

function checkCompany(){
	var o = document.getElementById("contactItem.company");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 100)){
			alert("������λ�ĳ��Ȳ��ܴ���100!");
			return false;
		}
	}
	return true;
}

function checkCompanyAddress(){
	var o = document.getElementById("contactItem.companyAddress");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 100)){
			alert("��λ��ַ�ĳ��Ȳ��ܴ���100!");
			return false;
		}
	}
	return true;
}

function checkMobile1(){
	var o = document.getElementById("contactItem.mobile1");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("�ֻ�1�ĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkMobile1City(){
	var o = document.getElementById("contactItem.mobile1City");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("�ֻ�1���еĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkMobile2(){
	var o = document.getElementById("contactItem.mobile2");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("�ֻ�2�ĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkMobile2City(){
	var o = document.getElementById("contactItem.mobile2City");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("�ֻ�2���еĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkFamilyPhone1(){
	var o = document.getElementById("contactItem.familyPhone1");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("��ͥ�绰1�ĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkFamilyPhone1City(){
	var o = document.getElementById("contactItem.familyPhone1City");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("��ͥ�绰1���еĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkFamilyPhone2(){
	var o = document.getElementById("contactItem.familyPhone2");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("��ͥ�绰2�ĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkFamilyPhone2City(){
	var o = document.getElementById("contactItem.familyPhone2City");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("��ͥ�绰2���еĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkWorkPhone1(){
	var o = document.getElementById("contactItem.workPhone1");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("�����绰1�ĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkWorkPhone1City(){
	var o = document.getElementById("contactItem.workPhone1City");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("�����绰1���еĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkWorkPhone2(){
	var o = document.getElementById("contactItem.workPhone2");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("�����绰2�ĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkWorkPhone2City(){
	var o = document.getElementById("contactItem.workPhone2City");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("�����绰2���еĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkEmail1(){
	var o = document.getElementById("contactItem.email1");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("Email1�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkEmail1Note(){
	var o = document.getElementById("contactItem.email1Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("Email1��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkEmail2(){
	var o = document.getElementById("contactItem.email2");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("Email2�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkEmail2Note(){
	var o = document.getElementById("contactItem.email2Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("Email2��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkQq1(){
	var o = document.getElementById("contactItem.qq1");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 15)){
			alert("QQ1�ĳ��Ȳ��ܴ���15!");
			return false;
		}
	}
	return true;
}

function checkQq1Note(){
	var o = document.getElementById("contactItem.qq1Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("QQ1��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkQq2(){
	var o = document.getElementById("contactItem.qq2");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 15)){
			alert("QQ2�ĳ��Ȳ��ܴ���15!");
			return false;
		}
	}
	return true;
}

function checkQq2Note(){
	var o = document.getElementById("contactItem.qq2Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("QQ2��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkMsn1(){
	var o = document.getElementById("contactItem.msn1");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("Msn1�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkMsn1Note(){
	var o = document.getElementById("contactItem.msn1Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("Msn1��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkMsn2(){
	var o = document.getElementById("contactItem.msn2");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("Msn2�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkMsn2Note(){
	var o = document.getElementById("contactItem.msn2Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("Msn2��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkYahoo1(){
	var o = document.getElementById("contactItem.yahoo1");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("�Ż�ͨ1�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkYahoo1Note(){
	var o = document.getElementById("contactItem.yahoo1Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("�Ż�ͨ1��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkYahoo2(){
	var o = document.getElementById("contactItem.yahoo2");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("�Ż�ͨ2�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkYahoo2Note(){
	var o = document.getElementById("contactItem.yahoo2Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("�Ż�ͨ2��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkAliwang1(){
	var o = document.getElementById("contactItem.aliwang1");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("����1�ĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkAliwang1Note(){
	var o = document.getElementById("contactItem.aliwang1Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("����1��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkAliwang2(){
	var o = document.getElementById("contactItem.aliwang2");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 20)){
			alert("����2�ĳ��Ȳ��ܴ���20!");
			return false;
		}
	}
	return true;
}

function checkAliwang2Note(){
	var o = document.getElementById("contactItem.aliwang2Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("����2��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkWeb1(){
	var o = document.getElementById("contactItem.web1");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 100)){
			alert("������ҳ1�ĳ��Ȳ��ܴ���100!");
			return false;
		}
	}
	return true;
}

function checkWeb1Note(){
	var o = document.getElementById("contactItem.web1Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("������ҳ1��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkWeb2(){
	var o = document.getElementById("contactItem.web2");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 100)){
			alert("������ҳ2�ĳ��Ȳ��ܴ���100!");
			return false;
		}
	}
	return true;
}

function checkWeb2Note(){
	var o = document.getElementById("contactItem.web2Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("������ҳ2��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkOther1(){
	var o = document.getElementById("contactItem.other1");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 100)){
			alert("����1�ĳ��Ȳ��ܴ���100!");
			return false;
		}
	}
	return true;
}

function checkOther1Note(){
	var o = document.getElementById("contactItem.other1Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("����1��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkOther2(){
	var o = document.getElementById("contactItem.other2");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 100)){
			alert("����2�ĳ��Ȳ��ܴ���100!");
			return false;
		}
	}
	return true;
}

function checkOther2Note(){
	var o = document.getElementById("contactItem.other2Note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 30)){
			alert("����2��ע�ĳ��Ȳ��ܴ���30!");
			return false;
		}
	}
	return true;
}

function checkNote(){
	var o = document.getElementById("contactItem.note");
	if(Trim(o.value)!=""){
		if(!checkInputLength(Trim(o.value), 1000)){
			alert("��ע�ĳ��Ȳ��ܴ���1000!");
			return false;
		}
	}
	return true;
}

//ɾ��ContactType
function deleteContactItem(contactItemId){
	if (confirm("��ȷ��Ҫɾ����ϵ����ϸ��")){ 
		window.location="contactItemAction!logicDeleteContactItem.action?contactItemId="+contactItemId;
	}
}