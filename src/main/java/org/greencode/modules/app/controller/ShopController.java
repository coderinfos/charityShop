package org.greencode.modules.app.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.greencode.common.utils.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.greencode.modules.app.entity.ShopEntity;
import org.greencode.modules.app.service.ShopService;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;

import javax.servlet.http.HttpServletRequest;


/**
 * 分店管理
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:58:37
 */
@RestController
@RequestMapping("app/shop")
@Api(tags = "分店管理")
public class ShopController {
    @Autowired
    private ShopService shopService;



    /**
     * 列表，没有分页功能
     */
    @GetMapping("/shopList")
    @ApiOperation("列表")
    public R shopList(){
        List<ShopEntity> shopEntities = shopService.shopList();
        return R.ok().put("data", shopEntities);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("通过id来查询信息")
    public R info(@PathVariable("id") Long id){
		ShopEntity shop = shopService.getById(id);

        return R.ok().put("shop", shop);
    }

    /**
     * 保存
     */
//    @PostMapping("/save")
//    @ApiOperation("保存")
//    public R save(@RequestBody ShopEntity shop){
//		shopService.save(shop);
//
//        return R.ok();
//    }



}
