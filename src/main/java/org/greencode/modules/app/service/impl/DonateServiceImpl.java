package org.greencode.modules.app.service.impl;

import org.apache.commons.lang.StringUtils;
import org.greencode.common.utils.R;
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

import static org.greencode.common.constant.ClientConstants.*;
import static org.greencode.common.constant.ClientConstants.NOT_FIND_ERROR_MSG;


@Service("donateService")
public class DonateServiceImpl extends ServiceImpl<DonateDao, DonateEntity> implements DonateService {

    @Autowired
    private DonateDao donateDao;

    @Override
    public PageUtils receivingQueryPage(Map<String, Object> params) {
        String userId = (String)params.get("userId");
        QueryWrapper<DonateEntity> queryWrapper =new QueryWrapper<DonateEntity>();
        queryWrapper.isNotNull("donate_register_time").isNull("donate_sale_time").isNotNull("donate_type").like(StringUtils.isNotBlank(userId),"user_id", userId);

        IPage<DonateEntity> page = this.page(
                new Query<DonateEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils getByUserId(Map<String, Object> params) {
        QueryWrapper<DonateEntity> queryWrapper =new QueryWrapper<DonateEntity>();
        queryWrapper.isNotNull("donate_register_time").isNotNull("donate_type").eq("user_id",params.get("userId")).orderByDesc("donate_register_time");

        IPage<DonateEntity> page = this.page(
                new Query<DonateEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);

    }

    @Override
    public List<DonateEntity> getSoldByUserId(Long userId) {
        return donateDao.selectDonateSoldByUserId(userId);
    }

    @Override
    public List<HomeDonateVO> getRecentFive() {
        List<HomeDonateVO> homeDonateVOS = donateDao.selectRecentFive();
        homeDonateVOS.forEach(homeDonateVO ->{
            if(homeDonateVO.getNickNameOpen()==0){
                homeDonateVO.setNickName(DEFAULT_NAME);
            }
            if(homeDonateVO.getFaceOpen()==0){
                homeDonateVO.setFace(DEFAULT_HEAD);
            }
        });

        return homeDonateVOS;
    }

    @Override
    public DonateEntity getUnregisteredByUserId(Long userId,Long shopId) {
        DonateEntity list = donateDao.selectUnregisteredByUserId(userId,shopId);

        return list;
    }

    @Override
    public PageUtils soldQueryPage(Map<String, Object> params) {
        String userId = (String)params.get("userId");
        QueryWrapper<DonateEntity> queryWrapper =new QueryWrapper<DonateEntity>();
        queryWrapper.isNotNull("donate_sale_time").isNotNull("donate_price").like(StringUtils.isNotBlank(userId),"user_id", userId);

        IPage<DonateEntity> page = this.page(
                new Query<DonateEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}