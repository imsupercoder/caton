
function InitData(dataUrl,parameter,modelcallback) {
	console.log("event=="+parameter.pageNum);
	        $.ajax({  
	            url: dataUrl,  
	            method: 'post',
	            contentType : "application/json;charset=utf-8",
	            cache: false,  
	            data:ko.toJSON(parameter),  
	            dataType: 'json',  
	            success: function (u) {  // 返回json数据
	            	modelcallback(u);
  
                    var total = parseInt(u.total); // 总的记录数
                    var pSize = parseInt(u.pageSize);
                    $("#pagination").pagination({
        	        	dataSource: dataUrl,
        	        	locator:"data",//'data'=javabean的List属性名
        	        	totalNumber:total,
        	        	pageSize: pSize,
        	        	pageNumber:parameter.pageNum,
        	        	showGoInput: true,
        	            showGoButton: true,
        	            ajax: {
        	            	method: 'post',
        		            contentType : "application/json;charset=utf-8",
        		            cache: false,  
        		            data:ko.toJSON(parameter)
        	            },
        	            afterPageOnClick: pageselectCallback,
        	            afterPreviousOnClick : pageselectCallback,
        	            afterNextOnClick : pageselectCallback,
        	            afterGoButtonOnClick : pageselectCallback
        	        });
                      
	            }  
	        });
	        this.pageselectCallback = function(event,pageNumber) {
		    	console.log(pageNumber);
		    	parameter.pageNum = pageNumber;
		        InitData(dataUrl,parameter,modelcallback);
		    }

	    }  

	     