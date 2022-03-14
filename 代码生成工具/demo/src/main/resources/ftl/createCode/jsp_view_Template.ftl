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
	
	$(window).scroll(function () {
		fixedBtn($);
	});
	$(window).resize(function () {
		fixedBtn($);
	});	
});
</script>
<div class="layout layui-container viewpage">
	<form class="layui-form">
	<div class="aj-panel">
		<div class="aj-panel-body">
			<div class="layui-row">
<#list fieldList as var>
			<#if var[6] != "id">
				<#if var[6] != "state">
					<#if var[6] != "createUser">
						<#if var[6] != "createTime">
							<#if var[6] != "updateUser">
								<#if var[6] != "updateTime">
				<div class="layui-col-xs12 layui-col-sm12 layui-col-md6">
					<strong>${var[2] }:</strong>${r"${pd."}${var[6] }${r"}"}
				</div>
								</#if>
							</#if>
						</#if>
					</#if>
				</#if>
			</#if>
</#list>
				<c:if test="${r"${closeButtenType == 0}"}">
					<div class="layui-col-xs12 layui-col-sm12 layui-col-md12  fixed-container">
						<div class="fixed-mask">
							<a class="layui-btn layui-btn-primary" data-type="close">关闭</a>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
	</form>
</div>
</body>
</html>