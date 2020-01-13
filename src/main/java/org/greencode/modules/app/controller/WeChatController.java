package org.greencode.modules.app.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;

import org.greencode.common.utils.R;
import org.greencode.common.utils.UrlUtil;
import org.greencode.modules.app.entity.AdminUserEntity;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
@RequestMapping("/api/wechat/")
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
    public JSONObject getOpenIdAndSessionKey(String code) {
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

//    /**
//     * 第三方（微信授权登入）
//     * @param openId
//     * @return
//     */
//    @PostMapping("login")
//    public R login(String openId) {
//        log.info("openId:{}", openId);
//        if (StrUtil.isEmpty(openId)) {
//            log.info("param is null");
//            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
//        } else {
//            log.info("param is not null");
//            UserEntity userEntity = userService.getByWechatId(openId);
//            //登录的时候修改上次登录的时间和最后登录的时间
//
//            if (userEntity.getPreLoginTime()==null) {
//
//            }
//            //如果要传token那么这里应该创建一个token
//            Map<String, Object> resMap = new HashMap<>(MAP_INIT_NUM);
//            resMap.put("token", token);
//            resMap.put("teaId", teacher.getTeaId());
//            resMap.put("isAuth", teacher.getIsAuth());
//            return new Response<>(1, "not first login", resMap);
//        }
//
//    }
//
//
//    @GetMapping("getParent")
//    public Response getParent(HttpServletRequest request) {
//        Integer studentId = (Integer) request.getSession().getAttribute("studentId");
//        if (studentId == null) {
//            return new Response<>(COMMON_ERROR_CODE,"学生ID为空") ;
//        }
//        List<ParentDo> list = parentMapper.selectByStudentId(studentId);
//        if (list.size() >= 4) {
//            return new Response<>(COMMON_ERROR_CODE,"绑定数量已达上限")  ;
//        }
//        log.info("回调成功");
//        String code = request.getParameter("code");
//        String appID = "wx5e6bd2d27e9f07d2";
//        String appsecret = "f57eb7bbb4d3d862e630117e2af8460c";
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appID +
//                "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
//        cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(HttpUtil.get(url));
//        String openid = jsonObject.get("openid").toString();
//        ParentDo parentDo = parentMapper.selectByOpenId(openid, studentId);
//        //是否绑定过
//        if (parentDo != null) {
//            //是否解绑过
//            if (parentDo.getDeleted()==1){
//                parentMapper.recover(parentDo.getParentId());
//                return new Response<>(COMMON_ERROR_CODE,"绑定成功");
//            }
//            return new Response<>(COMMON_ERROR_CODE,"您已绑定过");
//        }
//        String access_token = jsonObject.get("access_token").toString();
//        String getUserURL = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
////        String getUserURL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ access_token +"&openid="+openid+"&lang=zh_CN" ;
//        cn.hutool.json.JSONObject user = JSONUtil.parseObj(HttpUtil.get(getUserURL));
//        System.out.println(user);
//        ParentDo parent = new ParentDo();
//        parent.setOpenId(openid);
//        parent.setNickname(user.get("nickname").toString());
//        parent.setParentHead(user.get("headimgurl").toString());
//        parent.setSex((Integer) user.get("sex"));
//        parent.setStudentId(studentId);
//        parent.setCountry(user.get("country").toString());
//        parent.setProvince(user.get("province").toString());
//        parent.setCity(user.get("city").toString());
//        Integer count = parentMapper.insert(parent);
//        if (count == 0) {
//            return new Response<>(COMMON_ERROR_CODE,"绑定失败");
//        } else {
//            //第一次绑定家长  加50积分
//            if (parentMapper.isFirst(studentId)==1){
//                studentMapper.addIntegral(studentId,50);
//            }
//            return new Response<>(COMMON_ERROR_CODE,"绑定成功");
//        }
//    }



}
