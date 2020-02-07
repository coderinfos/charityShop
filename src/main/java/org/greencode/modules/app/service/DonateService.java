package org.greencode.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.entity.DonateEntity;
import org.greencode.modules.app.entity.HomeDonateVO;

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
    /**
     * 查询捐物登记列表
     * @param params
     * @return
     */
    PageUtils receivingQueryPage(Map<String, Object> params);

    /**
     * 通过用户id来查询捐赠表，（有捐物登记时间认为是有效捐物）
     * @param
     * @return
     */
    PageUtils getByUserId(Map<String, Object> params);



    /**
     * 查询最近的五条售出记录
     * @return
     */
    List<HomeDonateVO> getRecentFive();

    /**
     * 查询已经提交没有登记的有效捐赠表，两周内的是有效的
     * @param userId
     * @return
     */
    DonateEntity getUnregisteredByUserId(Long userId,Long shopId);

    /**
     * 查询售出列表
     * @param params
     * @return
     */
    PageUtils soldQueryPage(Map<String, Object> params);
}

