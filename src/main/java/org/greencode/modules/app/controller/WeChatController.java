package org.greencode.modules.app.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.greencode.common.utils.IPUtils;
import org.greencode.common.utils.R;
import org.greencode.common.utils.UrlUtil;
import org.greencode.modules.app.dao.UserDao;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.entity.wx.MpTemplateMsgVo;
import org.greencode.modules.app.entity.wx.TemplateData;
import org.greencode.modules.app.entity.wx.TouserVo;
import org.greencode.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.greencode.common.constant.WeChatConstants.*;
import static org.greencode.common.constant.ClientConstants.*;

/**
 *
 */
@RestController
@RequestMapping("/app/wechat/")
@Api(tags = "微信接口")
@Slf4j
public class WeChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 传入code来查询openId是否存在库中，走登录还是走注册
     * @param code
     * @return
     */
    @GetMapping("getOpenId")
    @ApiOperation("传入code,如果用户已经注册,返回用户信息,如果没有返回openid")
    public JSONObject getOpenIdAndSessionKey(String code,HttpServletRequest request) {
        //获取ip
        String ipAddr = IPUtils.getIpAddr(request);
        System.out.println("code-------------"+code);
        log.info("code:{}", code);
        if (code == null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", PARAM_ERROR_CODE);
            jsonObject.put("msg", PARAM_ERROR_MSG);
            return jsonObject;
        }
//        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> params = new HashMap<>(MAP_INIT_NUM);
        params.put("appid", APP_ID);
        params.put("secret", APP_SECRET);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        JSONObject jsonObject = JSON.parseObject(UrlUtil.sendPost(requestUrl, params));
        log.info("jsonObject:{}", jsonObject);
        if (jsonObject.get(ERR_CODE) != null) {
            log.info("request is error");
            return jsonObject;
        }
        //获取access_token 成功
        String openId = (String) jsonObject.get("openid");
        log.info("openId:{}", openId);
        UserEntity userEntity = userService.getByWechatId(openId);
        JSONObject jsonObject1 = new JSONObject();
        if (userEntity == null) {

            log.info("First Login");
            jsonObject1.put("type_code", 0);
            jsonObject1.put("type_msg", "first login");
            UserEntity user = new UserEntity();
            user.setWechatId(openId);
            //第一次登录，生成当前时间并存入
            user.setPreLoginTime(new Date());
            user.setPreLoginIp(ipAddr);
            user.setLastLoginIp(ipAddr);
            user.setLastLoginTime(new Date());
            userService.save(user);
            UserEntity userEntity1 = userDao.selectById(user.getId());
            jsonObject1.put("data", userEntity1);
            return jsonObject1;
        }
        log.info("not first login");
        jsonObject1.put("type_code", 1);
        jsonObject1.put("type_msg", "not first login");
        jsonObject1.put("data", userEntity);
        userEntity.setPreLoginTime(userEntity.getLastLoginTime());
        userEntity.setPreLoginIp(userEntity.getLastLoginIp());
        userEntity.setLastLoginTime(new Date());
        userEntity.setLastLoginIp(ipAddr);
        userService.updateById(userEntity);
        return jsonObject1;
    }


    public R common(boolean code){
        if(code){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
