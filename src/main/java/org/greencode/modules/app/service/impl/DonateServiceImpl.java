package org.greencode.modules.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private DonateDao donateDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DonateEntity> page = this.page(
                new Query<DonateEntity>().getPage(params),
                new QueryWrapper<DonateEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<DonateEntity> getByUserId(Long userId) {

        return donateDao.selectDonateByUserId(userId);
    }

    @Override
    public List<DonateEntity> getSoldByUserId(Long userId) {
        return donateDao.selectDonateSoldByUserId(userId);
    }

}