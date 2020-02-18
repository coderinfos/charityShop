package org.greencode.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.greencode.common.utils.ExcelUtils;
import org.greencode.modules.app.dao.BossDao;
import org.greencode.modules.app.dao.DonateDao;
import org.greencode.modules.app.dao.ShopDao;
import org.greencode.modules.app.dao.UserDao;
import org.greencode.modules.app.entity.BossEntity;
import org.greencode.modules.app.entity.DonateEntity;
import org.greencode.modules.app.entity.ShopEntity;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Abbot
 * @description
 * @date 2018/9/17 11:24
 **/
@RestController
@RequestMapping("admin/excel")
@Api(tags = "excel导入导出")
public class ExcelController  {

    @Autowired
    UserDao userDao;
    @Autowired
    BossDao bossDao;
    @Autowired
    DonateDao donateDao;
    @Autowired
    ShopDao shopDao;
    @GetMapping("/userExport")
    @ApiOperation(value = "导出")
    public void userExport(HttpServletResponse response)  throws IOException {

        List<UserEntity> list = userDao.selectList(null);
        ExcelUtils.writeExcel(response, list, UserEntity.class,"志愿者账号");
//        long t1 = System.currentTimeMillis();
//        long t2 = System.currentTimeMillis();
//        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
    }
    @GetMapping("/bossExport")
    @ApiOperation(value = "导出")
    public void bossExport(HttpServletResponse response)  throws IOException {
        List<BossEntity> list = bossDao.selectList(null);
        ExcelUtils.writeExcel(response, list, BossEntity.class,"排班表");
    }
    @GetMapping("/shopExport")
    @ApiOperation(value = "导出")
    public void shopExport(HttpServletResponse response)  throws IOException {
        List<ShopEntity> list = shopDao.selectList(null);
        ExcelUtils.writeExcel(response, list, ShopEntity.class,"分店表");
    }
    @GetMapping("/donateExport")
    @ApiOperation(value = "导出")
    public void donateExport(HttpServletResponse response)  throws IOException {
        List<DonateEntity> list = donateDao.selectList(null);
        ExcelUtils.writeExcel(response, list, DonateEntity.class,"捐物表");
    }

//    @RequestMapping(value = "/readExcel", method = RequestMethod.POST)
//    public void readExcel(MultipartFile file){
//
//        long t1 = System.currentTimeMillis();
//        List<BusClick> list = ExcelUtils.readExcel("", BusClick.class, file);
//        long t2 = System.currentTimeMillis();
//        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
//        list.forEach(
//                b -> System.out.println(JSON.toJSONString(b))
//        );
//    }
}