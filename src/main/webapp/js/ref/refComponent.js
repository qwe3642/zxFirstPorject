/*
 * 版    权:  Timesoft Copyright 2016-2020,All rights reserved
 * 文 件 名:  refComponent.js
 * 描       述:  参照组件
 * 修改人:  LUOXW
 * 修改时间:  2019-09-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
(function($) {
	
	/**
	 * 初始化refComponent组件方法
	 * 参数: key 方法名称(或者是options对象)
	 *       a 参数1
	 *       b 参数2
	 *       c 参数3
	 */
	$.fn.refComponent=function(key,a,b,c){
		var id = this.selector;
		if(typeof key=="string"){
			if ($.fn.refComponent.methods[key]){
				return $.fn.refComponent.methods[key](this,a,b,c);
			}
			return $.fn.refComponent.methods['callRefMethod'](this,key,a,b,c);
		}else{
			var refobj = $.fn.refComponent.refObjects[id];
			var obj = $(id);
			var op;
			if (refobj){
				op = $.fn.refComponent.methods["options"](this);
				$.fn.extend(op,key);
 			}else{
 				op = (function(id){
					op=new Object();
					//深度复制默认选项值
					op = $.fn.extend(true,op,$.fn.refComponent.defaults);
					op.refId = id;
					op.path=path;
					op = $.fn.extend(true,op,key);
					return op;
				})(id);
			}
			//根据配置的参照类型生成参照组件
			if (op.refType =='1'){
				refobj = $(id).combobox(op);
				$.fn.refComponent.refObjects[id] = refobj.combobox;
			} else if (op.refType =='2'){
				refobj = $(id).combotree(op);
				$.fn.refComponent.refObjects[id] = refobj.combotree;
			} else if (op.refType =='3'){
				refobj = $(id).combogrid(op);
				$.fn.refComponent.refObjects[id] = refobj.combogrid;
			} else if (op.refType =='4'){
                refobj = $(id).combotreegrid(op);
                $.fn.refComponent.refObjects[id] = refobj.combotreegrid;
            } else if (op.refType =='5'){
				refobj = $(id).combo(op);
				$.fn.refComponent.refObjects[id] = refobj.combo;
			} else{
				refobj = $(id).combobox(op);
				$.fn.refComponent.refObjects[id] = refobj.combobox;
			}
			
		}
	};
	
	//保存源方法
	$.fn.refComponent.refObjects = new Object();
	//改写方法
	/*
	$.fn.combobox.methods['loadData'] = function(jq, data){
		if(data){
			$.fn.combobox.methods_old['loadData'](jq,data);
		}else{
			var op = $(this).combobox("options");
			
		}
	};
	*/
	//扩展属性
	$.fn.refComponent.defaults = new Object();
	$.extend($.fn.refComponent.defaults,{
		//自定义参照Key
		refKey:'',
		//组件ID
		refId:'',
		//父级参照组件ID
		parentRefId:'',
		//应用服务根路径
		rootpath:'',
		//参照值字段
		valueField:"code",
		//参照文本字段
		textField :"name",
		//参照类型  1 combbox 2 treeCombobox 3 gridCombobox
		refType:'1',
        //是否设置默认值
        isSetdefaultValue:true,		
		//默认值
		defaultValue:'',
		//过滤值列表
		filterValues:{
		   //是否启用值过滤
		   isFilter:false,
		   //delete值列表中数据删除,save值列表中数据保留
		   filterMode:'delete',
		   //需要过滤的值列表
		   values:[]
		},			
		//子参照组件选项
		childOption:{}
	});
	
	//扩展方法
	$.fn.refComponent.methods = new Object();
	$.extend($.fn.refComponent.methods,{
		/**
		 * 装载参照数据
		 * @param data 数据内容
		 */	
		loadRefData:function(jq,data){
			this.callRefMethod(jq, "loadRefData");
		},
		/**
		 * 调用子組件方法
		 * @param key 方法名
		 * @param a 参数1
		 * @param b 参数2
		 * @param c 参数3
		 * @param d 参数4
		 */			
		callRefMethod:function(jq,key,a,b,c,d){
			var refObj = this.getRefObject(jq)
			return refObj.methods[key](jq,a,b,c,d);
		},
		/**
		 * 设置或者获取refSearchbox组件参数
		 * @param id 表格ID
		 * @param a 参数
		 * @returns options
		 */
		options:function(jq){
			return this.callRefMethod(jq,'options');
		},	
		/**
		 * 获取参照组件对象
		 * @param a 参数
		 * @returns options
		 */
		getRefObject:function(jq,a){
			var id = jq.selector;
			if(typeof a=="undefined"){
				return $.fn.refComponent.refObjects[id];
			}else{
				$.fn.refComponent.refObjects[id] = a;
				return a;
			}			
		},			
		/**
		 * 添加查询参数
		 * @param params 参数
		 */			
		addQueryParams:function(jq,params){
			return this.callRefMethod(jq,'addQueryParams',params); 
		},
		/**
		 * 获取当前组件选中的值对象内容
		 */			
		valueObj:function(jq){
			return this.callRefMethod(jq,'valueObj'); 
		},
		/**
		 * 获取子组件选中的值对象内容
		 */			
		childValueObj:function(jq){
			return this.callRefMethod(jq,'childValueObj'); 
		},
		/**
		 * 获取子组件选中的值
		 */			
		getChildValue:function(jg){
			return this.callRefMethod(jq,'getChildValue'); 
		},
		/**
		 * 获取子组件选中的文本
		 */			
		getChildText:function(jg){
			return this.callRefMethod(jq,'getChildText'); 
		},
		/**
		 * 获取子组件参照ID
		 */
        getChildRefId:function(jq){
			return this.callRefMethod(jq,'getChildRefId'); 
        }		
	});	
})(jQuery);