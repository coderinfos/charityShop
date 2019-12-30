package org.greencode.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.Query;

import org.greencode.modules.app.dao.ShopDao;
import org.greencode.modules.app.entity.ShopEntity;
import org.greencode.modules.app.service.ShopService;


@Service("shopService")
public class ShopServiceImpl extends ServiceImpl<ShopDao, ShopEntity> implements ShopService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ShopEntity> page = this.page(
                new Query<ShopEntity>().getPage(params),
                new QueryWrapper<ShopEntity>()
        );

        return new PageUtils(page);
    }

}