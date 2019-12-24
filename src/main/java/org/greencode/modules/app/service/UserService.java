/**
 *
 *
 * https://shop.charityShop.org
 *
 * 版权所有，侵权必究！
 */

package org.greencode.modules.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.form.LoginForm;

/**
 * 用户
 *
 * @author
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回用户ID
	 */
	long login(LoginForm form);
}
