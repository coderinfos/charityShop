package org.greencode.common.task;


import org.greencode.modules.app.dao.BossDao;
import org.greencode.modules.app.dao.DonateDao;
import org.greencode.modules.app.entity.BossEntity;
import org.greencode.modules.app.entity.DonateEntity;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.UserService;
import org.greencode.modules.app.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class ScheduledTask {

    @Autowired
    private DonateDao donateDao;
    @Autowired
    private BossDao bossDao;
    @Autowired
    private WxService wxService;
    @Autowired
    private UserService userService;
   /**
    * 功能描述: 每天12点查询15天仍然未收到货
    * @Param:
    * @Return: void
    * @Author: mango
    * @methodName: sendTeacherEvaluate
    * @Date: 2020/2/11 19:00
    */
    @Scheduled(cron = "0 0 12 * * ?")
    public void findNotReceiveTheGoods () {
        List<DonateEntity> list = donateDao.findNotReceiveTheGoods();
        for(DonateEntity donateEntity:list){
            UserEntity user = userService.getById(donateEntity.getUserId());
            wxService.WxPushNotification(user.getWechatId(),2,null);
        }
    }
    /**
     * 功能描述:每天21：00通知明天有排班的
     * @Param: []
     * @Return: void
     * @Author: mango
     * @methodName: findNotReceiveTheGoods
     * @Date: 2020/2/12 20:20
     */
    @Scheduled(cron = "0 39 20 * * ?")
    public void findBoss () {
        Date date=new Date();
        System.out.println("date"+date);
        List<BossEntity> list = bossDao.tomorrow();
        for(BossEntity bossEntity:list){
            UserEntity user = userService.getById(bossEntity.getUserId());
            wxService.WxPushNotification(user.getWechatId(),3,null);
        }
    }

}
