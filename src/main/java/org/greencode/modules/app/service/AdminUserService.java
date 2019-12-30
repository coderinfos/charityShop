package org.greencode.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.entity.AdminUserEntity;

import java.util.Map;

/**
 * 管理员账号表
 *
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
public interface AdminUserService extends IService<AdminUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

