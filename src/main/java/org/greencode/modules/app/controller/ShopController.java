package org.greencode.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.greencode.modules.app.entity.ShopEntity;
import org.greencode.modules.app.service.ShopService;

/**
 * 
 *
 * @author connie
 * @email connie1451@163.com
 * @date 2019-12-23 16:07:51
 */
@RestController
@RequestMapping("app/shop")
@Api(tags = "分店管理")
public class ShopController {
    @Autowired
    private ShopService shopService;

    /**
     * 列表
     */
    @ApiOperation("查询分店列表")
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = shopService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @ApiOperation("修改分店")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
			ShopEntity shop = shopService.getById(id);

        return R.ok().put("shop", shop);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("添加分店")
    public R save(@RequestBody ShopEntity shop){
			shopService.save(shop);

        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation("根据id修改分店信息")
    @PostMapping("/update")
    public R update(@RequestBody ShopEntity shop){
			shopService.updateById(shop);

        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("根据ids删除分店")
    @DeleteMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
			shopService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
