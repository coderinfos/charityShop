package org.greencode.modules.app.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.Query;

import org.greencode.modules.app.dao.UserDao;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity getByMobilePhone(Long mobilePhone) {
        return userDao.selectByMobilePhone(mobilePhone);
    }

    @Override
    public UserEntity getByWechatId(String wechatId) {
        return userDao.selectByWechatId(wechatId);
    }


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String mobilePhoneOpen = (String)params.get("mobilePhoneOpen");
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>().like(StringUtils.isNotBlank(mobilePhoneOpen),"mobile_phone", mobilePhoneOpen)
        );

        return new PageUtils(page);
    }

}