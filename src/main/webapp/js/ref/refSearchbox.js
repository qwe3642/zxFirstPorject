/*
 * 版    权:  Timesoft Copyright 2016-2020,All rights reserved
 * 文 件 名:  refSearchbox.js
 * 描       述:  参照refSearchbox组件
 * 修改人:  LUOXW
 * 修改时间:  2019-09-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
(function($) {
	/**
	 * 初始化refSearchbox组件方法
	 * 参数: key 方法名称(或者是options对象)
	 *       a 参数1
	 *       b 参数2
	 *       c 参数3
	 */
	$.fn.refSearchbox=function(key,a,b,c){
		var id = this.selector;
		if(typeof key=="string"){
			return $.fn.refSearchbox.searchbox_methods[key](id,a,b,c);
		}else{
			var op = $.fn.refSearchbox.searchbox_options[id];
			if (op){
 			}else{
				op = (function(id){
					op=new Object();
					//保存的数据信息
					var rowData = new Object();
					$(id).data('refSearchbox_data',rowData);
					//深度复制默认选项值
					op = $.fn.extend(true,op,$.fn.refSearchbox.defaults);
					op.refId = id;
					op.path=path;
					op = $.fn.extend(true,op,key);
					op = $.fn.refSearchbox.searchbox_methods["options"](id,op);
					return op;			
				})(id);
				$(id).searchbox({ 
					searcher:function(value,name){ 
						var op = $(id).refSearchbox('options');
						op.searcher(value,name,op);
					}, 
					prompt:op.prompt,
					width:op.width,
					height:op.height
				}); 		
			}
			$.fn.extend(op,key);
		}
	};
	
	/**
	 * refSearchbox选项对象,用于存储组件参数信息
	 */
	$.fn.refSearchbox.searchbox_options =new Object();
	/**
	 * refSearchbox默认方法信息
	 */
	$.fn.refSearchbox.searchbox_methods ={
		/**
		 * 获取查询参数信息
		 * @param id 表格ID
		 * @return 
		 */
		getQueryParams:function(id){
			var op = this.options(id);
	 		return op.queryParams;
		},
		/**
		 * 设置查询参数
		 * @param id 表格ID
		 * @param p 参数
		 */
		setQueryParams:function(id,p){
			var op = this.options(id);
	 		op.queryParams = p;
		},		
		/**
		 * 设置或者获取refSearchbox组件参数
		 * @param id 表格ID
		 * @param a 参数
		 * @returns options
		 */
		options:function(id,a){
			if(typeof a=="undefined"){
				return $.fn.refSearchbox.searchbox_options[id];
			}else{
				var op =$.fn.refSearchbox.searchbox_options[id];
				op = $.fn.extend(true,op,a);
				$.fn.refSearchbox.searchbox_options[id]=op;
				return op;
			}
		},
		/**
		 * 设置或者获取refSearchbox组件参数
		 * @param id 表格ID
		 * @param a 参数
		 * @returns options
		 */		
		valueObj:function(id,v){
			if(typeof v=="undefined"){
				var value = $(id).data('refSearchbox_data');
				return value;
			}else{
				$(id).data('refSearchbox_data',v);
				return v;
			}			
		},
		/**
		 * 获取ID值
		 * @param id 组件ID
		 * @returns id
		 */		
		getId:function(id){
			var o = this.valueObj(id);
			var op = this.options(id);
			var v = op.getId(op,o);
			return v;
		},
		/**
		 * 获取Code值
		 * @param id 组件ID
		 * @returns code
		 */			
		getCode:function(id){
			var o = this.valueObj(id);
			var op = this.options(id);
			var v = op.getCode(op,o);
			return v;
		},
		/**
		 * 获取Name值
		 * @param id 组件ID
		 * @returns name
		 */			
		getName:function(id){
			var o = this.valueObj(id);
			var op = this.options(id);
			var v = op.getName(op,o);
			return v;
		},
		/**
		 * 设置获取refSearchbox组件当前操作是否选择了内容值
		 * @param id 组件ID
		 * @param v 状态值(boolean)
		 * @returns boolean
		 */			
		issuccess:function(id,v){
			var op = this.options(id);
			if(typeof v=="undefined"){
				return op.issuccess;
			}else{
				var success =op.issuccess = v;
				return success;
			}
		},
		/**
		 * 设置显示文本内容
		 * @param id 组件ID
		 * @param v 显示文本内容
		 */			
		setText:function(id,v){
			var text = v;
			if(typeof v=="undefined"){
				text = this.getName(id);
			}
			$(id).searchbox('setValue',text);
		},
		/**
		 * 设置获取显示文本内容值
		 * @param id 组件ID
		 * @returns text
		 */			
		getText:function(id){
			return $(id).searchbox('getValue');
		},	
		/**
		 * 清除组件
		 * @param id 组件ID
		 */		
		clear:function(id){
			var op = this.options(id);
			op.value = new Object();
			this.setText(id,'');
		},
		/**
		 * 设置组件宽度
		 * @param id 组件ID
		 * @param width 宽度值
		 */			
		resize:function(id,width){
			$(id).searchbox('resize',width);
		}
	};
	
	//默认属性对象
	$.fn.refSearchbox.defaults = new Object();
	$.fn.extend($.fn.refSearchbox.defaults,{
		searcher:function(value,name,op){
			var param_s = requestParamsToString(op.queryParams);
			if (param_s) param_s = "&" + param_s;
			var url = op.refUrlPath(op,param_s);
			openLayerModalDialog(op.refSearchboxName,op.refWidth,op.refHeight,url,op.queryParams,1,true,
				{
				  endcall : function(){
					var returnValue = getLayerModalDialogResult(layer);
					if (returnValue != null  && returnValue.success) {
						$(op.refId).refSearchbox('valueObj',returnValue.value);
						$(op.refId).refSearchbox('issuccess',true);
						$(op.refId).refSearchbox('setText');
						op.searcherSuccess(op,returnValue.value);
					}else{
						$(op.refId).refSearchbox('issuccess',false);
						op.searcherFailure(op);
					}
					op.endcall(op,value);
		 		  },
		 		  successcall: function(layero, index,childLayer){
		 			 op.successcall(op);
		 		  },
	 			  maxmin : true,
	 			  resize : true 
	 			}
 			);
			
			function requestParamsToString(queryParams){
				var param= new Array();
			    if (queryParams) {
			    	//遍历传递的其他查询参数添加到param中
			        for (var p in queryParams) {
			        	//排除函数
			            if (typeof (queryParams[p]) != "function") {
				           var v = p + "=" + queryParams[p];
				           param.push(v);
			            }
			        }
			        return param.join("&");
			    }
			    return '';
			}
		},
		//是否选中内容
		issuccess:false,
		//参照Searchbox键值
		refId:'',
		//参照键值
		refKey:'',
		//组件宽度
		width:0,
		//组件高度
		height:0,
		//参照弹出时宽度
		refWidth:'600px',
		//参照弹出时高度度
		refHeight:'800px',
		//参照弹出时title
		refSearchboxName:'Searchbox参照',
		//Searchbox未输入值显示内容
		prompt:'',
		//参照是否多选,默认单选
		multi:false,
		//应用服务默认根路径
		rootpath:'',
		//查询参数
		queryParams:new Object(),
		//参照选中内容后触发
		searcherSuccess:function(op,param){},
		//参照未选中后触发
		searcherFailure:function(op){},
		//参照打开成功后回调
		successcall:function(op){},
		//参照关闭后回调
		endcall:function(op,value){},
		//获取弹出参照url
		refUrlPath:function(op,param_s){
			var url_f = '{0}/publicRef/index?key={1}&multi={2}{3}';
			var rootpath = path;
			if (rootpath){
				op.rootpath = rootpath;
			}
			var url = String.format(url_f,op.rootpath,op.refKey,op.multi,param_s);
			return url;
		},
		getId:function(op,value){
			var v = value.id;
			if (typeof v=="undefined"){
				v = value.ids;
			}
			return v;
		},
		getCode:function(op,value){
			var v = value.code;
			if (typeof v=="undefined"){
				v = value.codes;
			}
			return v;
		},
		getName:function(op,value){
			var v = value.name;
			if (typeof v=="undefined"){
				v = value.names;
			}
			return v;
		}	
	});
})(jQuery);