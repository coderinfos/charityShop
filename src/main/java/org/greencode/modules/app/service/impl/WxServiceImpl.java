package org.greencode.modules.app.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.greencode.common.utils.R;
import org.greencode.modules.app.entity.wx.MpTemplateMsgVo;
import org.greencode.modules.app.entity.wx.TemplateData;
import org.greencode.modules.app.entity.wx.TouserVo;
import org.greencode.modules.app.service.UserService;
import org.greencode.modules.app.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.greencode.common.constant.ClientConstants.PARAM_ERROR_CODE;
import static org.greencode.common.constant.ClientConstants.PARAM_ERROR_MSG;
import static org.greencode.common.constant.WeChatConstants.*;
import static org.greencode.common.constant.WeChatConstants.TEMPLATE;


@Service
@Slf4j
public class WxServiceImpl implements WxService{
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public R WxPushNotification(String openid, Integer type,Integer price) {
        RestTemplate restTemplate = new RestTemplate();
        if (openid == null||type==null) {
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        //从redis中拿到小程序唯一凭证token
        String ACCESS_TOKEN = (String) redisTemplate.opsForValue().get("access_token");
        if(ACCESS_TOKEN==null){
            //如果redis中没有，那么重新获取
            ACCESS_TOKEN = getAccessToken();
        }
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+ACCESS_TOKEN;
        TouserVo touserVo = new TouserVo();
        //用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        touserVo.setTouser(openid);
        //传递公众号信息
        MpTemplateMsgVo mpTemplateMsgVo = new MpTemplateMsgVo();
        //公众号appid，要求与小程序有绑定且同主体
        mpTemplateMsgVo.setAppid(GZAppID);
        Map<String, TemplateData> data = new HashMap<>(4);
        if(type==1){
            //公众号模板id
            mpTemplateMsgVo.setTemplate_id(TEMPLATE);
            data.put("first", new TemplateData("尊敬的捐赠人，您捐赠给慈善商店的爱心物品刚刚出售啦！"));
            data.put("keyword1", new TemplateData("慈善商店"));
            data.put("keyword2", new TemplateData("感谢您捐赠的物品，为精准捐衣项目带来了"+price+"元的捐款，每25元的捐款可以帮助我们将一件8成新以上合适干净的四季衣送到需要的人手中，传递一份善意。么么哒!"));
            data.put("remark", new TemplateData(""));
        }else if(type==2){
            //公众号模板id
            mpTemplateMsgVo.setTemplate_id("0isjeghiZ4nRnqKSt5E9VWdxAIbFwVeVFah2S7GRr1E");
            data.put("first", new TemplateData("尊敬的捐赠人，我们的慈善商店尚未收到您两周前提交的爱心物品捐赠。如果您尚未发货，请您尽快发货哦！（如果您已发货，请忽略这条消息）如有问题请联络"));
            data.put("keyword1", new TemplateData("宝贝爱蓝天小助手。微信号：bbaltxzs"));
            data.put("keyword2", new TemplateData("15021414581"));
            data.put("remark", new TemplateData(""));
        }else if(type==3){
            //公众号模板id
            mpTemplateMsgVo.setTemplate_id(TEMPLATE);
            data.put("first", new TemplateData(""));
            data.put("keyword1", new TemplateData("慈善商店"));
            data.put("keyword2", new TemplateData("尊敬的志愿者店长，您已报名明天的慈善商店店长值守，请按照当班时间提前>5分钟到达慈善商店。到达慈善商店后请先阅读我们为您准备的店长手册。感谢您的志愿服务，期待您在慈善商店志愿值守中付出善意、发挥特长、收获美好。"));
            data.put("remark", new TemplateData(""));
        }

        mpTemplateMsgVo.setData(data);
        touserVo.setMp_template_msg(mpTemplateMsgVo);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(requestUrl, touserVo, String.class);
        log.info("jsonObject:{}", responseEntity);
        String[] split = responseEntity.getBody().split(",");
        String[] code = split[0].split(":");
        String[] msg = split[1].split(":");

        if (Integer.parseInt(code[1] )!= 0) {
            log.info("request is error");

            return R.error(Integer.parseInt(code[1]) ,msg[1]);
        }
        //发送 成功
        return R.ok();
    }

    @Override
    public String getAccessToken() {
        //获取公众号接口调用凭证
        String APPID=APP_ID;
        String APPSECRET=APP_SECRET;
        String access_tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                +APPID+"&secret="+APPSECRET;
        cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.get(access_tokenUrl));
        if (jsonObject.get(ERR_CODE) != null) {
            log.info("error code"+Integer.parseInt(jsonObject.get(ERR_CODE).toString())+"error msg"+jsonObject.get("errmsg").toString());
            return null;
        }
        //有效期根据expires_in，一般是2小时
        String access_token=jsonObject.get("access_token").toString();
        Integer expires_in= Integer.parseInt(jsonObject.get("expires_in").toString());
        redisTemplate.opsForValue().set("access_token",access_token);
        redisTemplate.expire("access_token", expires_in, TimeUnit.SECONDS);
        return access_token;
    }
}
