package org.greencode.modules.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.greencode.common.utils.R;
import org.greencode.modules.app.entity.BossEntity;
import org.greencode.modules.app.entity.HomeBossVO;
import org.greencode.modules.app.entity.HomeDonateVO;
import org.greencode.modules.app.service.BossService;
import org.greencode.modules.app.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("app/home")
@Api(tags = "小程序首页接口")
public class HomeController {
    @Autowired
    private DonateService donateService;
    @Autowired
    private BossService bossService;
    /**
     * 查询最近的五条售出记录
     */
    @GetMapping("/donate")
    @ApiOperation("查询最近的五条售出记录")
    public R homeDonate(){
        List<HomeDonateVO> donate = donateService.getRecentFive();
        return R.ok().put("donate", donate);
    }

    /**
     * 查询未来十四天的店长
     * @return
     */
    @GetMapping("/Boss")
    @ApiOperation("查询未来十四天的店长")
    public R theMonthBoss (){
        List<HomeBossVO> list = bossService.findtheMonthBoss();
        return R.ok().put("data",list);
    }
}
