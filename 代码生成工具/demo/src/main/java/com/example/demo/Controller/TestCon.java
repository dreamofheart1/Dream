package com.example.demo.Controller;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/c")
public class TestCon {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private  DaoSupport daoSupport;
    @RequestMapping("/code")
    public Object code(@RequestParam Map map, HttpServletResponse response) throws Exception {

        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");


        String tableName = (String) map.get("tableName");
        String packageName = (String) map.get("packageName");
        String typeName = (String) map.get("typeName");
        String authorName = (String) map.get("authorName");

        System.out.println("========================");
        List list= daoSupport.findTableMetaData(tableName);
        System.out.println(list);


        Map<String,Object> root = new HashMap<String,Object>();		// 创建数据模型
        root.put("fieldList", list);
        root.put("tableName", tableName);                             //表名
        root.put("packageName", packageName);						// 包名
        root.put("typeName", typeName);							// 类名
        root.put("typeNameLower", typeName.toLowerCase());		// 类名(全小写)
        root.put("typeNameUpper", typeName.toUpperCase());		// 类名(全大写)
        root.put("nowDate", new Date());							// 当前日期
        root.put("authorName", authorName);	                            // 创建人
/*        root.put("currentResult",0);
        root.put("limit",10);*/
        // 创建人
        // 生成代码前,先清空之前生成的代码
        DelAllFile.delFolder(PathUtil.getClasspath()+"uploadFiles/code");

        // 存放路径
        String filePath = "uploadFiles/code/";

        // ftl路径
        String ftlPath = "createCode";

        // 生成doc
       Freemarker.printFile("APIdoc.ftl", root, "doc/"+typeName+"API.txt", filePath, ftlPath);
        // 生成controller
        Freemarker.printFile("controllerTemplate.ftl", root, "controller/"+packageName+"/"+typeName+"Controller.java", filePath, ftlPath);

        // 生成service
        Freemarker.printFile("serviceTemplate.ftl", root, "service/"+packageName+"/"+typeName+"Service.java", filePath, ftlPath);

        // 生成dao
        Freemarker.printFile("daoTemplate.ftl", root, "dao/"+packageName+"/"+typeName+"Dao.java", filePath, ftlPath);
        // 生成mybatis.xml
        Freemarker.printFile("mapperMysqlTemplate.ftl", root, "mybatis_mysql/"+typeName+"Mapper.xml", filePath, ftlPath);


        // 生成jsp页面
        /*Freemarker.printFile("jsp_list_Template.ftl", root, "jsp/"+packageName+"/"+typeName.toLowerCase()+"/"+typeName.toLowerCase()+"_list.jsp", filePath, ftlPath);
        Freemarker.printFile("jsp_edit_Template.ftl", root, "jsp/"+packageName+"/"+typeName.toLowerCase()+"/"+typeName.toLowerCase()+"_edit.jsp", filePath, ftlPath);
        Freemarker.printFile("jsp_view_Template.ftl", root, "jsp/"+packageName+"/"+typeName.toLowerCase()+"/"+typeName.toLowerCase()+"_view.jsp", filePath, ftlPath);
*/
        // 生成说明文档
       // Freemarker.printFile("docTemplate.ftl", root, "说明.doc", filePath, ftlPath);

        // 生成的全部代码压缩成zip文件
        FileZip.zip(PathUtil.getClasspath()+"uploadFiles/code", PathUtil.getClasspath()+"uploadFiles/code.zip");

        // 下载代码
        FileDownload.fileDownload(response, PathUtil.getClasspath()+"uploadFiles/code.zip", "code.zip");
        return null;
    }
}
