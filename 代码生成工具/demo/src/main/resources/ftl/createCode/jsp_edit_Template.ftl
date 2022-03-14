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
</head>
<body>
<script type="text/javascript">
layui.use(['form', 'element','laydate','layer'], function(){
	var form = layui.form;
	var element = layui.element;
	var laydate = layui.laydate;
	var layer = layui.layer;
	var $ = layui.jquery;
	element.init();
	form.render('select');
	//初始化日期空间
	<#list fieldList as var>
		<#if var[3] == "是">
			<#if var[1] == 'Date' && var[6] != "createTime">
	laydate.render({
		elem: '#${var[6] }'
		,type: 'datetime'
	});
			</#if>
		</#if>
	</#list>
	
	//自定义前端验证
	form.verify({
		
	});
	var active = {
		close : function(){
			//当你在iframe页面关闭自身时
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭 
		}
	};
	$(document).on('blur','input',function(){
   		var type = $(this).data('type');
   		active[type] ? active[type].call(this) : '';
   	});
	$(document).on('click','.layui-btn',function(){
   		var type = $(this).data('type');
   		active[type] ? active[type].call(this) : '';
   	});
	//监听表单提交事件
	form.on('submit(save)', function(data){
 		//console.log(data.elem); //得到select原始DOM对象
 		//console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
		//console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
		$.ajax({
			type: "POST",
			url: '${packageName}/${typeNameLower}/${r"${msg}"}.do',
	    	data: data.field,
			dataType:'json',
			cache: false,
			success: function(data){
				if(data.code === 0){
					//当你在iframe页面关闭自身时
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index); //再执行关闭
					parent.layer.msg(data.msg);
				}else{
					layer.msg(data.msg);
				}
			},
			error: function(data){
				//layer.msg(data.msg);
			}
		})
	});
	$(window).scroll(function () {
		fixedBtn($);
	});
	$(window).resize(function () {
		fixedBtn($);
	});	
});
</script>
<div class="layout layui-container">
	<form class="layui-form">
	<input type="hidden" name="id" id="id" value="${r"${pd.id}"}"/>
	<div class="aj-panel">
		<div class="aj-panel-body">
			<div class="layui-row">
<#list fieldList as var>
	<#if var[3] == "是">
		<#if var[1] == 'Date'>
			<#if var[6] == 'createTime'>
				<c:if test="${r"${msg=='edit'}"}">
					<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
						<div class="form-item">
							<label class="form-label not-null">记录创建时间</label>
							<div class="input-block">
								<input name="createTime" value="${r"${pd.createTime}"}" maxlength="32" 
									lay-verify="required" lay-verType="tips" placeholder="这里输入记录创建时间" 
									title="记录创建时间" class="layui-input" readonly="readonly" type="text"/>
							</div>
						</div>
					</div>
				</c:if>
			<#else>
				<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					<div class="form-item">
						<label class="form-label">${var[2] }</label>
						<div class="input-block">
							<input name="${var[6] }" value="${r"${pd."}${var[6] }${r"}"}" maxlength="32" 
								lay-verify="required" lay-verType="tips" placeholder="这里输入${var[2] }" 
								title="${var[2] }" class="layui-input" type="text"/>
						</div>
					</div>
				</div>
			</#if>
		<#else>
			<#if var[6] != "id"><#if var[6] != "state"><#if var[6] != "createUser"><#if var[6] != "createTime"><#if var[6] != "updateUser"><#if var[6] != "updateTime">
				<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
					<div class="form-item">
						<label class="form-label not-null">${var[2] }</label>
						<div class="input-block">
							<input name="${var[6] }" value="${r"${pd."}${var[6] }${r"}"}" maxlength="32" 
								lay-verify="required" lay-verType="tips" placeholder="这里输入${var[2] }" 
								title="${var[2] }" class="layui-input" type="text"/>
						</div>
					</div>
				</div>
			</#if></#if></#if></#if></#if></#if>
		</#if>
	</#if>
</#list>
				<div class="layui-col-xs12 layui-col-sm12 layui-col-md12  fixed-container">
					<div class="fixed-mask">
						<a class="layui-btn" lay-submit lay-filter="save">保存</a>
						<a class="layui-btn layui-btn-primary" data-type="close">取消</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
</div>
</body>
</html>