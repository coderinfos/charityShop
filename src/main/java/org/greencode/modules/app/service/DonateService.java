package org.greencode.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.entity.DonateEntity;

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
}

