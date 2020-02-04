package org.greencode.modules.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.greencode.common.utils.UrlUtil;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;

@RestController
@RequestMapping("app/utils")
@Api(tags = "公用方法")
public class PrintController {
    @PostMapping("/sendPrint")
    @ApiOperation(value = "远程打印")
    public String sendPrint(@RequestParam("param") String param) {
        return UrlUtil.sendPost("http://api.poscom.cn/apisc/sendMsg",param);
    }

    @GetMapping("/getDate")
    @ApiOperation(value = "获取时间")
    public Date getData(){
        return new Date();
    }
}
