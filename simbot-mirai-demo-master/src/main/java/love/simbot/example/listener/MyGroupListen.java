package love.simbot.example.listener;

import catcode.CatCodeUtil;
import catcode.Neko;
import love.forte.common.ioc.annotation.Beans;
import love.forte.common.ioc.annotation.Depend;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.containers.GroupAccountInfo;
import love.forte.simbot.api.message.containers.GroupInfo;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 群消息监听的示例类。
 * 所有需要被管理的类都需要标注 {@link Beans} 注解。
 * @author ForteScarlet
 */
@Beans
public class MyGroupListen {

    /** log */
    private static final Logger LOG = LoggerFactory.getLogger(MyGroupListen.class);

    @Depend
    private MessageContentBuilderFactory messageContentBuilderFactory;

    SetSendMsg setSendMsg=new SetSendMsg();
    /**
     * 此监听函数代表，收到消息的时候，将消息的各种信息打印出来。
     *
     * 此处使用的是模板注解 {@link OnGroup}, 其代表监听一个群消息。
     *
     * 由于你监听的是一个群消息，因此你可以通过 {@link GroupMsg} 作为参数来接收群消息内容。
     *
     * <p>
     * 注意！ 假如你发现你群消息发不出去（或者只有一些很短的消息能发出去）且没有任何报错，
     * 但是尝试后，发现 <b>私聊</b> 一切正常，能够发送，那么这是 <b>正常现象</b>！
     *
     * 参考：
     *
     */
//    @OnGroup
//    public void onGroupMsg(GroupMsg groupMsg, Sender sender) {
//        // 打印此次消息中的 纯文本消息内容。
//        // 纯文本消息中，不会包含任何特殊消息（例如图片、表情等）。
//        System.out.println(groupMsg.getText());
//
//        // 打印此次消息中的 消息内容。
//        // 消息内容会包含所有的消息内容，也包括特殊消息。特殊消息使用CAT码进行表示。
//        // 需要注意的是，绝大多数情况下，getMsg() 的效率低于甚至远低于 getText()
//        System.out.println(groupMsg.getMsg());
//
//        // 获取此次消息中的 消息主体。
//        // messageContent代表消息主体，其中通过可以获得 msg, 以及特殊消息列表。
//        // 特殊消息列表为 List<Neko>, 其中，Neko是CAT码的封装类型。
//
//        MessageContent msgContent = groupMsg.getMsgContent();
//
//        // 打印消息主体
//        System.out.println(msgContent);
//        // 打印消息主体中的所有图片的链接（如果有的话）
//        List<Neko> imageCats = msgContent.getCats("image");
//        System.out.println("img counts: " + imageCats.size());
//        for (Neko image : imageCats) {
//            System.out.println("Img url: " + image.get("url"));
//        }
//
//
//        // 获取发消息的人。
//        GroupAccountInfo accountInfo = groupMsg.getAccountInfo();
//        // 打印发消息者的账号与昵称。
//        System.out.println(accountInfo.getAccountCode());
//        System.out.println(accountInfo.getAccountNickname());
//
//
//        // 获取群信息
//        GroupInfo groupInfo = groupMsg.getGroupInfo();
//        // 打印群号与名称
//        System.out.println(groupInfo.getGroupCode());
//        System.out.println(groupInfo.getGroupName());
//
//
//        //-------------------------
//
//
//
//        // 向 privateMsg 的账号发送消息，消息为当前接收到的消息。
//
//        sender.sendGroupMsg(groupMsg, msgContent);
//
//        // 再发送一个表情ID为'9'的表情。
//        // 方法1：使用消息构建器构建消息并发送
//        // 在绝大多数情况下，使用消息构建器所构建的消息正文 'MessageContent'
//        // 是用来发送消息最高效的选择。
//        // 相对的，MessageContentBuilder所提供的构建方法是十分有限的。
//
//        // 获取消息构建器
//        MessageContentBuilder msgBuilder = messageContentBuilderFactory.getMessageContentBuilder();
//        // 通过.text(...) 向builder中追加一句话。
//        // 通过.face(ID) 向builder中追加一个表情。
//        // 通过.build() 构建出最终消息。
//        MessageContent msg = msgBuilder.text("表情：").face(9).build();
//
//        // 直接通过这个msg发送。
//        sender.sendGroupMsg(groupMsg, msg);
//
//        // 方法2：使用CAT码发送消息。
//        // 使用CAT码构建一个需要解析的消息是最灵活的，
//        // 但是相对的，它的效率并不是十分的可观，毕竟在这其中可能会涉及到很多的'解析'操作。
//
//        // 获取CAT码工具类实例
//        CatCodeUtil catCodeUtil = CatCodeUtil.getInstance();
//
//        // 构建一个类型为 'face', 参数为 'id=9' 的CAT码。
//        // 有很多方法。
//
//        // 1. 通过 codeBuilder 构建CAT码
//        // String cat1 = catCodeUtil.getStringCodeBuilder("face", false).key("id").value(9).build();
//
//        // 2. 通过CatCodeUtil.toCat 构建CAT码
//        // String cat2 = catCodeUtil.toCat("face", "id=9");
//
//        // 3. 通过模板构建CAT码
//        String cat3 = catCodeUtil.getStringTemplate().face(9);
//
//        // 在cat码前增加一句 '表情' 并发送
//        sender.sendGroupMsg(groupMsg, "表情：" + cat3);
//
//    }



    @OnGroup
    public void onGroupMsg(GroupMsg groupMsg, Sender sender) {
        // 打印此次消息中的 纯文本消息内容。
        // 纯文本消息中，不会包含任何特殊消息（例如图片、表情等）。
        System.out.println(groupMsg.getText());

        // 打印此次消息中的 消息内容。
        // 消息内容会包含所有的消息内容，也包括特殊消息。特殊消息使用CAT码进行表示。
        // 需要注意的是，绝大多数情况下，getMsg() 的效率低于甚至远低于 getText()
        System.out.println(groupMsg.getMsg());

        // 获取此次消息中的 消息主体。
        // messageContent代表消息主体，其中通过可以获得 msg, 以及特殊消息列表。
        // 特殊消息列表为 List<Neko>, 其中，Neko是CAT码的封装类型。

        MessageContent msgContent = groupMsg.getMsgContent();

        // 打印消息主体
        System.out.println(msgContent);
        // 打印消息主体中的所有图片的链接（如果有的话）
        List<Neko> imageCats = msgContent.getCats("image");
        System.out.println("img counts: " + imageCats.size());
        for (Neko image : imageCats) {
            System.out.println("Img url: " + image.get("url"));
        }


        // 获取发消息的人。
        GroupAccountInfo accountInfo = groupMsg.getAccountInfo();
        // 打印发消息者的账号与昵称。
        System.out.println(accountInfo.getAccountCode());
        System.out.println(accountInfo.getAccountNickname());


        // 获取群信息
        GroupInfo groupInfo = groupMsg.getGroupInfo();
        // 打印群号与名称
        System.out.println(groupInfo.getGroupCode());
        System.out.println(groupInfo.getGroupName());


        //-------------------------


        MessageContentBuilder msgBuilder = messageContentBuilderFactory.getMessageContentBuilder();
        // 向 privateMsg 的账号发送消息，消息为当前接收到的消息。

        String str = setSendMsg.SendMsg(msgContent.getMsg());
        if(!"".equals(str)){
            sender.sendGroupMsg(groupMsg, msgBuilder.text(str).build());
        }



    }




}
