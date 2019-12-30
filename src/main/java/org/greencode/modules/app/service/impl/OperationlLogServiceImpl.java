package org.greencode.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.Query;

import org.greencode.modules.app.dao.OperationlLogDao;
import org.greencode.modules.app.entity.OperationlLogEntity;
import org.greencode.modules.app.service.OperationlLogService;


@Service("operationlLogService")
public class OperationlLogServiceImpl extends ServiceImpl<OperationlLogDao, OperationlLogEntity> implements OperationlLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OperationlLogEntity> page = this.page(
                new Query<OperationlLogEntity>().getPage(params),
                new QueryWrapper<OperationlLogEntity>()
        );

        return new PageUtils(page);
    }

}