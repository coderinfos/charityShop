///**
// *
// *
// * https://shop.charityShop.org
// *
// * 版权所有，侵权必究！
// */
//
//package org.greencode.modules.app.controller;
//
//
//import org.greencode.common.utils.R;
//import org.greencode.common.validator.ValidatorUtils;
//import org.greencode.modules.app.entity.UserEntity;
//import org.greencode.modules.app.form.RegisterForm;
//import org.greencode.modules.app.service.UserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
///**
// * 注册
// *
// * @author
// */
//@RestController
//@RequestMapping("/app")
////@Api(tags = "APP注册接口")
//public class AppRegisterController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("register")
//    @ApiOperation("注册")
//    public R register(@RequestBody RegisterForm form){
//        //表单校验
//        ValidatorUtils.validateEntity(form);
//
//        UserEntity user = new UserEntity();
//        user.setMobilePhone(form.getMobilePhone());
//        user.setUserName(form.getMobilePhone()+"");
//        user.setPassWord(DigestUtils.sha256Hex(form.getPassWord()));
//        user.setRegTime(new Date());
//        userService.save(user);
//
//        return R.ok();
//    }
//}
