/**
 *
 *
 *
 *
 * 版权所有，侵权必究！
 */

package org.greencode.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.greencode.modules.sys.entity.SysUserTokenEntity;

/**
 * 系统用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
