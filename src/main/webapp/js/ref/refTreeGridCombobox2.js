/*
 * 版    权:  Timesoft Copyright 2016-2020,All rights reserved
 * 文 件 名:  refGridcombogrid2.js
 * 描       述:  参照refGridcombogrid2组件
 * 修改人:  zhangx
 * 修改时间:  2019-10-10
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
(function($) {
    //保存源方法
    $.fn.combotreegrid.methods_old = new Object();
    $.fn.combotreegrid.methods_old = $.fn.extend(true,{},$.fn.combotreegrid.methods);
    //改写方法
    /*
    $.fn.combogrid.methods['loadData'] = function(jq, data){
        if(data){
            $.fn.combogrid.methods_old['loadData'](jq,data);
        }else{
            var op = $(this).combogrid("options");

        }
    };
    */
    //扩展属性
    $.extend($.fn.combotreegrid.defaults,{
        //自定义参照Key
        refKey:'',
        //组件ID
        refId:'',
        //父级参照组件ID
        parentRefId:'',
        //参照类型 0  默认类型 1 combbox 2 treeCombobox 3 gridCombobox
        refType:'',
        rootpath:'',
        //参照值字段
        valueField:"code",
        //参照文本字段
        textField :"name",
        //下拉grid设置的ID字段
    	idField:'id',
        //上下级字段
        treeField:'code',
        //参照宽度
        panelWidth:450,
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
        //参照列
        columns:[[
            {field:'code',title:'代码',width:100},
            {field:'name',title:'名称',width:300}
        ]],
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
                //获取子参照组件ID
                var c_id = op.childOption.refId;
                var p_id = op.refId;
                //合并组建选项到子参照组件
                var c_op = new Object();
                c_op = $.extend(true,{},op.childOption);
                c_op.parentRefId = op.refId;
				//设置主参照组件onSelect事件
                if (c_op.refType == '0'){
                    //默认combotreegrid组件
                    $(c_id).combotreegrid(c_op);
                    op.onClickRow = function(rec){
                        $(c_id).combotreegrid('addQueryParams',rec);
                        $(c_id).combotreegrid('clear');
                        $(c_id).combotreegrid('loadRefData');
                    };
                }else if (c_op.refType == '1'){
                    //combobox组件
                    $(c_id).combobox(c_op);
                    op.onClickRow = function(rec){
                        $(c_id).combobox('addQueryParams',rec);
                        $(c_id).combobox('clear');
                        $(c_id).combobox('loadRefData');
                    };
                }else if (c_op.refType == '2'){
                    //combotree组件
                    $(c_id).combotree(c_op);
                    op.onClickRow = function(rec){
                        $(c_id).combotree('addQueryParams',rec);
                        $(c_id).combotree('clear');
                        $(c_id).combotree('loadRefData');
                    };
                }else if (c_op.refType == '3'){
                    //combogrid组件
                    $(c_id).combogrid(c_op);
                    op.onClickRow = function(rec){
                        $(c_id).combogrid('addQueryParams',rec);
                        $(c_id).combogrid('clear');
                        $(c_id).combogrid('loadRefData');
                    };
                }else if (c_op.refType == '4'){
                    //默认combobox组件
                    $(c_id).combotreegrid(c_op);
                    op.onClickRow = function(rec){
                        $(c_id).combotreegrid('addQueryParams',rec);
                        $(c_id).combotreegrid('clear');
                        $(c_id).combotreegrid('loadRefData');
                    };
                }else if (c_op.refType == '5'){
                    //默认combobox组件
                    $(c_id).combo(c_op);
                    op.onClickRow = function(rec){
                        $(c_id).combo('addQueryParams',rec);
                        $(c_id).combo('clear');
                        $(c_id).combo('loadRefData');
                    };
                }else {
                    //默认combobox组件
                    $(c_id).combobox(c_op);
                    op.onClickRow = function(rec){
                        $(c_id).combobox('addQueryParams',rec);
                        $(c_id).combobox('clear');
                        $(c_id).combobox('loadRefData');
                    };
                }
				//如有默认值时触发onSelect事件获取子参照数据
				if (op.isSetdefaultValue){
					var rec = $(p_id).combotreegrid('valueObj');
					if (rec) op.onClickRow(rec);
				}
            }
        },
        childOption:{}
    });

    //扩展方法
    $.extend($.fn.combotreegrid.methods,{
        /**
         * 装载参照数据
         * @param data 数据内容
         */
        loadRefData:function(jq,data){
            var id = jq.selector;
            if (data){
                $(id).combotreegrid("loadData",data);
            }else{
                var op = $(id).combotreegrid("options");
                op.loadRefDataBeforeProc(op);
                var url = op.refUrlPath(op);
                var requestdata = op.requestRefData(jq,op,url);
                $(id).combotreegrid("grid").datagrid("loadData", requestdata.data);
                if (op.isSetdefaultValue){
                	//初始化时参数设置了默认值时,以初始化的默认值优先
                	if (op.defaultValue){
                		$(id).combotreegrid("setValue",op.defaultValue);
	                    var grid = $(id).combotreegrid('grid');
						var rowIndex = grid.datagrid('getRowIndex', op.defaultValue);
						grid.datagrid('selectRow',rowIndex);                		
                	}else if (typeof(requestdata.defaultValue)!='undefined' && requestdata.defaultValue != null && requestdata.defaultValue!=''){
	                    $(id).combotreegrid("setValue",requestdata.defaultValue);
	                    var grid = $(id).combotreegrid('grid');
						var rowIndex = grid.datagrid('getRowIndex', requestdata.defaultValue);
						grid.datagrid('selectRow',rowIndex);
	                }
                }
                //combotreegrid
                op.loadRefDataAfterProc(op,requestdata);
            }
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
            var oldLoadDataMethod = $.fn.combotreegrid.methods_old[key]
            return oldLoadDataMethod.call($.fn.combotreegrid.methods,jq,a,b,c,d);
        },
        /**
         * 添加查询参数
         * @param params 参数
         */
        addQueryParams:function(jq,params){
            var id = jq.selector;
            var op = $(id).combotreegrid("options");
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
            var op = $(id).combotreegrid('options');
            var obj = new Object();
            var code = $(id).combotreegrid('getValue');
            var text = $(id).combotreegrid('getText');
            var grid = $(id).combotreegrid('grid');
            var rowh = grid.datagrid('getRowIndex',code);
            obj[op.valueField] = code;
            obj[op.textField] = text;
            obj['rowh'] = rowh;
            return obj;
        },
        /**
         * 获取子组件选中的值对象内容
         */
        childValueObj:function(jq){
        	var c_id = this.getChildRefId(jq);
        	return $(c_id).combotreegrid('valueObj');
        },
        /**
         * 获取子组件选中的值
         */
        getChildValue:function(jg){
        	var c_id = this.getChildRefId(jq);
            return $(c_id).combotreegrid('getValue');
        },
        /**
         * 获取子组件选中的文本
         */
        getChildText:function(jg){
        	var c_id = this.getChildRefId(jq);
            return $(c_id).combotreegrid('getText');
        },
        /**
         * 获取子组件参照ID
         */
        getChildRefId:function(jq){
        	var id = jq.selector;
        	var op = $(id).combotreegrid('options');
        	var c_id = op.childOption.refId;
        	return c_id;
        },
        getSelected:function(jq){
        	var id = jq.selector;
        	var g = $(id).combotreegrid('grid');
        	var r = g.datagrid('getSelected');
        	return r;
        },
        getRow:function(jq,rowh){
        	var id = jq.selector;
        	var g = $(id).combotreegrid('grid');
        	var rows = g.datagrid('getRows');
        	var v = rows[rowh]; 
        	return v;
        }
    });
})(jQuery);