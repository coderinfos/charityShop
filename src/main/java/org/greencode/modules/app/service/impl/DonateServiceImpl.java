package org.greencode.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.Query;

import org.greencode.modules.app.dao.DonateDao;
import org.greencode.modules.app.entity.DonateEntity;
import org.greencode.modules.app.service.DonateService;


@Service("donateService")
public class DonateServiceImpl extends ServiceImpl<DonateDao, DonateEntity> implements DonateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DonateEntity> page = this.page(
                new Query<DonateEntity>().getPage(params),
                new QueryWrapper<DonateEntity>()
        );

        return new PageUtils(page);
    }

}