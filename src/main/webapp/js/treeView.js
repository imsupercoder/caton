function Class() { }
Class.prototype.construct = function() {};
Class.extend = function(def) {
    var classDef = function() {
        if (arguments[0] !== Class) { this.construct.apply(this, arguments); }
    };
    
    var proto = new this(Class);
    var superClass = this.prototype;
    
    for (var n in def) {
        var item = def[n];                        
        if (item instanceof Function) item.$ = superClass;
        proto[n] = item;
    }

    classDef.prototype = proto;

    classDef.extend = this.extend;        
    return classDef;
};

var treeView =Class.extend({
	
	construct: function() { /* optional constructor method */ },
	
	treeObj:null,
	
	initTree:function(url,treeId,setting,data,callback){//初始化树
		$.ajax({
            url : url,
            data: data?data:'{}',
            type: 'post',
            contentType : "application/json;charset=utf-8",
            success : function(nodes) {
            	$.fn.zTree.init($("#"+treeId),setting, nodes);
            	treeView.treeObj=$.fn.zTree.getZTreeObj(treeId);
            	if(typeof callback =="function"){
            		callback();
            	}
			},
            dataType : "json"
        });
	},
	
	addNode:function(url,data,callback){//添加新节点
		var flag=true;
		if(treeView.treeObj.getSelectedNodes().length==0){//没有选中节点统一添加为根目录节点
			flag=false;
			data.parentId=0;
		}else{
			data.parentId=selectNodes()[0].id;
		}
		
		$.ajax({
            url : url,
            data : JSON.stringify(data),
            method : "POST",
            contentType : "application/json;charset=utf-8",
            success : function(id) {
            	if(typeof callback =="function"){
            		callback(data);
            	}
            	data.id=id;
            	
            	if(flag){
            		treeView.treeObj.addNodes(selectNodes()[0], data);
            	}else{
            		treeView.treeObj.addNodes(null, data);
            	}
            	
            	
			},
            statusCode : {
                400 : function(d) {
                    alert(d.responseText);
                }
            }
        });
	},
	
	updateNode:function(url,data,callback){//修改节点
		$.ajax({
            url : url,
            method : "PUT",
            contentType : "application/json;charset=utf-8",
            data :JSON.stringify(data),
            success : function(num) {
            	if(typeof callback =="function"){
            		callback(data);
            	}
            	var selectedNode=selectNodes()[0];
            	selectedNode.name = data.name;//节点名name
            	treeView.treeObj.updateNode(selectedNode);
			},
            dataType : "json",
            statusCode : {
                400 : function(d) {
                    alert(d.responseText);
                }
            }
        });
	},
	
	deleteNode:function(url,callback){//删除节点
		var selectedNode=selectNodes()[0];
		$.ajax({
            url : url+"/"+selectedNode.id,
            contentType : "application/json;charset=utf-8",
            method : "DELETE",
            success : function(data) {
            	treeView.treeObj.removeNode(selectedNode);
            	$('#deleteModal').modal('hide');
            	if(typeof callback =="function"){
            		callback();
            	}
			},
            dataType : "json"
        });
   			
	}
	

});

//获取选中节点
function selectNodes(){
	var selectedNodes=treeView.treeObj.getSelectedNodes();
	if(selectedNodes.length==0){
		alert("请先选中一个节点");
		return null;
	}else{
		return selectedNodes;
	}
}

//展开树节点
function expandNode(tId,level){
	if(null!=tId && typeof(tId) != "undefined"){
		treeView.treeObj.expandNode(treeView.treeObj.getNodeByTId(tId), true, false, false);
	}else{
		//如没有传入指定节点，则默认展开一层
		var nodes = treeView.treeObj.getNodesByParam("level", level?level:0);
		for(var j=0; j<nodes.length; j++) {
			treeView.treeObj.expandNode(nodes[j], true, false, false);
		}
		
	}
}