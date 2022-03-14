package com.gttss.rfidlibrary.controller.${packageName};


import com.gttss.rfidlibrary.service.${packageName}.${typeName}Service;

import com.gttss.entity.PageData;
import com.gttss.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "")
@RestController
@RequestMapping(value ="/${typeNameLower}",method = {RequestMethod.GET,RequestMethod.POST})
public class ${typeName}Controller {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ${typeName}Service ${typeNameLower}Service;

    @ApiOperation(value = "返回数据集合")
    @RequestMapping(value ="/list")
    public RetResult showList(@ApiIgnore @RequestParam Map map){
        logger.info("begin............");
		PageData pd= new PageData(map);
        List data= null;
        RetCode code= RetCode.SUCCESS;
        String msg="成功";
        try {
            data=${typeNameLower}Service.findList(pd);
        } catch (Exception e) {
            code=RetCode.INTERNAL_SERVER_ERROR;
            msg="异常";
            e.printStackTrace();
        } finally {
            logger.info("end............");
        }
        return RetResponse.makeRsp(code,msg,data);
    }
    @ApiOperation(value = "返回一条数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "uuid",value = "uuid",paramType="query")
    })
    @RequestMapping(value ="/findOne")
    public RetResult show (@ApiIgnore @RequestParam Map map){
        logger.info("begin............");
		PageData pd= new PageData(map);
        PageData data= null;
        RetCode code= RetCode.SUCCESS;
        String msg="成功";
        try {
            data= ${typeNameLower}Service.findByUuid(pd);
        } catch (Exception e) {
            code=RetCode.INTERNAL_SERVER_ERROR;
            msg="异常";
            e.printStackTrace();
        } finally {
            logger.info("end............");
        }
        return RetResponse.makeRsp(code,msg,data);
    }
    @ApiOperation(value = "添加数据")
    @ApiImplicitParams({
    <#list fieldList as var>
        <#if var[0] != 'id' && var[0] != 'uuid' && var[0] != 'create_user' && var[0] != 'create_time' && var[0] != 'update_user' && var[0] != 'update_time'>
        @ApiImplicitParam(name = "${var[1]}",value = "${var[2]}",paramType="query")<#if var_has_next>,</#if>
        </#if>
    </#list>
    })
    @RequestMapping(value ="/add")
    public RetResult add(@ApiIgnore @RequestParam Map map,@ApiIgnore  HttpSession session){
        logger.info("begin............");
        PrincipalCollection attribute = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        Map user = (Map) attribute.getPrimaryPrincipal();
		PageData pd= new PageData(map);
		int data= 0;
        RetCode code= RetCode.SUCCESS;
        String msg="成功";
        try {
            pd.put("uuid",UUIDUtils.getUUID());

            pd.put("createTime", DateUtil.getTime());
            pd.put("createUser", user.get("username").toString());
            pd.put("updateTime", DateUtil.getTime());
            pd.put("updateUser", user.get("username").toString());
            pd.put("state",0);
            data= ${typeNameLower}Service.add(pd);
        } catch (Exception e) {
            code=RetCode.INTERNAL_SERVER_ERROR;
            msg="异常";
            e.printStackTrace();
        } finally {
            logger.info("end............");
        }
        return RetResponse.makeRsp(code,msg,data);

    }
    @ApiOperation(value = "编辑数据")
    @ApiImplicitParams({
    <#list fieldList as var>
        <#if var[0] != 'id' && var[0] != 'uuid' && var[0] != 'create_user' && var[0] != 'create_time' && var[0] != 'update_user' && var[0] != 'update_time'>
        @ApiImplicitParam(name = "${var[1]}",value = "${var[2]}",paramType="query")<#if var_has_next>,</#if>
        </#if>
    </#list>
    })
    @RequestMapping(value ="/edit")
    public RetResult edit(@ApiIgnore @RequestParam Map map,@ApiIgnore  HttpSession session){
        logger.info("begin............");
        PrincipalCollection attribute = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        Map user = (Map) attribute.getPrimaryPrincipal();
		PageData pd= new PageData(map);
		int data = 0;
        RetCode code= RetCode.SUCCESS;
        String msg="成功";
        try {
            pd.put("updateTime", DateUtil.getTime());
            pd.put("updateUser", user.get("username").toString());
            data= ${typeNameLower}Service.edit(pd);
        } catch (Exception e) {
            code=RetCode.INTERNAL_SERVER_ERROR;
            msg="异常";
            e.printStackTrace();
        } finally {
            logger.info("end............");
        }
        return RetResponse.makeRsp(code,msg,data);

    }

    @ApiOperation(value = "删除数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "uuid",value = "uuid",paramType="query")
    })
    @RequestMapping(value ="/delete")
    public RetResult delete(@ApiIgnore @RequestParam Map map,@ApiIgnore HttpSession session){
        logger.info("begin............");
        PrincipalCollection attribute = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        Map user = (Map) attribute.getPrimaryPrincipal();
		PageData pd= new PageData(map);
		int data =0 ;
        RetCode code= RetCode.SUCCESS;
        String msg="成功";
        try {
            pd.put("updateTime", DateUtil.getTime());
            pd.put("updateUser", user.get("username").toString());
            data = ${typeNameLower}Service.delete(pd);
        } catch (Exception e) {
            code=RetCode.INTERNAL_SERVER_ERROR;
            msg="异常";
            e.printStackTrace();
        } finally {
            logger.info("end............");
        }
        return RetResponse.makeRsp(code,msg,data);

    }

}
