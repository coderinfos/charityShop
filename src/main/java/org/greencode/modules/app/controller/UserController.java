package org.greencode.modules.app.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.UserService;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;

import static org.greencode.common.constant.ClientConstants.*;
import static org.greencode.common.constant.ClientConstants.NOT_FIND_ERROR_MSG;


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
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;



    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("通过id来查询用户")
    public R info(@PathVariable("id") Long id){
        if(id==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
		UserEntity user = userService.getById(id);
        if(user==null){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }
        return R.ok().put("user", user);
    }

    /**
     * 判断个人信息是否完整
     * @param id
     * @return
     */

    @GetMapping("/foxMessage/{id}")
    @ApiOperation("判断个人信息是否完整,真实姓名，昵称，手机")
    public R foxMessage(@PathVariable("id") Long id){
        UserEntity user = userService.getById(id);
        if(user==null){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }
        if(user.getNickName()==null||user.getMobilePhone()==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }else {
            return R.ok();
        }

    }




    /**
     * 填写个人信息
     */
    @PostMapping("/update")
    @ApiOperation("填写个人信息，个人信息的修改")
    public R update(@RequestBody UserEntity user){
        if(user.getId()==null){
            log.info("id is not null");
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }
        if(user.getMobilePhone()!=null){
            //填写个人信息的时候加上各种正则判断
            boolean isTelephone = Pattern.matches(REGEX_MOBILE, user.getMobilePhone().toString());
            if (!isTelephone) {
                log.info("phone format error");
                return  R.error(PHONE_ERROR_CODE, PHONE_ERROR_MSG);
            }
        }

//        boolean isPassword = Pattern.matches(REGEX_PASSWORD, user.getPassWord());
//            if (!isPassword) {
//                log.info("password format error");
//                return R.error(PWD_ERROR_CODE, PWD_ERROR_MSG);
//            }
        if(user.getAge()!=null){
            boolean isAge = Pattern.matches(REGEX_AGE,user.getAge().toString());
            if (!isAge) {
                log.info("age format error");
                return R.error(AGE_ERROR_CODE, AGE_ERROR_MSG);
            }
        }
//
//        if(StringUtils.isEmpty(user.getRealName())){
//            return R.error(NAME_ERROR_CODE, NAME_ERROR_MSG);
//        }
//        if(StringUtils.isEmpty(user.getNickName())){
//            return R.error(NAME_ERROR_CODE, NAME_ERROR_MSG);
//        }
        boolean code = userService.updateById(user);
        return common(code);
    }


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


    public R common(boolean code){
        if(code){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
