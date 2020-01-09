package org.greencode.modules.app.service.impl;

import org.greencode.common.utils.R;
import org.greencode.modules.app.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    @Override
    public List<BossEntity> getByUserId(Long userId) {
        return bossDao.selectByUserId(userId);
    }

    @Override
    public List<BossEntity> getNotStart(Long userId) {
        return bossDao.selectNotStart(userId);
    }

    @Override
    public List<BossEntity> completed(Long userId) {
        //查找当天的并判断是否完成
        BossEntity bossEntity = bossDao.selectTheDay(userId);
        //查找以前已经完成的
        List<BossEntity> bossEntities = bossDao.selectCompleted(userId);
        if(bossEntity!=null){
            //拿到预约上午还是下午
            Integer dutyType = bossEntity.getDutyType();
            //拿到预约当天的日期
            Date dutyDate = bossEntity.getDutyDate();
            long longDate = System.currentTimeMillis();
            Date start = new Date(longDate);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            System.out.println("显示输入的日期:" + dutyDate); //显示输入的日期
            Calendar cal = Calendar.getInstance();
            cal.setTime(dutyDate);
            Date time=null;
            if(dutyType==1){
                cal.add(Calendar.HOUR, 6);// 24小时制
                time = cal.getTime();

                System.out.println("显示更新后的日期:" + format.format(dutyDate));  //显示更新后的日期
            }else if(dutyType==2){
                cal.add(Calendar.HOUR, 5);// 24小时制
                cal.add(Calendar.MINUTE, 30);// 24小时制
                time = cal.getTime();
                System.out.println("显示更新后的日期:" + format.format(dutyDate));  //显示更新后的日期
            }
            long cha = start.getTime() - time.getTime();
            if(cha>0){
                bossEntities.add(bossEntity);
            }
        }
        return bossEntities;
    }

    @Override
    public List<BossEntity> getCancelBoss(Long userId) {
        return bossDao.selectCancelBoss(userId);
    }

}