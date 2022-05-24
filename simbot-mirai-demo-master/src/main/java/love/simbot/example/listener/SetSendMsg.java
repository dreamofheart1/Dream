package love.simbot.example.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.mchange.v2.lang.StringUtils;
import kotlinx.serialization.json.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;

public class SetSendMsg {
    private String hasMsg="";
    private  String responseMsg="";
    private String sendMsg="";

    public String SendMsg(String msg){
        String[] strArr = msg.split(" ");
        if(msg!=null&&!"".equals(msg)&&"/设置".equals(strArr[0])){
            hasMsg= strArr[1];
            responseMsg=strArr[2];
            sendMsg="设置成功";
        }else if(!"".equals(hasMsg)&&hasMsg.equals(msg)){
            sendMsg=responseMsg;
        }else if(msg!=null&&!"".equals(msg)&&"/删除".equals(strArr[0])){
            sendMsg="删除成功";
        }else{
            try {
                sendMsg=this.getMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sendMsg;
    }

    public static void main(String[] args) throws IOException {
        SetSendMsg setSendMsg=new SetSendMsg();
        setSendMsg.getMsg("你好");
    }

    public String getMsg(String msg) throws IOException {
        HttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet("http://www.liulongbin.top:3006/api/robot?spoken="+msg);
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            JSONObject jsonObject = JSONObject.parseObject(content);
            String text = jsonObject.getJSONObject("data").getJSONObject("info").getString("text");

            System.out.println(content);
            System.out.println(text);
            return text;
        }else {
            return "我脑袋出问题了！";
        }


    }
}
