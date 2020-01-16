package org.greencode.modules.app.controller;

import java.util.Arrays;
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
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = shopService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("列表")
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
    @PostMapping("/save")
    @ApiOperation("保存")
    public R save(HttpServletRequest request){
        ShopEntity shop =  (ShopEntity) request.getSession().getAttribute("SysUserEntity");
        String ipAddr = IPUtils.getIpAddr(request);
        shop.setOperatorIp(ipAddr);
        shopService.save(shop);

        return R.ok();
    }
    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public R update(@RequestBody ShopEntity shop){
		shopService.updateById(shop);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
        public R delete(@RequestBody Long[] ids){
		shopService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
