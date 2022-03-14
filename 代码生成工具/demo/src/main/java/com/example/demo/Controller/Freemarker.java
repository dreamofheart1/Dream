package com.example.demo.Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;


public class Freemarker {

	/**
	 * 打印到控制台(测试用)
	 *  @param ftlName
	 */
	public static void print(String ftlName, Map<String,Object> root, String ftlPath) throws Exception{
		try {
			Template temp = getTemplate(ftlName, ftlPath);		//通过Template可以将模板文件输出到相应的流
			temp.process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出到输出到文件
	 * @param ftlName   ftl文件名
	 * @param root		传入的map
	 * @param outFile	输出后的文件全部路径
	 * @param filePath	输出前的文件上部路径
	 */
	public static void printFile(String ftlName, Map<String,Object> root, String outFile, String filePath, String ftlPath) throws Exception{
		try {
			File file = new File(PathUtil.getClasspath() + filePath + outFile);
			if(!file.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
				file.getParentFile().mkdirs();				//不存在就全部创建
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			Template template = getTemplate(ftlName, ftlPath);
			template.process(root, out);					//模版输出
			out.flush();
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过文件名加载模版
	 * @param ftlName
	 */
	public static Template getTemplate(String ftlName, String ftlPath) throws Exception{
		try {
			//@Autowired
			//ResourceLoader resourceLoader ;

			/*Resource resource = resourceLoader.getResource("classpath:resource.properties");
			InputStream is = resource.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String data = null;
			while((data = br.readLine()) != null) {
				System.out.println(data);
			}

			br.close();
			isr.close();
			is.close();*/
			// 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是FreeMarker对于的版本号。
			Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			configuration.setEncoding(Locale.CHINA, "utf-8");
			File file=ResourceUtils.getFile((ResourceUtils.CLASSPATH_URL_PREFIX+"ftl/"+ftlPath));
			configuration.setDirectoryForTemplateLoading(ResourceUtils.getFile((ResourceUtils.CLASSPATH_URL_PREFIX+"ftl/"+ftlPath)));		//设定去哪里读取相应的ftl模板文件
			Template template = configuration.getTemplate(ftlName);												//在模板文件目录中找到名称为name的文件
			return template;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
