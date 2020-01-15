package org.greencode.modules.app.service.impl;

import org.greencode.modules.app.entity.HomeDonateVO;
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
    public PageUtils receivingQueryPage(Map<String, Object> params) {
        QueryWrapper<DonateEntity> queryWrapper =new QueryWrapper<DonateEntity>();
        queryWrapper.isNotNull("donate_register_time").isNull("donate_sale_time").isNotNull("donate_type");

        IPage<DonateEntity> page = this.page(
                new Query<DonateEntity>().getPage(params),
                queryWrapper
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

    @Override
    public List<HomeDonateVO> getRecentFive() {
        return donateDao.selectRecentFive();
    }

    @Override
    public List<DonateEntity> getUnregisteredByUserId(Long userId) {
        return donateDao.selectUnregisteredByUserId(userId);
    }

    @Override
    public PageUtils soldQueryPage(Map<String, Object> params) {
        QueryWrapper<DonateEntity> queryWrapper =new QueryWrapper<DonateEntity>();
        queryWrapper.isNotNull("donate_sale_time").isNotNull("donate_price");

        IPage<DonateEntity> page = this.page(
                new Query<DonateEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}