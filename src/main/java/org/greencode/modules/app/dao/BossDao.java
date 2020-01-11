package org.greencode.modules.app.dao;

import org.greencode.modules.app.entity.BossEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.greencode.modules.app.entity.HomeBossVO;

import java.util.Date;
import java.util.List;

/**
 * 店长排班表
 * 
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
@Mapper
public interface BossDao extends BaseMapper<BossEntity> {
    /**
     * 通过日期和上午下午,及分店id来查找是否被申请
     * @param dutyDate
     * @param dutyType
     * @return
     */
    List<BossEntity> selectByDutyDate(Date dutyDate, Integer dutyType,Long shopId);

    /**
     * 查询未来三天内的店长申请
     * @return
     */
    List<BossEntity> findNextThreeDay();
    /**
     * 通过用户id来查询排班表
     * @param userId
     * @return
     */
    List<BossEntity> selectByUserId(Long userId);
    /**
     * 通过用户id来查询排班表，筛选未开始且未取消的排班
     * @param userId
     * @return
     */
    List<BossEntity> selectNotStart(Long userId);
    /**
     * 通过用户id来查询排班表，筛选已经完成且未取消的排班
     * @param userId
     * @return
     */
    List<BossEntity> selectCompleted(Long userId);

    /**
     * 查询当天排班
     */
    BossEntity selectTheDay (Long userId);
    /**
     * 通过用户id来查询已经取消排班表
     * @param userId
     * @return
     */
    List<BossEntity> selectCancelBoss(Long userId);
    /**
     * 查询当月的店长排班
     * @return
     */
    List<HomeBossVO> selectTheMonthBoss();
}
