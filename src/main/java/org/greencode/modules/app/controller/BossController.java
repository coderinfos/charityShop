package org.greencode.modules.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.greencode.modules.app.entity.BossEntity;
import org.greencode.modules.app.service.BossService;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;



/**
 * 店长排班表
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:58:37
 */
@RestController
@RequestMapping("app/boss")
@Api(tags = "店长排班表")
@Slf4j
public class BossController {
    @Autowired
    private BossService bossService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bossService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("列表")
    public R info(@PathVariable("id") Long id){
		BossEntity boss = bossService.getById(id);

        return R.ok().put("boss", boss);
    }

    /**
     * 未来三天申请店长列表
     * @return
     */
    @GetMapping("/appointmentList")
    @ApiOperation("未来三天申请店长列表")
    public R appointmentList (){
        List<BossEntity> nextThreeDay = bossService.findNextThreeDay();
        return R.ok().put("appointmentList",nextThreeDay);
    }
    /**
     * 店长申请，信息保存
     */
    @PostMapping("/save")
    @ApiOperation("店长申请，信息保存传入UserId，ShopId，DutyType，DutyDate")
    public R save(@RequestBody BossEntity boss){
        if(boss.getUserId()==null||boss.getShopId()==null||boss.getDutyType()==null||boss.getDutyDate()==null){
            return R.error(HttpStatus.SC_BAD_REQUEST,"信息不完整");
        }
        boolean find=bossService.getAppointment(boss.getDutyDate(),boss.getDutyType());
        if(find){
            return R.ok("该时间已有店长");
        }
        boolean code = bossService.save(boss);
        return common(code);

    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public R update(@RequestBody BossEntity boss){
        bossService.updateById(boss);
        return R.ok();
    }

    /**
     * 通过排班id,取消店长预约
     * @param
     * @return
     */
    @PostMapping("/cancel")
    @ApiOperation("通过排班id,取消店长预约")
    public R cancel(@RequestBody Long id){
        if(id==null){
            log.info("id not is null");
            return R.error(HttpStatus.SC_BAD_REQUEST,"信息不完整");
        }

        BossEntity boss = bossService.getById(id);
        if(boss==null){
            return R.error(HttpStatus.SC_NOT_FOUND,"没有找到该用户");
        }
        boss.setDutyStatus(0);
        boolean code = bossService.updateById(boss);
        return common(code);
    }


    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
        public R delete(@RequestBody Long[] ids){
		bossService.removeByIds(Arrays.asList(ids));

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
