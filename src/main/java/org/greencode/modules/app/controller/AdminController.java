package org.greencode.modules.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.greencode.common.utils.IPUtils;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;
import org.greencode.modules.app.dao.BossDao;
import org.greencode.modules.app.dao.DonateDao;
import org.greencode.modules.app.dao.ShopDao;
import org.greencode.modules.app.dao.UserDao;
import org.greencode.modules.app.entity.*;
import org.greencode.modules.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

import static org.greencode.common.constant.ClientConstants.*;

@RestController
@RequestMapping("admin/")
@Api(tags = "后台接口")
@Slf4j
public class AdminController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopDao shopDao;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DonateService donateService;
    @Autowired
    private DonateDao donateDao;
    @Autowired
    private BossService bossService;
    @Autowired
    private BossDao bossDao;
    @Autowired
    private OperationlLogService operationlLogService;
    /**
     * 列表
     */
    @GetMapping("shop/list")
    @ApiOperation("后台专用接口,列表")
    public R shopList(@RequestParam Map<String, Object> params){
        PageUtils page = shopService.queryPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("shop/info/{id}")
    @ApiOperation("通过id来查询信息")
    public R shopInfo(@PathVariable("id") Long id){
        ShopEntity shop = shopService.getById(id);

        return R.ok().put("shop", shop);
    }

    @PostMapping("shop/save")
    @ApiOperation("后台专用接口，保存")
    public R shopSave(@RequestBody ShopEntity shop,HttpServletRequest request){
        shop.setOperationTime(new Date());
        String ipAddr = IPUtils.getIpAddr(request);
        shop.setOperatorIp(ipAddr);
//        shop.setOperator(operator);
        shopService.save(shop);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("shop/update")
    @ApiOperation("后台专用接口，修改")
    public R shopUpdate(@RequestBody ShopEntity shop,HttpServletRequest request){
        shop.setOperationTime(new Date());
        String ipAddr = IPUtils.getIpAddr(request);
        shop.setOperatorIp(ipAddr);
//        shop.setOperator(operator);
        shopService.updateById(shop);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("shop/delete")
    @ApiOperation("后台专用接口，删除")
    @Transactional(rollbackFor = Exception.class)
    public R shopDelete(@RequestBody Long[] ids,HttpServletRequest request){

        OperationlLogEntity operationlLogEntity= new OperationlLogEntity();
        //操作时间和ip
        operationlLogEntity.setOperationTime(new Date());
        String ipAddr = IPUtils.getIpAddr(request);
        operationlLogEntity.setOperatorIp(ipAddr);
        for(int i=0;i<ids.length;i++){
            ShopEntity shop = shopDao.selectById(ids[i]);
            operationlLogEntity.setOperationType("分店管理");
            operationlLogEntity.setContent(shop.toString());
            operationlLogService.save(operationlLogEntity);
        }

        shopService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


//-----------------------------------------------------------------
    /**
     * 列表
     */
    @GetMapping("user/list")
    @ApiOperation("后台专用接口，列表")
    public R userList(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("user/info/{id}")
    @ApiOperation("通过id来查询用户")
    public R userInfo(@PathVariable("id") Long id){
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
     * 注册
     */
    @PostMapping("user/save")
    @ApiOperation("后台专用接口，保存")
    public R userSave(@RequestBody UserEntity user){
//        String userName = user.getUserName();
//        if(userName.indexOf(" ")!=-1||userName.length()<6||userName.length()>15){
//            return R.error(USN_ERROR_CODE,USN_ERROR_MSG);
//        }
//
//        boolean isPassword = Pattern.matches(REGEX_PASSWORD, user.getPassWord());
//        if (!isPassword) {
//            log.info("password format error");
//            return R.error(PWD_ERROR_CODE, PWD_ERROR_MSG);
//        }
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserName(user.getUserName());
//        userEntity.setPassWord(user.getPassWord());
        userService.save(user);
        return R.ok();
    }
    /**
     * 填写个人信息
     */
    @PostMapping("user/update")
    @ApiOperation("填写个人信息，个人信息的修改")
    public R userUpdate(@RequestBody UserEntity user){
        if(user.getId()==null){
            log.info("id is not null");
            return R.error(NOT_FIND_ERROR_CODE,NOT_FIND_ERROR_MSG);
        }
        //填写个人信息的时候加上各种正则判断
        boolean isTelephone = Pattern.matches(REGEX_MOBILE, user.getMobilePhone().toString());
        if (!isTelephone) {
            log.info("phone format error");
            return  R.error(PHONE_ERROR_CODE, PHONE_ERROR_MSG);
        }
//        boolean isPassword = Pattern.matches(REGEX_PASSWORD, user.getPassWord());
//            if (!isPassword) {
//                log.info("password format error");
//                return R.error(PWD_ERROR_CODE, PWD_ERROR_MSG);
//            }
        boolean isAge = Pattern.matches(REGEX_AGE,user.getAge().toString());
        if (!isAge) {
            log.info("age format error");
            return R.error(AGE_ERROR_CODE, AGE_ERROR_MSG);
        }
        if(StringUtils.isEmpty(user.getRealName())){
            return R.error(NAME_ERROR_CODE, NAME_ERROR_MSG);
        }
        if(StringUtils.isEmpty(user.getNickName())){
            return R.error(NAME_ERROR_CODE, NAME_ERROR_MSG);
        }
        boolean code = userService.updateById(user);
        return common(code);
    }

    /**
     * 删除
     */
    @PostMapping("user/delete")
    @ApiOperation("后台专用接口，删除")
    @Transactional(rollbackFor = Exception.class)
    public R userDelete(@RequestBody Long[] ids,HttpServletRequest request){
        OperationlLogEntity operationlLogEntity= new OperationlLogEntity();
        //操作时间和ip
        operationlLogEntity.setOperationTime(new Date());
        String ipAddr = IPUtils.getIpAddr(request);
        operationlLogEntity.setOperatorIp(ipAddr);
        for(int i=0;i<ids.length;i++){
            UserEntity user = userDao.selectById(ids[i]);
            operationlLogEntity.setOperationType("用户管理");
            operationlLogEntity.setContent(user.toString());
            operationlLogEntity.setUserId(user.getId());
            operationlLogService.save(operationlLogEntity);
        }

        userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
//--------------------------------------------------------------
    /**
     * 列表
     */
    @GetMapping("boss/list")
    @ApiOperation("列表")
    public R bossList(@RequestParam Map<String, Object> params){
        PageUtils page = bossService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("boss/info/{id}")
    @ApiOperation("列表")
    public R bossInfo(@PathVariable("id") Long id){
        BossEntity boss = bossService.getById(id);

        return R.ok().put("boss", boss);
    }

    @PostMapping("boss/save")
    @ApiOperation("后台专用接口，保存")
    public R bossSave(@RequestBody BossEntity boss, HttpServletRequest request){
        boss.setOperationTime(new Date());
        boss.setDutySubmitTime(new Date());
        String ipAddr = IPUtils.getIpAddr(request);
        boss.setOperatorIp(ipAddr);
//        boss.setOperator(operator);
        boolean code = bossService.save(boss);
        return common(code);

    }
    /**
     * 修改
     */
    @PostMapping("boss/update")
    @ApiOperation("后台专用接口,修改")
    public R bossUpdate(@RequestBody BossEntity boss, HttpServletRequest request){
        boss.setOperationTime(new Date());
        String ipAddr = IPUtils.getIpAddr(request);
        boss.setOperatorIp(ipAddr);
//        boss.setOperator(operator);
        bossService.updateById(boss);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("boss/delete")
    @ApiOperation("后台专用接口,删除")
    @Transactional(rollbackFor = Exception.class)
    public R bossDelete(@RequestBody Long[] ids,HttpServletRequest request){

        OperationlLogEntity operationlLogEntity= new OperationlLogEntity();
        //操作时间和ip
        operationlLogEntity.setOperationTime(new Date());
        String ipAddr = IPUtils.getIpAddr(request);
        operationlLogEntity.setOperatorIp(ipAddr);
        for(int i=0;i<ids.length;i++){
            BossEntity boss = bossDao.selectById(ids[i]);
            operationlLogEntity.setOperationType("排班管理");
            operationlLogEntity.setUserId(boss.getUserId());
            operationlLogEntity.setContent(boss.toString());
            operationlLogService.save(operationlLogEntity);
        }

        bossService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
//------------------------------------------------------
    /**
     * 列表
     */
    @GetMapping("donate/receivingList")
    @ApiOperation("捐物登记列表")
    public R receivingList(@RequestParam Map<String, Object> params){
        PageUtils page = donateService.receivingQueryPage(params);

        return R.ok().put("page", page);
    }
    @GetMapping("donate/soldList")
    @ApiOperation("后台专用出售明细")
    public R soldList(@RequestParam Map<String, Object> params){
        PageUtils page = donateService.soldQueryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("donate/info/{id}")
    @ApiOperation("列表")
    public R info(@PathVariable("id") Long id){
        DonateEntity donate = donateService.getById(id);
        return R.ok().put("donate", donate);
    }
    /**
     *
     */
    @PostMapping("donate/save")
    @ApiOperation("后台专用接口，保存")
    public R donateSave(@RequestBody DonateEntity donate, HttpServletRequest request){
        if (donate.getDonateRegisterTime()==null||donate.getUserId()==null||donate.getDonateType()==null) {
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }
        donate.setOperationTime(new Date());
        String ipAddr = IPUtils.getIpAddr(request);
        donate.setOperatorIp(ipAddr);
        boolean code = donateService.save(donate);
        return common(code);
    }

    /**
     * 修改
     */
    @PostMapping("donate/update")
    @ApiOperation("后台专用接口,修改")
    public R donateUpdate(@RequestBody DonateEntity donate, HttpServletRequest request){
        if (donate.getDonateRegisterTime()==null||donate.getUserId()==null||donate.getDonateType()==null) {
            return R.error(PARAM_ERROR_CODE,PARAM_ERROR_MSG);
        }

        donate.setOperationTime(new Date());
        String ipAddr = IPUtils.getIpAddr(request);
        donate.setOperatorIp(ipAddr);
        donateService.updateById(donate);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("donate/delete")
    @ApiOperation("删除")
    @Transactional(rollbackFor = Exception.class)
    public R donateDelete(@RequestBody Long[] ids,HttpServletRequest request){

        OperationlLogEntity operationlLogEntity= new OperationlLogEntity();
        //操作时间和ip
        operationlLogEntity.setOperationTime(new Date());
        String ipAddr = IPUtils.getIpAddr(request);
        operationlLogEntity.setOperatorIp(ipAddr);
        for(int i=0;i<ids.length;i++){
            DonateEntity donate = donateDao.selectById(ids[i]);
            operationlLogEntity.setOperationType("捐物管理");
            operationlLogEntity.setUserId(donate.getUserId());
            operationlLogEntity.setContent(donate.toString());
            operationlLogService.save(operationlLogEntity);
        }
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
