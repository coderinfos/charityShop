/**
 *
 *
 * https://shop.charityShop.org
 *
 * 版权所有，侵权必究！
 */

package org.greencode.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.greencode.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * @author
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
