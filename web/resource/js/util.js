
function LTrim(str){
	var i;
	for(i=0;i<str.length;i++){
	 if(str.charAt(i)!=" "&&str.charAt(i)!=" ") 
	 		break;
	}
	str = str.substring(i,str.length);
	return str;
}

function RTrim(str){
	var i;
	for(i=str.length-1;i>=0;i--){
		if(str.charAt(i)!=" "&&str.charAt(i)!=" ") 
			break;
	}
	str = str.substring(0,i+1);
	return str;
}

function Trim(str){
	return LTrim(RTrim(str));
}

function checkEmails(email){
	return  /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(email);
}


function checkEmailsBak(emailStr) {
	if (emailStr.length == 0) {
		return false;
	}
	var emailPat=/^(.+)@(.+)$/;
	var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
	var validChars="\[^\\s" + specialChars + "\]";
	var quotedUser="(\"[^\"]*\")";
	var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
	var atom=validChars + '+';
	var word="(" + atom + "|" + quotedUser + ")";
	var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
	var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
	var matchArray=emailStr.match(emailPat);
	if (matchArray == null) {
		return false;
	}
	var user=matchArray[1];
	var domain=matchArray[2];
	if (user.match(userPat) == null) {
		return false;
	}
	var IPArray = domain.match(ipDomainPat);
	if (IPArray != null) {
		for (var i = 1; i <= 4; i++) {
			if (IPArray[i] > 255) {
				return false;
			}
		}
		return true;
	}
	var domainArray=domain.match(domainPat);
	if (domainArray == null) {
		return false;
	}
	var atomPat=new RegExp(atom,"g");
	var domArr=domain.match(atomPat);
	var len=domArr.length;
	if ((domArr[domArr.length-1].length < 2) ||
		(domArr[domArr.length-1].length > 3 && domArr[domArr.length-1].indexOf("disabled")<0)) {
		return false;
	}
	if (len < 2) {
		return false;
	}
	return true;
}

   
function checkByteLength(str, minlen, maxlen) {
	if (str == null) return false;
	var l = str.length;
	var blen = 0;
	for(i=0; i<l; i++) {
		if ((str.charCodeAt(i) & 0xff00) != 0) {
			blen ++;
		}
		blen ++;
	}
	if (blen > maxlen || blen < minlen) {
		return false;
	}
	return true;
}

function checkInputLength(str, maxlen) {
	var len = str.match(/[^ -~]/g) == null ? str.length : str.length + str.match(/[^ -~]/g).length ;
	
	if (len < maxlen || len == maxlen) {
		return true;
	}
	return false;
}

function getInputLen(str) {
	var len = str.match(/[^ -~]/g) == null ? str.length : str.length + str.match(/[^ -~]/g).length ;
	return len;
}

function getInputLenWithoutRN(str) {
	var len = str.match(/[^\r|\n| -~]/g) == null ? str.replace(/[\r|\n]/g,"").length : str.replace(/[\r|\n]/g,"").length + str.match(/[^\r|\n| -~]/g).length ;
	return len;
}

function copyToClipboard(txt) {
    if(window.clipboardData) {
        window.clipboardData.clearData();
        window.clipboardData.setData("Text", txt);
    } else if(navigator.userAgent.indexOf("Opera") != -1) {
        window.location = txt;
    } else if (window.netscape) {
        try {
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        } catch (e) {
            alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
        }
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if (!clip)
            return;
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if (!trans)
            return;
        trans.addDataFlavor('text/unicode');
        var str = new Object();
        var len = new Object();
        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
        var copytext = txt;
        str.data = copytext;
        trans.setTransferData("text/unicode",str,copytext.length*2);
        var clipid = Components.interfaces.nsIClipboard;
        if (!clip)
            return false;
        clip.setData(trans,null,clipid.kGlobalClipboard);
        //alert("复制成功！") ;
    }
}