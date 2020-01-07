package org.greencode.modules.app.dao;

import org.greencode.modules.app.entity.BossEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
     * 通过日期和上午下午来查找是否被申请
     * @param dutyDate
     * @param dutyType
     * @return
     */
    BossEntity selectByDutyDate(Date dutyDate, Integer dutyType);

    /**
     * 查询未来三天内的店长申请
     * @return
     */
    List<BossEntity> findNextThreeDay();

}
