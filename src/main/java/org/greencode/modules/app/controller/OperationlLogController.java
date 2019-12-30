package org.greencode.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.greencode.modules.app.entity.OperationlLogEntity;
import org.greencode.modules.app.service.OperationlLogService;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.R;



/**
 * 操作日志表
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:58:37
 */
@RestController
@RequestMapping("app/operationllog")
@Api(tags = "操作日志表")
public class OperationlLogController {
    @Autowired
    private OperationlLogService operationlLogService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = operationlLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("列表")
    public R info(@PathVariable("id") Long id){
		OperationlLogEntity operationlLog = operationlLogService.getById(id);

        return R.ok().put("operationlLog", operationlLog);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存")
    public R save(@RequestBody OperationlLogEntity operationlLog){
		operationlLogService.save(operationlLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public R update(@RequestBody OperationlLogEntity operationlLog){
		operationlLogService.updateById(operationlLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
        public R delete(@RequestBody Long[] ids){
		operationlLogService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
