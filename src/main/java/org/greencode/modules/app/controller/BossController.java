package org.greencode.modules.app.controller;


import java.util.*;
import java.util.regex.Pattern;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.greencode.common.utils.IPUtils;
import org.greencode.modules.app.entity.HomeBossVO;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.greencode.modules.app.entity.BossEntity;
import org.greencode.modules.app.service.BossService;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;

import javax.servlet.http.HttpServletRequest;

import static org.greencode.common.constant.ClientConstants.*;
import static org.greencode.common.constant.ClientConstants.PHONE_ERROR_CODE;
import static org.greencode.common.constant.ClientConstants.PHONE_ERROR_MSG;

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

    @Autowired
    private UserService userService;

    /**
     * 信息
     */
    @GetMapping("/theDay/{userId}")
    @ApiOperation("通过用户userId来查询当天是否有排班，如果有就会返回排班信息")
    public R theDay(@PathVariable("userId") Long userId){
        List<BossEntity> boss = bossService.theDay(userId);
        if(boss==null){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }
        return R.ok().put("boss", boss);
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
     * 通过用户id来查询排班表
     * @param
     * @return
     */
    @GetMapping("/myBoss")
    @ApiOperation("(我的排班)userId,page,limit来查询排班表，duty_status为0则是取消")
    public R myBoss(@RequestParam Map<String, Object> params){
        Long userId = Long.parseLong(params.get("userId").toString());
        if(userId==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        PageUtils donate = bossService.getByUserId(params);
        if(donate==null){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }else {
            return R.ok().put("data",donate);
        }
    }

    /**
     * 通过用户id来查询排班表，筛选未开始且未取消的排班
     * @param userId
     * @return
     */
    @GetMapping("/notStart/{userId}")
    @ApiOperation("通过用户id来查询排班表，筛选未开始且未取消的排班")
    public R notStart (@PathVariable("userId") Long userId){
        if(userId==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        List<BossEntity> donate = bossService.getNotStart(userId);
        if(donate.isEmpty()){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }else {

            return R.ok().put("data",donate);
        }

    }
    /**
     * 通过用户id来查询已经取消排班表
     * @param userId
     * @return
     */
    @GetMapping("/cancelBoss/{userId}")
    @ApiOperation("通过用户id来查询已经取消排班表")
    public R  cancelBoss (@PathVariable("userId") Long userId){
        if(userId==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        List<BossEntity> donate = bossService.getCancelBoss(userId);
        if(donate.isEmpty()){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }else {

            return R.ok().put("data",donate);
        }

    }
    /**
     * 通过用户id来查询排班表，筛选已完成且未取消的排班
     * @param userId
     * @return
     */
    @GetMapping("/completed/{userId}")
    @ApiOperation("通过用户id来查询排班表，筛选已完成且未取消的排班")
    public R Completed (@PathVariable("userId") Long userId) {
        if (userId == null) {
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        List<BossEntity> donate = bossService.completed(userId);
        if (donate.isEmpty()) {
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        } else {
            return R.ok().put("data",donate);
        }
    }

    /**
     * 通过id来查询是否在24小时内
     * @param id
     * @return
     */
    @GetMapping("/cancelTime/{id}")
    @ApiOperation("取消时间查询，判断是否在24h外，如果是返回成功，不是则返回code=1")
    public R cancelTime(@PathVariable("id") Long id){
        BossEntity boss = bossService.getById(id);
        //获取当前系统时间
        long longDate = System.currentTimeMillis();
        Date start = new Date(longDate);
        Date end = boss.getDutyDate();
        long cha = end.getTime() - start.getTime();
        if(cha<0){
            return R.error();
        }
        double result = cha * 1.0 / (1000 * 60 * 60);
        if(result<=24){
            return R.error(CANCEL_TIME_CODE,CANCEL_TIME_MSG);
        }else{
            return R.ok();
        }
    }
    /**
     * 未来十四天申请店长列表
     * @return
     */
    @GetMapping("/appointmentList")
    @ApiOperation("未来十四天申请店长列表")
    public R appointmentList (){
        List<BossEntity> nextThreeDay = bossService.findNextThreeDay();

        return R.ok().put("data",nextThreeDay);
    }


    /**
     * 店长申请，信息保存
     */
    @PostMapping("/apply")
    @ApiOperation("店长申请，信息保存传入UserId，ShopId，DutyType，DutyDate预约的日期(如果type为1则传当天9点，2020-01-09 09:00:00，为2下午，则2020-01-09 14:30:00)")
    public R apply(@RequestBody BossEntity boss){
        if(boss.getUserId()==null||boss.getShopId()==null||boss.getDutyType()==null||boss.getDutyDate()==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        boolean find=bossService.getAppointment(boss.getDutyDate(),boss.getDutyType(),boss.getShopId());
        if(!find){
            return R.error(EXIST_ERROR_CODE,EXIST_ERROR_MSG);
        }
        boolean code = bossService.save(boss);
        return common(code);


    }


    /**
     * 通过排班id,取消店长预约
     * @param
     * @return
     */
    @PostMapping("/cancel")
    @ApiOperation("通过排班id,mobilePhone,取消店长预约")
    public R cancel(@RequestBody Map<String, Object> params){
        String id = (String)params.get("id");
        String mobilePhone = (String)params.get("mobilePhone");
        if(id==null){
            log.info("id not is null");
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        boolean isTelephone = Pattern.matches(REGEX_MOBILE,mobilePhone);
        if (!isTelephone) {
            log.info("phone format error");
            return  R.error(PHONE_ERROR_CODE, PHONE_ERROR_MSG);
        }
        BossEntity boss = bossService.getById(id);
        if(boss==null){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }
        UserEntity userEntity = userService.getById(boss.getUserId());

        if(!userEntity.getMobilePhone().equals(Long.parseLong(mobilePhone))){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_PHONE_ERROR_MSG);
        }
        boss.setDutyStatus(0);
        boolean code = bossService.updateById(boss);
        return common(code);
    }




    public R common(boolean code){
        if(code){
            return R.ok();
        }else {
            return R.error();
        }
    }

}
