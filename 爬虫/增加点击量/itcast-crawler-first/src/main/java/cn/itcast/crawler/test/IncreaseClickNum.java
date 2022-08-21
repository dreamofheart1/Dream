package cn.itcast.crawler.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class IncreaseClickNum {

    public static void main(String[] args) throws Exception {
        List<String> urls = new ArrayList();
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/126282656?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/126239320?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/125132917?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/125117444?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/125117326?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/124705609?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/124590488?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/124543900?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/123825015?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/115981413?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/107951931?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/101295762?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/89186160?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/89155848?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/89154881?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/88634091?spm=1001.2014.3001.5501");
        urls.add("https://blog.csdn.net/dreamofheart1/article/details/88528635?spm=1001.2014.3001.5501");

        while (true) {
            Thread.sleep(60000);
            System.out.println("开始");
            for (String url : urls) {
//                String url = "https://blog.csdn.net/dreamofheart1/article/details/126282656?spm=1001.2014.3001.5501";
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

                    System.out.println("增加点击量成功");
                }
                //关闭HttpClient
                httpClient.close();
            }
        }
    }
}
