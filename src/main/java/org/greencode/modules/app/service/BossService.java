package org.greencode.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.entity.BossEntity;

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
     * 通过日期和上午下午查询是否已经被申请店长
     * @param dutyDate
     * @param dutyType
     * @return
     */
    boolean getAppointment(Date dutyDate, Integer dutyType);

    /**
     * 查询未来三天的店长申请
     * @return
     */
    List<BossEntity> findNextThreeDay();
}

