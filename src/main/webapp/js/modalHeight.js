$(function() {


	var height = findDimensions();
	var ie = isIE();
	
	if(ie==true){
		if(height<700){
			var obj = getClassNames('modal-body' , 'div'); 
			//obj.style.cssText = "max-height:400px;overflow-y:auto;";
			for (var i = 0, len = obj.length; i < len; i++) {  
				obj[i].style.cssText = "max-height:400px;overflow-y:auto;";
		    }  
		}
	}else{
		if(height<1000){
			var obj = document.getElementsByClassName('modal-body');
			//obj.style.cssText = "max-height:400px;overflow-y:auto;";
			for (var i = 0, len = obj.length; i < len; i++) {  
				obj[i].style.cssText = "max-height:400px;overflow-y:auto;";
		    }  
		}
	}
	
});

function findDimensions() // 函数：获取尺寸
{
	// 获取窗口宽度
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
	// 获取窗口高度
	if (window.innerHeight)
		winHeight = window.innerHeight;
	else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;
	// 通过深入Document内部对body进行检测，获取窗口大小
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	}
	// 结果输出至两个文本框
	/*document.form1.availHeight.value = winHeight;
	document.form1.availWidth.value = winWidth;*/
	return winHeight;
}

//判断是否ie浏览器
function isIE() { //ie?       
	if (!!window.ActiveXObject || "ActiveXObject" in window)
		return true;
	else
		return false;
} 

function getClassNames(classStr, tagName) {
	if (document.getElementsByClassName) {
		return document.getElementsByClassName(classStr)
	} else {
		var nodes = document.getElementsByTagName(tagName), ret = [];
		for (i = 0; i < nodes.length; i++) {
			if (hasClass(nodes[i], classStr)) {
				ret.push(nodes[i])
			}
		}
		return ret;
	}
}
function hasClass(tagStr, classStr) {
	var arr = tagStr.className.split(/\s+/); // 这个正则表达式是因为class可以有多个,判断是否包含
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == classStr) {
			return true;
		}
	}
	return false;
} 

