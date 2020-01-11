/**
 * coderinfo Inc.
 * <p>
 * Copyright (c) 201901-2020 All Rights Reserved.
 */
package org.greencode.modules.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.greencode.common.utils.R;
import org.greencode.modules.app.biz.HomeBiz;
import org.greencode.modules.app.vo.HomeDonateDTO;
import org.greencode.modules.app.vo.HomeSchedulingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fenggang
 * @version Id: HomeController.java, v 0.1 2020年01月05日
 * 14:10 fenggang Exp $
 */
@RestController
@RequestMapping("app/mp")
@Api(tags = "小程序首页接口")
public class HomeController {

    @Resource
    private HomeBiz homeBiz;

    @GetMapping("/scheduling/list")
    @ApiOperation("排班列表")
    public R scheduling(@ApiParam("获取数据数量") @RequestParam(value = "num", defaultValue = "6") Integer num) {
        List<HomeSchedulingDTO> list = homeBiz.scheduling(num);
        return R.ok().put("data", list);
    }


    @GetMapping("/donate/list")
    @ApiOperation("捐赠列表")
    public R donate(@ApiParam("获取数据数量") @RequestParam(value = "num", defaultValue = "2") Integer num) {
        List<HomeDonateDTO> list = homeBiz.donate(num);
        return R.ok().put("data", list);
    }
}
