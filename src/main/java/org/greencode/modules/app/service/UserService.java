package org.greencode.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.entity.UserEntity;

import java.util.Map;

/**
 * 基本用户信息表
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

