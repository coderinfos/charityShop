package org.greencode.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.entity.DonateEntity;

import java.util.List;
import java.util.Map;

/**
 * 捐赠表
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
public interface DonateService extends IService<DonateEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 通过用户id来查询捐赠表，（有捐物登记时间认为是有效捐物）
     * @param userId
     * @return
     */
    List<DonateEntity> getByUserId(Long userId);

    /**
     * 通过用户ID来查询已经售出的捐赠记录,通过donatePrice这个字段判断是否售出，未售出为null
     * @param userId
     * @return
     */
    List<DonateEntity> getSoldByUserId(Long userId);
}

