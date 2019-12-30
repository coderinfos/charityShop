package org.greencode.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.greencode.modules.app.entity.AdminUserEntity;
import org.greencode.modules.app.service.AdminUserService;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;



/**
 * 管理员账号表
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:58:37
 */
@RestController
@RequestMapping("app/adminuser")
@Api(tags = "管理员账号表")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adminUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("列表")
    public R info(@PathVariable("id") Long id){
		AdminUserEntity adminUser = adminUserService.getById(id);

        return R.ok().put("adminUser", adminUser);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    public R save(@RequestBody AdminUserEntity adminUser){
		adminUserService.save(adminUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public R update(@RequestBody AdminUserEntity adminUser){
		adminUserService.updateById(adminUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
        public R delete(@RequestBody Long[] ids){
		adminUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
