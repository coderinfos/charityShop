package org.greencode.modules.app.dao;

import org.greencode.modules.app.entity.AdminUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员账号表
 * 
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
@Mapper
public interface AdminUserDao extends BaseMapper<AdminUserEntity> {
	
}
