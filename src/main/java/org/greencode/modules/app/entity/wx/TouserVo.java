package org.greencode.modules.app.entity.wx;


import lombok.Data;



@Data
public class TouserVo {
    //用户openid，可以是小程序的openid，也可以是mp_template_msg.appid对应的公众号的openid
    private String touser;
    //小程序模板消息相关的信息，可以参考小程序模板消息接口; 有此节点则优先发送小程序模板消息
    private WeappTemplateMsgVo weapp_template_msg;
    //公众号模板消息相关的信息，可以参考公众号模板消息接口；有此节点并且没有weapp_template_msg节点时，发送公众号模板消息
    private MpTemplateMsgVo mp_template_msg;

}
