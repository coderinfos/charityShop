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


//这个方法是内容由前端传输
//    @PostMapping("/sendPrint")
//    @ApiOperation(value = "远程打印,以application/x-www-form-urlencoded形式传入分店shopId和内容msgDetail")
//    public String sendPrint(@RequestBody String params) {
//        String[] split = params.split("=");
//        Long shopId = Long.parseLong(split[1].split("&")[0]);
//        String msgDetail = split[2];
//        ShopEntity shopEntity = shopDao.selectById(shopId);
//        //获取当前时间戳
//        long Time = System.currentTimeMillis();
//        String reqTime = Long.toString(Time);
//        //打印信息的格式类型：mode=2，自由格式打印（推荐使用）；mode=3，指令集打印；
//        String mode ="2";
//        //打印机的终端号码
//        String deviceID=shopEntity.getDeviceId();
//        //商户编码
//        String memberCode ="770bf6d03ff94015b8e5f9a39f6a3b2f";
//        //安全检验码，用 API 密钥和规定的参数进行 MD5 运算的结果，结果字符为小写。
//        String apiKey="2ZJVZC39";
//        String str = memberCode+deviceID+reqTime+apiKey;
//        String securityCode = DigestUtils.md5DigestAsHex(str.getBytes());
//        String Print = "reqTime="+reqTime+"&securityCode="+securityCode+"&memberCode="+memberCode+
//                "&deviceID="+deviceID+"&mode="+mode+"&msgDetail="+msgDetail;
//        return UrlUtil.sendPost("http://api.poscom.cn/apisc/sendMsg",Print);
//
//    }

    @PostMapping("/sendPrintNew")
    @ApiOperation(value = "远程打印,传入商品id，商品type和分店shopId")
    public String sendPrintNew(@RequestBody Map<String,Object> params) {

        String id = params.get("id").toString();
        Integer type = Integer.parseInt(params.get("type").toString());
        Long shopId = Long.parseLong(params.get("shopId").toString());
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
        String msgDetail="";
        //1物品,2书籍
        if(type==1){
            msgDetail = "SIZE+40+mm%2C20+mm%0AGAP+2+mm%2C0+mm%0AREFERENCE+10%2C0%0ASPEED+2%0ADENSITY+6%0ADIRECTION+0%0ACLS%0ATEXT+10%2C32%2C%22TSS24.BF2%22%2C0%2C1%2C1%2C%22mycode%E5%8F%B7%E7%89%A9%E5%93%81%22%0AQRCODE+140%2C20%2CL%2C4%2CA%2C0%2C%22https%3A%2F%2Fshop.bbalt.org%2FcharityShop%2Fitem%2Fmycode.html%22%0APRINT+1%2C1";
        }else if(type==2){
            msgDetail = "SIZE+40+mm%2C20+mm%0AGAP+2+mm%2C0+mm%0AREFERENCE+10%2C0%0ASPEED+2%0ADENSITY+6%0ADIRECTION+0%0ACLS%0ATEXT+10%2C32%2C%22TSS24.BF2%22%2C0%2C1%2C1%2C%22mycode%E5%8F%B7%E4%B9%A6%E7%B1%8D%22%0AQRCODE+140%2C20%2CL%2C4%2CA%2C0%2C%22https%3A%2F%2Fshop.bbalt.org%2FcharityShop%2Fitem%2Fmycode.html%22%0APRINT+1%2C1";
        }
        msgDetail = msgDetail.replaceAll("mycode", id);
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
