package org.greencode.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.entity.ShopEntity;

import java.util.List;
import java.util.Map;

/**
 * 分店管理
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
public interface ShopService extends IService<ShopEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<ShopEntity> shopList ();
}

