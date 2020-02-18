package org.greencode.modules.app.controller;

import java.util.*;
import java.util.regex.Pattern;

import cn.hutool.core.collection.CollectionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;

import org.apache.shiro.util.CollectionUtils;
import org.greencode.common.utils.IPUtils;
import org.greencode.modules.app.entity.DonateDTO;
import org.greencode.modules.app.entity.HomeDonateVO;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.UserService;
import org.greencode.modules.app.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.greencode.modules.app.entity.DonateEntity;
import org.greencode.modules.app.service.DonateService;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;

import javax.servlet.http.HttpServletRequest;

import static org.greencode.common.constant.ClientConstants.*;
import static org.greencode.common.constant.ClientConstants.NOT_FIND_ERROR_MSG;


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
    @Autowired
    private WxService wxService;
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
     * @param
     * @return
     */
    @GetMapping("/myDonate")
    @ApiOperation("（我的捐赠）通过用户ID来查询捐赠表,userId,pageNum,pageSize,type")
    public R myDonate(@RequestParam Map<String, Object> params){
        Long userId = Long.parseLong(params.get("userId").toString());
        if(userId==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
            PageUtils donate = donateService.getByUserId(params);
            if(donate==null){
                return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
            }else {
                return R.ok().put("data",donate);
            }



    }



    /**
     * 捐赠物品，传入userId，donate_submit_time
     */
    @PostMapping("/donation")
    @ApiOperation("捐赠物品，传入userId，donateSubmitTime，shopId所有需要传入时间的以2020-01-07 09:41:03格式")
    public R donation(@RequestBody DonateEntity donate){
        if(donate.getUserId()==null || donate.getDonateSubmitTime()==null||donate.getShopId()==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }

        boolean code = donateService.save(donate);
        return common(code);
    }

    /**
     * 捐物登记
     * @param
     * @param
     * @return
     */
    @PostMapping("/receiving")
    @ApiOperation("捐物登记，传入donateType，number，mobilePhone,shopId")
    public R receiving(@RequestBody DonateDTO donate){

        if(donate.getDonateType()==null||donate.getNumber()==null||donate.getNumber()==0||donate.getMobilePhone()==null||donate.getShopId()==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        boolean isTelephone = Pattern.matches(REGEX_MOBILE,donate.getMobilePhone().toString());
        if (!isTelephone) {
            log.info("phone format error");
            return  R.error(PHONE_ERROR_CODE, PHONE_ERROR_MSG);
        }
        //通过手机号码找出用户
        UserEntity userEntity = userService.getByMobilePhone(donate.getMobilePhone());
        if(userEntity==null){
            return  R.error(NOT_FIND_ERROR_CODE, NOT_FIND_PHONE_ERROR_MSG);
        }
        //查出该用户所创建的捐物单,提交时间两周内的,且在该分店shopId
        DonateEntity donateEntity = donateService.getUnregisteredByUserId(userEntity.getId(),donate.getShopId());

        //建一个list用来存储id和type，返回前端
        List<DonateEntity> list= new ArrayList();
        if(donateEntity==null){
            //如果没有找到，则新建一条记录
            DonateEntity newdonate = new DonateEntity();
            newdonate.setUserId(userEntity.getId());
            newdonate.setDonateSubmitTime(new Date());
            newdonate.setShopId(donate.getShopId());
            donateService.save(newdonate);
            donateEntity = donateService.getUnregisteredByUserId(userEntity.getId(),donate.getShopId());
        }

        //如果传过来的number大于1则要创建相同用户id和提交时间的记录
        donateEntity.setDonateRegisterTime(new Date());
        donateEntity.setDonateType(donate.getDonateType());
        boolean code = donateService.updateById(donateEntity);
        donateEntity.setId(donateEntity.getId());
        list.add(donateEntity);
        System.out.println(donateEntity.getId());

        if(donate.getNumber()==1){
            return R.ok().put("data",list);
        }else {
            for(int i =0;i<donate.getNumber()-1;i++){
                DonateEntity num = new DonateEntity();
                num.setShopId(donateEntity.getShopId());
                num.setUserId(donateEntity.getUserId());
                num.setDonateSubmitTime(donateEntity.getDonateSubmitTime());
                num.setDonateRegisterTime(donateEntity.getDonateRegisterTime());
                num.setDonateType(donateEntity.getDonateType());
                donateService.save(num);
                System.out.println(num.getId());
                list.add(num);
            }
            return R.ok().put("data",list);
        }

    }

    /**
     * 售出登记
     * @param donate
     * @return
     */
    @PostMapping("/sold")
    @ApiOperation("售出登记，传入id，donatePrice，type所有需要传入时间的以2020-01-07 09:41:03格式")
    public R sold(@RequestBody DonateEntity donate){
        if(donate.getDonatePrice()==null||donate.getId()==null){
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        DonateEntity donateEntity = donateService.getById(donate.getId());
        if(donateEntity==null){
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }

        if(donate.getDonateType()!=null){
            donateEntity.setDonateType(donate.getDonateType());
        }
        donateEntity.setDonatePrice(donate.getDonatePrice());
        donateEntity.setDonateSaleTime(new Date());
        boolean code = donateService.updateById(donateEntity);
        //售出登记成功后自动发送订阅
        UserEntity user = userService.getById(donateEntity.getUserId());
        if(donateEntity.getDonateType()==1){
            wxService.WxPushNotification(user.getWechatId(),1,donateEntity.getDonatePrice());
        }else if(donateEntity.getDonateType()==2){
            wxService.WxPushNotification(user.getWechatId(),4,donateEntity.getDonatePrice());
        }

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
