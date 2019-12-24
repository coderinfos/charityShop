/**
 *
 *
 * https://shop.charityShop.org
 *
 * 版权所有，侵权必究！
 */

package org.greencode.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.greencode.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
