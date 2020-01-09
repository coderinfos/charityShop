package org.greencode.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.UserService;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;

import static org.greencode.common.utils.ClientConstants.*;
import static org.greencode.common.utils.ClientConstants.NOT_FIND_ERROR_MSG;


/**
 * 基本用户信息表
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:58:37
 */
@RestController
@RequestMapping("app/user")
@Api(tags = "基本用户信息表")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("通过id来查询用户")
    public R info(@PathVariable("id") Long id){
		UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }


    @GetMapping("/foxMessage/{id}")
    @ApiOperation("判断个人信息是否完整")
    public R foxMessage(@PathVariable("id") Long id){
        UserEntity user = userService.getById(id);
        if(user==null){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }
        if(user.getNickName()==null||user.getRealName()==null||user.getSex()==null||user.getAge()==null||user.getMobilePhone()==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }else {
            return R.ok().put("user", user);
        }

    }


    /**
     * 注册
     */
    @PostMapping("/save")
    @ApiOperation("注册")
    public R save(@RequestBody UserEntity user){
        //注册的时候判断账号和密码的正则，个人信息不需要填写，进入后再填写



        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setPassWord(user.getPassWord());
		userService.save(userEntity);

        return R.ok();
    }

    /**
     * 填写个人信息
     */
    @PostMapping("/update")
    @ApiOperation("填写个人信息")
    public R update(@RequestBody UserEntity user){
        //填写个人信息的时候加上各种正则判断






		userService.updateById(user);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
        public R delete(@RequestBody Long[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

//    @PostMapping("register")
//    public Response register(String phone, String password, String confirm, String code, Integer role, String inviteCode) {
//        log.info("phone:{},password:{},confirm:{},code:{},role:{}", phone, password, confirm, code, role);
//        if (StrUtil.hasEmpty(phone, password, confirm, code) || role == null) {
//            log.info("params is null");
//            return new Response(PARAM_ERROR_CODE, PARAM_ERROR_MSG);
//        } else {
//            log.info("params is not null");
//            boolean isTelephone = Pattern.matches(REGEX_MOBILE, phone);
//            if (!isTelephone) {
//                log.info("phone format error");
//                return new Response(PHONE_ERROR_CODE, PHONE_ERROR_MSG);
//            }
//            boolean isPassword = Pattern.matches(REGEX_PASSWORD, password);
//            if (!isPassword) {
//                log.info("password format error");
//                return new Response(PWD_ERROR_CODE, PWD_ERROR_MSG);
//            }
//            if (!confirm.equals(password)) {
//                log.info("password do not match");
//                return new Response(PWD_MATCH_CODE, PWD_MATCH_MSG);
//            }
//            String redisCode = (String) redisTemplate.opsForValue().get(phone);
//            if (StrUtil.isEmpty(redisCode)) {
//                log.info("Validation code is invalid");
//                return new Response(CODE_INVALID_CODE, CODE_INVALID_MSG);
//            }
//            if (!code.equals(redisCode)) {
//                log.info("Validation code is error");
//                return new Response(CODE_ERROR_CODE, CODE_ERROR_MSG);
//            }
//            if (role == 0) {
//                log.info("student register");
//                return userService.studentRegister(phone, password, inviteCode);
//            } else if (role == 1) {
//                log.info("teacher register");
//                return userService.teacherRegister(phone, password);
//            } else {
//                return new Response(ROLE_ERROR_CODE, ROLE_ERROR_MSG);
//            }
//        }
//    }
    /**
     * 通过手机来查询用户
     * @param mobilePhone
     * @return
     */
    @GetMapping("/selectUserByMobilePhone/{mobilePhone}")
    @ApiOperation("通过手机来查询用户")
    public R selectUserByMobilePhone(@PathVariable("mobilePhone") Long mobilePhone){
        if(mobilePhone==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        UserEntity user = userService.getByMobilePhone(mobilePhone);
        if(user==null){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }else {
            return R.ok().put("user", user);
        }
    }
}
