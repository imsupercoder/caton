/**
 * 
 */

var allDateForSearch;
function filterByAnyString(obj) {
	var ie = isIE();
	var searchStr;
	if(getExplorer()=='ie'){
		searchStr = toUnicode($("#search").val());
	}else{
		searchStr = $("#search").val();
	}
	
	var jsonStr = JSON.stringify(obj);
	if (searchStr == '' ||searchStr == '查找搜索'|| jsonStr.indexOf(searchStr) != -1) {
		return true;
	} else {
		return false;
	}
};

function toUnicode(s){ 
    return s.replace(/([\u4E00-\u9FA5]|[\uFE30-\uFFA0])/g,function(newStr){
      return "\\u" + newStr.charCodeAt(0).toString(16);
    });
  }

//判断是否ie浏览器
function isIE() { //ie?       
	if (!!window.ActiveXObject || "ActiveXObject" in window)
		return true;
	else
		return false;
} 

function  getExplorer() {  
    var explorer = window.navigator.userAgent ;  
    //ie  
    if (explorer.indexOf("MSIE")  >= 0 || (explorer.indexOf("Windows NT 6.1;") >= 0 && explorer.indexOf("Trident/7.0;") >= 0)) {  
        //return 'ie';  
    	if(explorer.indexOf('MSIE') != -1) {
    		return 'ie'//explorer.split(';')[1].split('.')[0];
        }else {
        	return 'ie11';//'MSIE 11';
        }
    }  
    //firefox  
    else if (explorer.indexOf("Firefox") >= 0) {  
        return 'Firefox';  
    }  
    //Chrome  
    else if(explorer.indexOf("Chrome") >= 0){  
        return 'Chrome';  
    }  
    //Opera  
    else if(explorer.indexOf("Opera") >= 0){  
        return 'Opera';  
    }  
    //Safari  
    else if(explorer.indexOf("Safari") >= 0){  
        return 'Safari';  
    } 
    else{
    	return 'unknow';
    }
}  
