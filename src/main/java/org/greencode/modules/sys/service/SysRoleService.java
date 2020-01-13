/**
 *
 *
 *
 *
 * 版权所有，侵权必究！
 */

package org.greencode.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.sys.entity.SysRoleEntity;

import java.util.Map;


/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysRoleService extends IService<SysRoleEntity> {

	PageUtils queryPage(Map<String, Object> params);

	void saveRole(SysRoleEntity role);

	void update(SysRoleEntity role);
	
	void deleteBatch(Long[] roleIds);

}
