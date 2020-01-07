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
    @ApiOperation("列表")
    public R info(@PathVariable("id") Long id){
		UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public R update(@RequestBody UserEntity user){
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


    /**
     * 通过手机来查询用户
     * @param mobilePhone
     * @return
     */
    @GetMapping("/selectUserByMobilePhone/{mobilePhone}")
    @ApiOperation("列表")
    public R selectUserByMobilePhone(@PathVariable("mobilePhone") Long mobilePhone){
        if(mobilePhone==null){
            return R.error(HttpStatus.SC_BAD_REQUEST,"信息不完整");
        }
        UserEntity user = userService.getByMobilePhone(mobilePhone);
        if(user==null){
            return R.error(HttpStatus.SC_NOT_FOUND,"没有找到该用户");
        }else {
            return R.ok().put("user", user);
        }
    }
}
