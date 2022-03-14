<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.gttss.rfidlibrary.dao.${packageName}.${typeName}Dao">

	<!-- 查询符合条件的记录数,包含分页查询、条件查询 -->
	<select id="findCount"  parameterType="pageData" resultType="Integer">
		select
			count(a.id)
		from ${tableName} as a
		where a.state != 1

	</select>
	
	<!-- 查询符合条件的记录,包含分页查询、条件查询 -->
	<select id="findList"  parameterType="pageData" resultType="pageData">
		select 
	<#list fieldList as var>
			a.${var[0]} as "${var[1]}"<#if var_has_next>,</#if>
	</#list>
		from 
			${tableName} as a
		where a.state != 1

		ORDER BY a.create_time DESC

	</select>
	
	<!-- 新增-->
	<insert id="add" parameterType="pageData">
		insert into ${tableName}
		<trim prefix="(" suffix=")" suffixOverrides="," >
	<#list fieldList as var>
		<#if var[0] != 'id' >
				${var[0]},
		</#if>
	</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides="," >
	<#list fieldList as var>
		<#if var[0] != 'id' >
				${r"#{"}${var[1]}${r"}"},
		</#if>
	</#list>
		</trim>
	</insert>
	
	<!-- 通过ID获取数据 -->
	<select id="findByUuid" parameterType="pageData" resultType="pageData">
		select 
	<#list fieldList as var>
			a.${var[0]} as "${var[1]}"<#if var_has_next>,</#if>
	</#list>
		from 
			${tableName} as a
		where 
			a.uuid = ${r"#{uuid}"}
	</select>
	
	<!-- 修改 -->
	<update id="edit" parameterType="pageData">
		update  ${tableName}
			<set >
		<#list fieldList as var>
			<#if var[0] != 'state' && var[0] != 'id' && var[0] != 'uuid' >
				<if test="${var[1]} != null">
					${var[0]} = ${r"#{"}${var[1]}${r"}"},
				</if>
			</#if>
		</#list>
			</set>
			where 
				uuid = ${r"#{uuid}"}
	</update>
	
	<!-- 删除-->
	<delete id="delete" parameterType="pageData">
		update  ${tableName}
			set 
			    update_user = ${r"#{updateUser}"},
			    update_time = ${r"#{updateTime}"},
				state = 1
		where 
			uuid = ${r"#{uuid}"}
	</delete>
	
	<!-- 批量删除 -->
	<delete id="deleteAll" parameterType="String">
		update  ${tableName}
			set 
			    update_user = ${r"#{updateUser}"},
			    update_time = ${r"#{updateTime}"},
				state = 1
		where 
			uuid in
		<foreach item="item" index="index" collection="array" open="(" separator="," close=")">
                 ${r"#{item}"}
		</foreach>
	</delete>
	
	<!-- 查询全部数据（状态正常的所有数据，但不包括删除数据） -->
	<select id="dataListPage" parameterType="pageData" resultType="pageData">
		select
		<#list fieldList as var>
				a.${var[0]} as "${var[1]}" <#if var_has_next>,</#if>
		</#list>
				
		from 
				${tableName} as a
		where a.state != 1
	</select>
	
	<!-- 查询全部数据（所有记录，包括已经删除的记录） -->
	<select id="listAll" parameterType="pageData" resultType="pageData">
		select
		<#list fieldList as var>
				a.${var[0]} as "${var[1]}" <#if var_has_next>,</#if>
		</#list>
				
		from 
				${tableName} a
	</select>
</mapper>