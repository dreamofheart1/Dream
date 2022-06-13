package jsoup;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsoupFirstTest {

    @Test
    public void testUrl() throws Exception {
        //解析url地址,第一个参数是访问的url，第二个参数是访问时候的超时时间
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);

        //使用标签选择器，获取title标签中的内容
        String title = doc.getElementsByTag("title").first().text();

        //打印
        System.out.println(title);
    }

    @Test
    public void testString() throws Exception {
        //使用工具类读取文件，获取字符串
        String content = FileUtils.readFileToString(new File("C:\\Users\\tree\\Desktop\\test.html"), "utf8");

        //解析字符串
        Document doc = Jsoup.parse(content);

        String title = doc.getElementsByTag("title").first().text();

        System.out.println(title);

    }

    @Test
    public void testFile() throws Exception {
        //解析文件
        Document doc = Jsoup.parse(new File("C:\\Users\\tree\\Desktop\\test.html"), "utf8");

        String title = doc.getElementsByTag("title").first().text();

        System.out.println(title);

    }

    @Test
    public void testDOM() throws Exception {
        //解析文件，获取Document对象
        Document doc = Jsoup.parse(new File("C:\\Users\\tree\\Desktop\\test.html"), "utf8");


        //获取元素
        //1.	根据id查询元素getElementById
        //Element element = doc.getElementById("city_bj");

        //2.	根据标签获取元素getElementsByTag
        //Element element = doc.getElementsByTag("span").first();

        //3.	根据class获取元素getElementsByClass
        //Element element = doc.getElementsByClass("class_a class_b").first();
        //Element element = doc.getElementsByClass("class_a").first();
        //Element element = doc.getElementsByClass("class_b").first();


        //4.	根据属性获取元素getElementsByAttribute
        //Element element = doc.getElementsByAttribute("abc").first();
        Element element = doc.getElementsByAttributeValue("href", "http://sh.itcast.cn").first();

        //打印元素的内容
        System.out.println("获取到的元素内容是：" + element.text());

    }


    @Test
    public void testData() throws Exception {
        //解析文件，获取Document
        Document doc = Jsoup.parse(new File("C:\\Users\\tree\\Desktop\\test.html"), "utf8");

        //根据id获取元素
        Element element = doc.getElementById("test");

        String str = "";

        //元素中获取数据
        //1.	从元素中获取id
        str = element.id();

        //2.	从元素中获取className
        str = element.className();
        //Set<String> classSet = element.classNames();
        //for (String s : classSet ) {
        //    System.out.println(s);
        //}

        //3.	从元素中获取属性的值attr
        //str = element.attr("id");
        str = element.attr("class");

        //4.	从元素中获取所有属性attributes
        Attributes attributes = element.attributes();
        System.out.println(attributes.toString());

        //5.	从元素中获取文本内容text
        str = element.text();

        //打印获取到的内容
        System.out.println("获取到的数据是：" + str);

    }

    @Test
    public void testSelector() throws Exception {

        //解析html文件，获取Document对象
        Document doc = Jsoup.parse(new File("C:\\Users\\tree\\Desktop\\test.html"), "utf8");

        //tagname: 通过标签查找元素，比如：span
        Elements elements = doc.select("span");
        //for (Element element : elements) {
        //    System.out.println(element.text());
        //}

        //#id: 通过ID查找元素，比如：#city_bj
        //Element element = doc.select("#city_bj").first();

        //.class: 通过class名称查找元素，比如：.class_a
        //Element element = doc.select(".class_a").first();

        //[attribute]: 利用属性查找元素，比如：[abc]
        Element element = doc.select("[abc]").first();

        //[attr=value]: 利用属性值来查找元素，比如：[class=s_name]
        Elements elements1 = doc.select("[class=s_name]");
        for (Element element1 : elements1) {
            System.out.println(element1.text());
        }


        //打印结果
        System.out.println("获取到的结果是：" + element.text());
    }

    @Test
    public void testSelector2()throws Exception{
        //解析html文件，获取Document对象
        Document doc = Jsoup.parse(new File("C:\\Users\\tree\\Desktop\\test.html"), "utf8");

        //el#id: 元素+ID，比如： h3#city_bj
        Element element = doc.select("h3#city_bj").first();

        //el.class: 元素+class，比如： li.class_a
        element = doc.select("li.class_a").first();

        //el[attr]: 元素+属性名，比如： span[abc]
        element = doc.select("span[abc]").first();

        //任意组合: 比如：span[abc].s_name
        element = doc.select("span[abc].s_name").first();

        //ancestor child: 查找某个元素下子元素，比如：.city_con li 查找"city_con"下的所有li
        Elements elements = doc.select(".city_con li");

        //parent > child: 查找某个父元素下的直接子元素，比如：
        //.city_con > ul > li 查找city_con第一级（直接子元素）的ul，再找所有ul下的第一级li
        elements = doc.select(".city_con > ul > li");

        //parent > *: 查找某个父元素下所有直接子元素
        elements = doc.select(".city_con > ul > *");



        System.out.println("获取到的内容是："+element.text());

        for (Element element1 : elements) {
            System.out.println("遍历的结果："+element1.text());
        }
    }

    @Test
    public void testHttp() throws Exception {
        String realFileName = "18009";
//        for(int i=0;i<=23;i++){
//           String year= StringUtils.leftPad(String.valueOf(i),2,"0");
////            System.out.println(year);
//            for(int j=0;j<=200;j++) {
//                String times = StringUtils.leftPad(String.valueOf(j), 3, "0");
////                System.out.println(times);
//                realFileName=year+times;
//                this.getInfo(realFileName);
//            }
//        }
        this.getInfo(realFileName);
    }
    public void getInfo(String realFileName) throws Exception {

        String baseUrl = "http://kaijiang.500.com/shtml/ssq/";
        String url = baseUrl+realFileName +".shtml";
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建URIBuilder
        URIBuilder uriBuilder = new URIBuilder(url);
        //设置参数
//        uriBuilder.setParameter("keys", "Java");
        URI uri = uriBuilder.build();
        //创建HttpGet对象
        HttpGet httpGet = new HttpGet(uri);
        //执行请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            //获取响应内容
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "gb2312");
//            System.out.println(content);
            Document document = Jsoup.parse(content);
            Elements elements = document.select(".ball_box01 > ul > li.ball_red");
            Elements elements2 = document.select(".ball_box01 > ul > li.ball_blue");
            Elements elements3 = document.select(".td_title01 > span.span_right");

            System.out.print(realFileName+":");
            for (Element element : elements) {
                System.out.print(element.text()+" ");
            }
            for (Element element : elements2) {
                System.out.println("--blue:"+element.text());
            }
            for (Element element : elements3) {
                String text = element.text();
                text=text.substring(text.indexOf("：")+1,text.indexOf("兑")-1);
                String[] arr = text.split("年|月|日");
                System.out.println(text);
                if(arr.length==1){
                    //正则表达式匹配年
                    Pattern pattern = Pattern.compile("\\d{4}");
                    Matcher matcher = pattern.matcher(text);
                    if(matcher.find()){
                        arr[0]=matcher.group();
                        System.out.println("--year:"+matcher.group());
                    }
                    text = matcher.replaceFirst("");
                    System.out.println("--date:"+text);
                    //正则表达式匹配月
                    pattern = Pattern.compile("\\d{1,2}");
                    matcher = pattern.matcher(text);

                    if(matcher.find()){
                        arr[1]=matcher.group();
                        System.out.println("--month:"+matcher.group());
                    }
                    text = matcher.replaceFirst("");
                    System.out.println("--date:"+text);
                    //正则表达式匹配日
                    pattern = Pattern.compile("\\d{1,2}");
                    matcher = pattern.matcher(text);
                    if(matcher.find()){
                        arr[2]=matcher.group();
                        System.out.println("--date:"+matcher.group());
                    }
                }
                arr[1]=StringUtils.leftPad(arr[1],2,"0");
                arr[2]=StringUtils.leftPad(arr[2],2,"0");
                System.out.println("--date:"+arr[0]+"-"+arr[1]+"-"+arr[2]);
                System.out.println("span:" + text);

            }

        }
        //关闭HttpClient
        httpClient.close();

    }

}
