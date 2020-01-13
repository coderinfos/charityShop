package org.greencode.modules.app.dao;

import org.greencode.modules.app.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * 基本用户信息表
 * 
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
    /**
     * 通过电话来查询用户
     * @param mobilePhone
     * @return
     */
    UserEntity selectByMobilePhone(Long mobilePhone);

    /**
     * 通过微信id来查询用户
     * @param wechatId
     * @return
     */
    UserEntity selectByWechatId(String wechatId);
}
