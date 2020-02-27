package org.greencode.modules.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.greencode.common.utils.UrlUtil;
import org.greencode.modules.app.dao.ShopDao;
import org.greencode.modules.app.entity.ShopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("app/utils")
@Api(tags = "公用方法")
public class PrintController {
    @Autowired
    private ShopDao shopDao;



    @PostMapping("/sendPrint")
    @ApiOperation(value = "远程打印,以application/x-www-form-urlencoded形式传入分店shopId和内容msgDetail")
    public String sendPrint(@RequestBody String params) {
        String[] split = params.split("=");
        Long shopId = Long.parseLong(split[1].split("&")[0]);
        String msgDetail = split[2];
        ShopEntity shopEntity = shopDao.selectById(shopId);
        //获取当前时间戳
        long Time = System.currentTimeMillis();
        String reqTime = Long.toString(Time);
        //打印信息的格式类型：mode=2，自由格式打印（推荐使用）；mode=3，指令集打印；
        String mode ="2";
        //打印机的终端号码
        String deviceID=shopEntity.getDeviceId();
        //商户编码
        String memberCode ="770bf6d03ff94015b8e5f9a39f6a3b2f";
        //安全检验码，用 API 密钥和规定的参数进行 MD5 运算的结果，结果字符为小写。
        String apiKey="2ZJVZC39";
        String str = memberCode+deviceID+reqTime+apiKey;
        String securityCode = DigestUtils.md5DigestAsHex(str.getBytes());
        String Print = "reqTime="+reqTime+"&securityCode="+securityCode+"&memberCode="+memberCode+
                "&deviceID="+deviceID+"&mode="+mode+"&msgDetail="+msgDetail;
        return UrlUtil.sendPost("http://api.poscom.cn/apisc/sendMsg",Print);

    }
    @GetMapping("/getDate")
    @ApiOperation(value = "获取时间")
    public Date getData(){
        return new Date();
    }
}
