package org.greencode.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.entity.OperationlLogEntity;

import java.util.Map;

/**
 * 操作日志表
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
public interface OperationlLogService extends IService<OperationlLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

