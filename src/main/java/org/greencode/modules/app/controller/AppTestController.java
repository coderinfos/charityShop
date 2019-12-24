/**
 *
 *
 * https://shop.charityShop.org
 *
 * 版权所有，侵权必究！
 */

package org.greencode.modules.app.controller;


import org.greencode.common.utils.R;
import org.greencode.common.annotation.Login;
import org.greencode.common.annotation.LoginUser;
import org.greencode.modules.app.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.greencode.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP测试接口
 *
 * @author
 */
@RestController
@RequestMapping("/app")
@Api(tags = "APP测试接口")
public class AppTestController {
    @Autowired
    UserService userService;


    @Login
    @GetMapping("userInfo")
    @ApiOperation("获取用户信息")
    public R userInfo(@LoginUser UserEntity user){
        return R.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public R userInfo(@RequestAttribute("userId") Integer userId){
        return R.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public R notToken(){
        return R.ok().put("msg", "无需token也能访问。。。");
    }



    @GetMapping("testInterface")
    @ApiOperation("测试接口")
    public R testInterface(@LoginUser UserEntity user){

        return R.ok().put("测试接口", "接口测试成功");
    }


    @GetMapping("testQuery")
    @ApiOperation("测试查询")
    public R testQuery(@LoginUser UserEntity user){
        UserEntity one = userService.getOne(null);
        return R.ok().put("entity", one);
    }


}
