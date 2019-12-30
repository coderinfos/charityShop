package org.greencode.modules.app.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.Query;

import org.greencode.modules.app.dao.AdminUserDao;
import org.greencode.modules.app.entity.AdminUserEntity;
import org.greencode.modules.app.service.AdminUserService;


@Service("adminUserService")
public class AdminUserServiceImpl extends ServiceImpl<AdminUserDao, AdminUserEntity> implements AdminUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AdminUserEntity> page = this.page(
                new Query<AdminUserEntity>().getPage(params),
                new QueryWrapper<AdminUserEntity>()
        );

        return new PageUtils(page);
    }

}