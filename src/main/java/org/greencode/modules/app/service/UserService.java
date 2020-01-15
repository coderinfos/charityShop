package org.greencode.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.entity.UserEntity;

import java.util.Date;
import java.util.Map;

/**
 * 基本用户信息表
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 通过手机号码来查询用户
     * @param mobilePhone
     * @return
     */
    UserEntity getByMobilePhone(Long mobilePhone);

    /**
     * 通过微信id来查询用户
     * @param wechatId
     * @return
     */
    UserEntity getByWechatId(String wechatId);

    PageUtils queryPage(Map<String, Object> params);
}

