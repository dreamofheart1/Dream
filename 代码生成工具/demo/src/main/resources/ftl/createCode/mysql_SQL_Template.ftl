
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `${tableName}`
-- ----------------------------
DROP TABLE IF EXISTS `${tableName}`;
CREATE TABLE `${tableName}` (
 		`ID` varchar(32) NOT NULL COMMENT '主键',
	<#list fieldList as var>
		<#if var[1] == 'Integer'>
		`${var[0]}` int NOT NULL COMMENT '${var[2]}',
		<#elseif var[1] == 'Date'>
		`${var[0]}` varchar(20) DEFAULT NULL COMMENT '${var[2]}',
		<#else>
		`${var[0]}` varchar(255) DEFAULT NULL COMMENT '${var[2]}',
		</#if>
	</#list>
		`CREATE_USER` varchar(32) COMMENT '创建人',
		`CREATE_TIME` varchar(20) COMMENT '创建时间',
		`UPDATE_USER` varchar(32) COMMENT '修改人',
		`UPDATE_TIME` varchar(20) COMMENT '修改时间',
		`STATE` int COMMENT '状态'
  		PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;