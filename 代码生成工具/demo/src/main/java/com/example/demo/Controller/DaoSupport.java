package com.example.demo.Controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;


import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

@Repository("daoSupport")
public class DaoSupport {

	@Value("${spring.datasource.url}")
	private String url; 
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;	
	@Value("${spring.datasource.username}")
	private String username;	
	@Value("${spring.datasource.password}")
	private String password;




	/**
	 * 查找数据库表的元数据
	 * @param tableName
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List findTableMetaData(String tableName) throws Exception {
		System.out.println(ResourceUtils.CLASSPATH_URL_PREFIX+"------------------------");
		ResultSet rs=null;
		ResultSet rsMeta=null;
		Connection conn =null;
		List<List> list =new ArrayList<>();
        try {
            Class.forName(this.driverClassName);
            conn = DriverManager.getConnection(this.url, this.username, this.password);
            //取得列备注
            HashMap commentMap=new HashMap();
            DatabaseMetaData dbmd = conn.getMetaData();


			rsMeta=dbmd.getColumns(null, "%", tableName, "%");
    		if(rsMeta!=null){
    			while(rsMeta.next()){
    				commentMap.put(rsMeta.getString("COLUMN_NAME"), rsMeta.getString("REMARKS"));
    			}
    		}
    		//取得列名和JDBC列类型
    		String columnComment=null;
			String columnName=null;
            String sql = "select * from " + tableName;
            rs = conn.createStatement().executeQuery(sql);
            ResultSetMetaData rsData = rs.getMetaData();

            for (int i = 1; i <= rsData.getColumnCount(); i++) {
				columnName=rsData.getColumnName(i);
            	columnComment=(String)commentMap.get(columnName);

            	if(columnComment==null){
					columnComment=columnName;
				}
				String [] temp=columnName.split("_");;
				String str="";
				if (temp.length>1){
					for (int j=0;j<temp.length;j++){
						String name=temp[j];
						if(j!=0) {
							name = name.substring(0, 1).toUpperCase() + name.substring(1);
						}
						str=str+name;
					}
				}else{
					str=columnName;
				}

				List<String> list2 =new ArrayList<>();
				list2.add(rsData.getColumnName(i));//数据库表字段列名
				list2.add(str);				//字段驼峰命名

				list2.add(columnComment);  // 注释

				System.out.println(rsData.getColumnTypeName(i));
				System.out.println(rsData.getColumnClassName(i));
				list2.add(rsData.getColumnTypeName(i));//数据库字段类型
				list2.add(rsData.getColumnClassName(i));// java 数据类型
				//list2.add(rsData.getColumnType(i));

				list.add(list2);


                //System.out.println("==列的信息:获取SQL语句的列名:"+rsData.getColumnName(i)+"("+rsData.getColumnLabel(i)+","+rsData.getColumnType(i)+","+rsData.getColumnClassName(i)+")"
                //        +" 列宽"+rsData.getPrecision(i)+" 大小写敏感"+rsData.isCaseSensitive(i)+" isReadOnly:"+rsData.isReadOnly(i)); 
                //==列的信息:获取SQL语句的列名:LIMITLEVER(LIMITLEVER,5,java.lang.Short) 列宽5 大小写敏感true isReadOnly:false 
            } 
            
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {
        	try {
        		rs.close();
        		rsMeta.close();
        		conn.close();
        		rs=null;
        		rsMeta=null;
        		conn=null;
        	}catch(Exception e) {
        		
        	}
        }
		return list;
	}

}


