package org.greencode.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.greencode.modules.app.entity.DonateEntity;
import org.greencode.modules.app.service.DonateService;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;



/**
 * 捐赠表
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:58:37
 */
@RestController
@RequestMapping("app/donate")
@Api(tags = "捐赠表")
@Slf4j
public class DonateController {
    @Autowired
    private DonateService donateService;
    @Autowired
    private UserService userService;
    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = donateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("列表")
    public R info(@PathVariable("id") Long id){
		DonateEntity donate = donateService.getById(id);

        return R.ok().put("donate", donate);
    }

    /**
     * 捐赠物品，传入userId，donate_submit_time
     */
    @PostMapping("/save")
    @ApiOperation("捐赠物品，传入userId，donate_submit_time")
    public R save(@RequestBody DonateEntity donate){
        if(donate.getUserId()==null || donate.getDonateSubmitTime()==null){
            return R.error(HttpStatus.SC_BAD_REQUEST,"信息不完整");
        }
        DonateEntity donateEntity = new DonateEntity();
        donateEntity.setUserId(donate.getUserId());
        donateEntity.setDonateSubmitTime(donate.getDonateSubmitTime());
		donateService.save(donateEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public R update(@RequestBody DonateEntity donate){
		donateService.updateById(donate);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
        public R delete(@RequestBody Long[] ids){
		donateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }





}
