package org.greencode.modules.app.service.impl;

import org.greencode.modules.app.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private BossDao bossDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BossEntity> page = this.page(
                new Query<BossEntity>().getPage(params),
                new QueryWrapper<BossEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean getAppointment(Date dutyDate, Integer dutyType) {
        BossEntity bossEntity = bossDao.selectByDutyDate(dutyDate, dutyType);
        if(bossEntity==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<BossEntity> findNextThreeDay() {
        return bossDao.findNextThreeDay();
    }

}