package love.simbot.example.listener;

import com.mchange.v2.lang.StringUtils;

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
            sendMsg="";
        }
        return sendMsg;
    }
}
