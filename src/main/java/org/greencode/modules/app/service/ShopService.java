package org.greencode.modules.app.service;




import com.baomidou.mybatisplus.extension.service.IService;
//import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.greencode.common.utils.PageUtils;
import org.greencode.modules.app.dao.ShopDao;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.form.LoginForm;
import org.greencode.modules.app.entity.ShopEntity;

import java.util.Map;

/**
 * 
 *
 * @author connie
 * @email connie1451@163.com
 * @date 2019-12-23 16:07:51
 *
 */
public interface ShopService  extends IService<ShopEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

