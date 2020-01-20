package org.greencode.modules.app.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.greencode.common.utils.IPUtils;
import org.greencode.common.utils.R;
import org.greencode.common.utils.UrlUtil;
import org.greencode.modules.app.entity.AdminUserEntity;
import org.greencode.modules.app.entity.ShopEntity;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * 传入code来查询openId是否存在库中，走登录还是走注册
     * @param code
     * @return
     */
    @PostMapping("getOpenId")
    @ApiOperation("传入code,如果用户已经注册,返回用户信息,如果没有返回openid")
    public JSONObject getOpenIdAndSessionKey(@RequestBody String code) {
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
        if ((Integer)jsonObject.get(ERR_CODE) != 0) {
            log.info("request is error");
            return jsonObject;
        }
        //获取access_token 成功
        String openId = (String) jsonObject.get("openid");
        log.info("openId:{}", openId);
        UserEntity userEntity = userService.getByWechatId(openId);

        if (userEntity == null) {
            log.info("First Login");
            jsonObject.put("type_code", 0);
            jsonObject.put("type_msg", "first login");
            jsonObject.put("data", openId);
            return jsonObject;
        }
        log.info("not first login");
        jsonObject.put("type_code", 1);
        jsonObject.put("type_msg", "not first login");
        jsonObject.put("data", userEntity);
        return jsonObject;
    }

    /**
     * 第三方（微信授权登入）,因为要获取ip，所以要传request,带openId
     * @param request
     * @return
     */
    @PostMapping("login")
    @ApiOperation("第三方（微信授权登入）传入openId")
    public R login(@RequestBody Map<String, Object> map,HttpServletRequest request)  {
        String openId = map.get("openId").toString();
        //获取ip
        String ipAddr = IPUtils.getIpAddr(request);
        log.info("openId:{}", openId);
        if (openId==null) {
            log.info("param is null");
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        } else {
            log.info("param is not null");
            UserEntity userEntity = userService.getByWechatId(openId);
            if(userEntity==null){
                return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
            }
            //登录的时候修改上次登录的时间和最后登录的时间
            if (userEntity.getPreLoginTime()==null) {
                //第一次登录，生成当前时间并存入
                userEntity.setPreLoginTime(new Date());
                userEntity.setPreLoginIp(ipAddr);
                userEntity.setLastLoginIp(ipAddr);
                userEntity.setLastLoginTime(new Date());
            }else {
                userEntity.setPreLoginTime(userEntity.getLastLoginTime());
                userEntity.setPreLoginIp(userEntity.getLastLoginIp());
                userEntity.setLastLoginTime(new Date());
                userEntity.setLastLoginIp(ipAddr);
            }
            userService.updateById(userEntity);
            //如果要传token那么这里应该创建一个token
//            Map<String, Object> resMap = new HashMap<>(MAP_INIT_NUM);
            //resMap.put("token", token);
//            resMap.put("id", userEntity.getId());
            return R.ok().put("data",userEntity);
        }

    }


    @GetMapping("register")
    @ApiOperation("传入openId,nickName,face,sex,mobilePhone保存到数据库")
    public R register(@RequestBody JSONObject jsonObject) throws ParseException {




//        String openid = jsonObject.get("openid").toString();
//        String access_token = jsonObject.get("access_token").toString();
//        String getUserURL = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
//       String getUserURL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ access_token +"&openid="+openid+"&lang=zh_CN" ;
//        cn.hutool.json.JSONObject user = JSONUtil.parseObj(HttpUtil.get(getUserURL));
        UserEntity userEntity = new UserEntity();
        userEntity.setWechatId(jsonObject.get("openId").toString());
        userEntity.setNickName(jsonObject.get("nickName").toString());
        userEntity.setFace(jsonObject.get("face").toString());
        userEntity.setSex(((Integer) jsonObject.get("sex")==1)?1:0);
        userEntity.setMobilePhone((Long) jsonObject.get("mobilePhone"));
        boolean code = userService.save(userEntity);
        return common(code);
    }


    public R common(boolean code){
        if(code){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
