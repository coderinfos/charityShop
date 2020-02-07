package org.greencode.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.entity.BossEntity;
import org.greencode.modules.app.entity.HomeBossVO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 店长排班表
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
public interface BossService extends IService<BossEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询该用户的今日是否排班
     * @param userId
     * @return
     */
    List<BossEntity> theDay(Long userId);
    /**
     * 通过日期和上午下午及分店id，查询是否已经被申请店长
     * @param dutyDate
     * @param dutyType
     * @return
     */
    boolean getAppointment(Date dutyDate, Integer dutyType,Long shopId);

    /**
     * 查询未来三天的店长申请
     * @return
     */
    List<BossEntity> findNextThreeDay();
    /**
     * 通过用户id来查询排班表
     * @param
     * @return
     */
    PageUtils getByUserId(Map<String, Object> params);


    /**
     * 通过用户id来查询排班表，筛选已经完成且未取消的排班
     * @param userId
     * @return
     */
    List<BossEntity> completed(Long userId);


    /**
     * 查询当月的店长排班
     * @return
     */
    List<HomeBossVO> findtheMonthBoss();

/**
 * 功能描述: 通过id来查询排班
 * @Param: [userId]
 * @Return: java.util.List<org.greencode.modules.app.entity.BossEntity>
 * @Author: mango
 * @methodName: findBossByUserId
 * @Date: 2020/2/7 18:30
 */
    List<BossEntity> findBossByUserId(Long userId);
    /**
     * 查询该用户的今日是否排班(此时此刻)
     * @param userId
     * @return
     */
    BossEntity newTheDay(Long userId);
}

