package org.greencode.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.entity.BossEntity;

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
}

