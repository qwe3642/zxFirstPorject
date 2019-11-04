/*
 * 版    权:  Timesoft Copyright 2016-2020,All rights reserved
 * 文 件 名:  refCombobox.js
 * 描       述:  参照refCombobox组件
 * 修改人:  LUOXW
 * 修改时间:  2019-09-27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
(function($) {
	//保存源方法
	$.fn.combo.methods_old = new Object();
	$.fn.combo.methods_old = $.fn.extend(true,{},$.fn.combo.methods);
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
	$.extend($.fn.combo.defaults,{
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
		//子参照类型 0  默认类型 1 combbox 2 treeCombobox 3 gridCombobox
		refType:'0',
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
		/**
		 * 参照数据URL路径
		 * @param op 参照属性选项
		 */
		refUrlPath:function(op){
			var url_f = "{0}/publicRef/getZdyRefData";
			var rootpath = path;
			if (rootpath){
				op.rootpath = rootpath;
			}
			var url = String.format(url_f,op.rootpath);		
			//op.url = url;
			return url;
		},

		/**
		 * 请求参照数据
		 * @param op 参照属性选项
		 * @param url 参照数据URL路径
		 */
		requestRefData:function(jq,op,url){
			var datas;
			//当前组件ID
			var id = jq.selector;
			//返回结果对象
			var result = new Object();
			//参数对象
			var param = new Object();
			param.key = op.refKey;
			//组件中设置了查询参数时合并到参数对象
			if (op.queryParams){
				param = $.fn.extend(true,param,op.queryParams);
			}
			//转换查询参数,用户特定参数结构时的改写
			param = this.convertQueryParams(param,op);
			$.ajax({
				type : "POST",
				url : url,
				data : param,
				dataType : "json",
				async:false,
				success:function callback(data){
					datas = data;
				},  
		        error : function(data,textstatus){  
		            alert(data.responseText);  
		        }
			});
			//转换查询结果,将返回查询数据转换成组件规定格式
			result = this.convertData(datas.returnObject,op);
			//过滤参照数据
			var filterResult = this.filterRefData(jq, op, result.data);
			result.data = filterResult;
			return result;
		},
		/**
		 * 转换数据内容
		 * @param returnObject 获取的数据内容
		 * @param op 参照属性选项
		 */		
		convertData:function(returnObject,op){
			var result = new Object();
			if (returnObject.data){
				result.data = returnObject.data;
			}else if (returnObject.rows) {
				result.data = returnObject.rows;
			}else{
				result.data = returnObject;
			}
			result.defaultValue = returnObject.defaultValue;
			//默认插入一个空行
			var nullRow = new Object();
			nullRow[op.valueField] = "";
			nullRow[op.textField] = "";
			result.data.splice(0, 0, nullRow);
			return result;
		},
		/**
		 * 过滤参照数据
		 * @param op 参照选项
		 * @param data 数据内容
		 */
		filterRefData:function(jq,op,data){
			var result = new Array();
			if (op.filterValues.isFilter && op.filterValues.values.length >0){
				for (var i = 0; i < data.length; i++) {
					var r = data[i];
					var v = r[this.valueField];
					var check = checkValue(op.filterValues.values, v)
					if (this.filterValues.filterMode=='delete'){
						if (check){
							continue;
						}else{
							result.push(r);
						}
					}else{
						if (check){
							result.push(r);
						}else{
							continue;
						}
					}
				}
			}else{
				result = data;
			}
			return result;
			
			function checkValue(values,v){
				var result = false;
				for (var i = 0; i < values.length; i++) {
					if (values[i]==v){
						result = true;
						break;
					}
				}
				return result;
			}			
		},		
		/**
		 * 转换查询参数
		 * @param params 参数
		 */		
		convertQueryParams:function(params,op){
			return params;
		},
		/**
		 * 调用loadRefData方法前置过程改写
		 * @param op 参照属性选项
		 */	
		loadRefDataBeforeProc:function(op){

		},
		/**
		 * 调用loadRefData方法后置过程改写
		 * @param op 参照属性选项
		 */			
		loadRefDataAfterProc:function(op,data){
			//设置了子参照ID时触发
			if (op.childOption.refId){
				var p_id = op.refId;
				//获取子参照组件ID
				var c_id = op.childOption.refId;
				//合并组建选项到子参照组件
				var c_op = new Object(); 
				c_op = $.extend(true,{},op.childOption);
				c_op.parentRefId = op.refId;
				//设置主参照组件onSelect事件
				if (c_op.refType == '0'){
					//默认combobox组件
					$(c_id).combo(c_op);
					op.onChange = function(newValue, oldValue){
						var rec = $(p_id).combo('valueObj');
						$(c_id).combo('addQueryParams',rec);
						$(c_id).combo('clear');
						$(c_id).combo('loadRefData');
					};					
				}else if (c_op.refType == '1'){
					//combobox组件
					$(c_id).combobox(c_op);
					op.onChange = function(newValue, oldValue){
						var rec = $(p_id).combo('valueObj');
						$(c_id).combobox('addQueryParams',rec);
						$(c_id).combobox('clear');
						$(c_id).combobox('loadRefData');
					};
				}else if (c_op.refType == '2'){
					//combotree组件
					$(c_id).combotree(c_op);
					op.onChange = function(newValue, oldValue){
						var rec = $(p_id).combo('valueObj');
						$(c_id).combotree('addQueryParams',rec);
						$(c_id).combotree('clear');
						$(c_id).combotree('loadRefData');
					};
				}else if (c_op.refType == '3'){
					//combogrid组件
					$(c_id).combogrid(c_op);
					op.onChange = function(newValue, oldValue){
						var rec = $(p_id).combo('valueObj');
						$(c_id).combogrid('addQueryParams',rec);
						$(c_id).combogrid('clear');
						$(c_id).combogrid('loadRefData');
					};
				}else if (c_op.refType == '4'){
                    //combogrid组件
                    $(c_id).combotreegrid(c_op);
                    op.onChange = function(newValue, oldValue){
                        var rec = $(p_id).combo('valueObj');
                        $(c_id).combotreegrid('addQueryParams',rec);
                        $(c_id).combotreegrid('clear');
                        $(c_id).combotreegrid('loadRefData');
                    };
                }else if (c_op.refType == '5'){
					//默认combobox组件
					$(c_id).combo(c_op);
					op.onChange = function(newValue, oldValue){
						var rec = $(p_id).combo('valueObj');
						$(c_id).combo('addQueryParams',rec);
						$(c_id).combo('clear');
						$(c_id).combo('loadRefData');
					};
				}else{
					//默认combobox组件
					$(c_id).combo(c_op);
					op.onChange = function(newValue, oldValue){
						var rec = $(p_id).combo('valueObj');
						$(c_id).combo('addQueryParams',rec);
						$(c_id).combo('clear');
						$(c_id).combo('loadRefData');
					};
				}
				//如有默认值时触发onSelect事件获取子参照数据
				if (op.isSetdefaultValue){
					var rec = $(p_id).combo('valueObj');
					if (rec) op.onChange(rec);
				}
				
			}
		},
		//子参照组件选项
		childOption:{}
	});
	
	//扩展方法
	$.extend($.fn.combo.methods,{
		/**
		 * 装载参照数据
		 * @param data 数据内容
		 */	
		loadRefData:function(jq,data){
			var id = jq.selector;
			if (data){
				$(id).combo("loadData",data);
			}else{
				var op = $(id).combo("options");
				op.refId = id;
				op.loadRefDataBeforeProc(op);
				var url = op.refUrlPath(op);
				var requestdata = op.requestRefData(jq,op,url);
				$(id).combo("loadData",requestdata.data);
				//设置默认为true时
				if (op.isSetdefaultValue){
					//初始化时参数设置了默认值时,以初始化的默认值优先
					if (op.defaultValue){
						$(id).combo("setValue",op.defaultValue);
					}else if (typeof(requestdata.defaultValue)!='undefined' && requestdata.defaultValue != null && requestdata.defaultValue!=''){
						$(id).combo("setValue",requestdata.defaultValue);
					}
				}
				op.loadRefDataAfterProc(op,requestdata);
			}
		},
		/**
		 * 装载参照数据
		 * @param data 数据内容
		 */	
		loadData:function(jq,data){
			var id = jq.selector;
			var op = $(id).combo("options");
			var f_input = '<div style="margin-bottom:5px"><input type="radio" name="lang" value="{0}"/><span>{1}</span></div>';
			var data_html = '';
			var cb_id = 'cb_' + id.replace('#','');
			var result_html = '<div id="{0}"><div style="padding:5px">{1}</div></div>';
			for (var i = 0; i < data.length; i++) {
				var r = data[i];
				var r_html = String.format(f_input,r[op.valueField],r[op.textField]);
				data_html = data_html + r_html;
			}
			result_html = String.format(result_html,cb_id,data_html);
			$(id).combo('panel').empty();
			$(id).combo('panel').append(result_html);
			$('#' + cb_id + ' input').click(function(a,c){
				var v = $(this).val();
				var s = $(this).next('span').text();
				$(id).combo('setValue', v).combo('setText', s).combo('hidePanel');
			});
		},
		/**
		 * 调用老方法
		 * @param key 方法名
		 * @param a 参数1
		 * @param b 参数2
		 * @param c 参数3
		 * @param d 参数4
		 */			
		callOldMethod:function(jq,key,a,b,c,d){
			var oldLoadDataMethod = $.fn.combo.methods_old[key]
			return oldLoadDataMethod.call($.fn.combo.methods,jq,a,b,c,d);
		},
		/**
		 * 添加查询参数
		 * @param params 参数
		 */			
		addQueryParams:function(jq,params){
			var id = jq.selector;
			var op = $(id).combo("options");
			if (params){
				op.queryParams = $.fn.extend(op.queryParams,params);
			}
			return op.queryParams;  
		},
		/**
		 * 获取当前组件选中的值对象内容
		 */			
		valueObj:function(jq){
			var id = jq.selector;
			var op = $(id).combo('options');
			var obj = new Object();
			var code = $(id).combo('getValue');
			var text = $(id).combo('getText');
			obj[op.valueField] = code;
			obj[op.textField] = text;
			return obj;
		},
		/**
		 * 获取子组件选中的值对象内容
		 */			
		childValueObj:function(jq){
			var c_id = this.getChildRefId(jq);
			return $(c_id).combo('valueObj');
		},
		/**
		 * 获取子组件选中的值
		 */			
		getChildValue:function(jg){
			var c_id = this.getChildRefId(jq);
			return $(c_id).combo('getValue');
		},
		/**
		 * 获取子组件选中的文本
		 */			
		getChildText:function(jg){
			var c_id = this.getChildRefId(jq);
			return $(c_id).combo('getText');
		},
		/**
		 * 获取子组件参照ID
		 */
        getChildRefId:function(jq){
        	var id = jq.selector;
        	var op = $(id).combo('options');
        	var c_id = op.childOption.refId;
        	return c_id;
        }		
	});	
})(jQuery);