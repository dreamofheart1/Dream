<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<meta name="renderer" content="webkit"/>
<meta name="force-rendering" content="webkit"/>
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title></title>
<%@ include file="/WEB-INF/jsp/system/admin/head.jsp"%>
<#assign i = 0>
<#list fieldList as var>
	<#if var[5] == '1'>
	<#assign i = i + 1>
	</#if>
</#list>
</head>
<body>
<script type="text/html" id="barDemo">
	<!-- 这里同样支持 laytpl 语法，如： -->
${r'
	{{#  if(${QX.cha == 1}){ }}
		<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
	{{#}}}
	{{#  if(${QX.edit == 1}){ }}
		{{#  if(${user.username != "admin"}){ }}
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		{{#}}}
		{{#  if(${user.username == "admin"}){ }}
			<a class="layui-btn layui-btn-xs" lay-event="edit">您不能编辑</a>
		{{#}}}
	{{#}}}
	{{#  if(${QX.del == 1}){ }}
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	{{#}}}
'}
</script>
<script type="text/javascript">
layui.use(['form', 'element','laydate','layer','table'], function(){
	var form = layui.form;
	var element = layui.element;
	var laydate = layui.laydate;
	var layer = layui.layer;
	var table = layui.table;
	var $ = layui.jquery;
	var locat = (window.location+'').split('/');
	$(function(){
		if('main'== locat[3]){
			locat =  locat[0]+'//'+locat[2];
		}else{
			locat =  locat[0]+'//'+locat[2]+'/'+locat[3];
		}
	});
	element.init();
	form.render('select');
	//初始化日期空间
	<#list fieldList as var>
		<#if var[1] == 'Date' && var[5] == '1' >
	laydate.render({
		elem: '#${var[6] }StartTime'
		,type: 'datetime'
	});
	laydate.render({
		elem: '#${var[6] }EndTime'
		,type: 'datetime'
	});
		</#if>
	</#list>
	
	//初始化列表显示的字段
	var cols = [[
		{ type: 'numbers', title: '序号',width: '5%' },
		<#list fieldList as var>
			<#if var[6] != "state">
			{field: '${var[6]}',title:'${var[2]}',templet:'<div><div title="{{d.${var[6]}}}">{{d.${var[6]}}}</div></div>'},
			</#if>
		</#list>
		{fixed: 'right', align:'center', toolbar: '#barDemo'}
	]];
	table.render({
		elem:'#table_report'
		,id: 'table_id'
		,cols:cols
		,url:'${packageName}/${typeNameLower}/list.do'
		,request: {
			pageName: 'page' //页码的参数名称，默认：page
			,limitName: 'limit' //每页数据量的参数名，默认：limit
		}
		,response: {
			statusName: 'code' //数据状态的字段名称，默认：code
			,statusCode: 0 //成功的状态码，默认：0
			,msgName: 'msg' //状态信息的字段名称，默认：msg
			,countName: 'count' //数据总数的字段名称，默认：count
			,dataName: 'data' //数据列表的字段名称，默认：data
		}
		,done: function(res, curr, count){
			$("td").each(function(){
				for(var i=0;i<cols[0].length;i++){
					if(cols[0][i]["field"]==$(this).attr("data-field")){
						$(this).attr("data-title",cols[0][i]["title"]);
						break;
					}
				}
			});
		}
		,page: true //开启分页
		,limits: [10,20,50]
		,limit: 10 //默认采用
		,skin: 'line' //行边框风格
		,even: true //开启隔行背景
	});

	//监听工具条
	table.on('tool(demo)', function(obj){
		var data = obj.data;
		if (obj.event === 'detail') {
			//layer.msg('id：'+ data.id + ' 的查看操作');
			layer.full(layer.open({
	   			type: 2,
	   			title:'查看',
	   			maxmin:true,
	   			content: '${packageName}/${typeNameLower}/goView.do?id='+data.id+'&closeButtenType=0',
	   			//area: ['807px', '460px'],
	   			end: function (data) {
	   				table.reload('table_id', {});
	            }
	   		}));
		} else if (obj.event === 'del') {
			layer.confirm('真的删除行么', function(index){
				var url = '${packageName}/${typeNameLower}/delete.do?id='+data.id+'&tm='+new Date().getTime();
				$.post(url,function(data){
					layer.msg(data.msg);
					obj.del();
					layer.close(index);
					table.reload('table_id');
				});
			});
		} else if (obj.event === 'edit') {
			layer.full(layer.open({
	   			type: 2,
	   			title:'编辑',
	   			maxmin:true,
	   			content: '${packageName}/${typeNameLower}/goEdit.do?id='+data.id,
	   			//area: ['807px', '460px'],
	   			end: function (data) {
	   				table.reload('table_id', {
	   					//url: '${typeNameLower}/list.do?'
	   					//,where: $('#myForm').serializeObject()  //设定异步数据接口的额外参数json格式
	   				});
	            }
	   		}));
		}
	});
	//此处为html5的监听按钮点击事件
	var active = {
		reload: function(){
			table.reload('table_id', {
				page: {curr: 1 }//重新从第 1 页开始
				,where: $('#myForm').serializeObject() //设定异步数据接口的额外参数json格式
			});
		},
		add : function(){
   			layer.full(layer.open({
   	   			type: 2,
   	   			title:'新增',
   	   			maxmin:true,
   	   			content: '${packageName}/${typeNameLower}/goAdd.do',
   	   			//area: ['807px', '460px'],
   	   			end: function () {
	   	   			table.reload('table_id', {
	   					//url: '${packageName}/${typeNameLower}/list.do?'
	   					//,where: $('#myForm').serializeObject()  //设定异步数据接口的额外参数json格式
	   				});
   	            }
   	   		}));
   		}
	};
	$(document).on('click','.layui-btn',function(){
   		var type = $(this).data('type');
   		active[type] ? active[type].call(this) : '';
   	});
	
	//form序列化为对象
	$.fn.serializeObject = function() {
	    var json = {};
	    var arrObj = this.serializeArray();
	    $.each(arrObj, function() {
	      if (json[this.name]) {
	           if (!json[this.name].push) {
	            json[this.name] = [ json[this.name] ];
	           }
	           json[this.name].push(this.value || '');
	      } else {
	           json[this.name] = this.value || '';
	      }
	    });
	    return json;
	};	
});
</script>
<div class="layout layui-container listpage">
	<div id="queryArea" class="aj-panel">
		<div class="aj-panel-body">
			<div class="layui-row">
				<form id="myForm" class="layui-form">
				<#list fieldList as var>
					<#if var[3] == "是" && var[5] == '1' >
						<#if var[1] == 'Date' >
					<div class="layui-col-xs12 layui-col-sm3 layui-col-md3">
						<div class="form-item">
							<label class="form-label">选择开始${var[2] }</label>
							<div class="input-block">
								<input id="${var[6] }StartTime" name="${var[6] }StartTime" 
									placeholder="这里输入${var[2] }" class="layui-input" type="text"/>
							</div>
						</div>
					</div>
					<div class="layui-col-xs12 layui-col-sm3 layui-col-md3">
						<div class="form-item">
							<label class="form-label" >选择结束${var[2] }</label>
							<div class="input-block">
								<input id="${var[6] }EndTime" name="${var[6] }EndTime" 
									placeholder="这里输入${var[2] }" class="layui-input" type="text"/>
							</div>
						</div>
					</div>
						</#if>
						<#if var[1] == 'String' >
					<div class="layui-col-xs12 layui-col-sm3 layui-col-md3">
						<div class="form-item">
							<label class="form-label">${var[2] }</label>
							<div class="input-block">
								<input  name="${var[6] }QueryData" id="${var[6] }QueryData" 
									placeholder="这里输入${var[2] }" class="layui-input" type="text"/>
							</div>
						</div>
					</div>
						</#if>
						<#if var[1] == 'Integer' >
						</#if>
					</#if>
				</#list>
				</form>
				<div class="layui-col-xs12 layui-col-sm3 layui-col-md3 btn-serach-team">
				<#if i gt 0 >
					<c:if test="${r'${QX.cha == 1}'}">
						<a class="layui-btn" title="检索" data-type="reload"><i class="iconfont aj-jiansuo"></i></a>
					</c:if>
				</#if>
					<c:if test="${r'${QX.add == 1 }'}">
						<a class="layui-btn" title="新增" data-type="add"><i class="iconfont aj-xinzeng"></i> 新增</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<div class="aj-panel">
		<div class="aj-panel-body">
			<table id="table_report" class="layui-table" lay-filter="demo">
			</table>
		</div>
	</div>
</div>
</body>
</html>