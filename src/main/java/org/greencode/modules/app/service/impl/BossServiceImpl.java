package org.greencode.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.Query;

import org.greencode.modules.app.dao.BossDao;
import org.greencode.modules.app.entity.BossEntity;
import org.greencode.modules.app.service.BossService;


@Service("bossService")
public class BossServiceImpl extends ServiceImpl<BossDao, BossEntity> implements BossService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BossEntity> page = this.page(
                new Query<BossEntity>().getPage(params),
                new QueryWrapper<BossEntity>()
        );

        return new PageUtils(page);
    }

}