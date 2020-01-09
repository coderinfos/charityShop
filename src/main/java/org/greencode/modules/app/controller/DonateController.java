package org.greencode.modules.app.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import org.greencode.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.greencode.modules.app.entity.DonateEntity;
import org.greencode.modules.app.service.DonateService;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;

import static org.greencode.common.utils.ClientConstants.*;
import static org.greencode.common.utils.ClientConstants.NOT_FIND_ERROR_MSG;


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
     * 通过用户id来查询捐赠表，（有捐物登记时间认为是有效捐物）
     * @param userId
     * @return
     */
    @GetMapping("/myDonate/{userId}")
    @ApiOperation("通过用户ID来查询捐赠表")
    public R myDonate(@PathVariable("userId") Long userId){
        if(userId==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        List<DonateEntity> donate = donateService.getByUserId(userId);
        if(donate.isEmpty()){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }else {
            return R.ok().put("data",donate);
        }


    }

    /**
     * 通过用户ID来查询已经售出的捐赠记录,通过donatePrice这个字段判断是否售出，未售出为null
     * @param userId
     * @return
     */
    @GetMapping("/myDonateSold/{userId}")
    @ApiOperation("通过用户ID来查询已经售出的捐赠记录")
    public R myDonateSold(@PathVariable("userId") Long userId){
        if(userId==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        List<DonateEntity> donate = donateService.getSoldByUserId(userId);
        if(donate.isEmpty()){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }else {
            return R.ok().put("data",donate);
        }


    }

    /**
     * 捐赠物品，传入userId，donate_submit_time
     */
    @PostMapping("/save")
    @ApiOperation("捐赠物品，传入userId，donateSubmitTime，所有需要传入时间的以2020-01-07 09:41:03格式")
    public R save(@RequestBody DonateEntity donate){
        if(donate.getUserId()==null || donate.getDonateSubmitTime()==null){
            return R.error(1,"信息不完整");
        }
        DonateEntity donateEntity = new DonateEntity();
        donateEntity.setUserId(donate.getUserId());
        donateEntity.setDonateSubmitTime(donate.getDonateSubmitTime());
		donateService.save(donateEntity);
        return R.ok();
    }

    /**
     * 售出登记
     * @param donate
     * @return
     */
    @PostMapping("/sold")
    @ApiOperation("售出登记，传入id，donateSaleTime，donatePrice，所有需要传入时间的以2020-01-07 09:41:03格式")
    public R sold(@RequestBody DonateEntity donate){
        if(donate.getDonatePrice()==null||donate.getDonateSaleTime()==null||donate.getId()==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        DonateEntity donateEntity = donateService.getById(donate.getId());
        if(donateEntity==null){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }
        donateEntity.setDonatePrice(donate.getDonatePrice());
        donateEntity.setDonateSaleTime(donate.getDonateSaleTime());
        boolean code = donateService.save(donateEntity);
        return common(code);
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


    public R common(boolean code){
        if(code){
            return R.ok();
        }else {
            return R.error();
        }
    }


}
