package org.greencode.modules.app.dao;

import org.greencode.modules.app.entity.DonateEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 捐赠表
 * 
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
@Mapper
public interface DonateDao extends BaseMapper<DonateEntity> {
    /**
     * 通过用户id来查询捐赠表，（有捐物登记时间认为是有效捐物）
     * @param userId
     * @return
     */
    List<DonateEntity> selectDonateByUserId(Long userId);
    /**
     * 通过用户ID来查询已经售出的捐赠记录,通过donatePrice这个字段判断是否售出，未售出为null
     * @param userId
     * @return
     */
    List<DonateEntity> selectDonateSoldByUserId(Long userId);
}
