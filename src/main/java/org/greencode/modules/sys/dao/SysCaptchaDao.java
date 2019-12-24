/**
 *
 *
 * https://shop.charityShop.org
 *
 * 版权所有，侵权必究！
 */

package org.greencode.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.greencode.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
