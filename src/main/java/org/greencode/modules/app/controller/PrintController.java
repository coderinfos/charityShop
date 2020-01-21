package org.greencode.modules.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.greencode.common.utils.UrlUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/utils")
@Api(tags = "公用方法")
public class PrintController {
    @PostMapping("/sendPrint")
    @ApiOperation(value = "远程打印")
    public String sendPrint(@RequestParam("param") String param) {
        return UrlUtil.sendPost("http://api.poscom.cn/apisc/sendMsg",param);
    }

}
