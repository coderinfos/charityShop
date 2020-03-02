package org.greencode.modules.app.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.greencode.common.utils.PageUtils;
import org.greencode.common.utils.Query;

import org.greencode.modules.app.dao.UserDao;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity getByMobilePhone(Long mobilePhone) {
        return userDao.selectByMobilePhone(mobilePhone);
    }

    @Override
    public UserEntity getByWechatId(String wechatId) {
        return userDao.selectByWechatId(wechatId);
    }


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String mobilePhoneOpen = (String)params.get("mobilePhoneOpen");
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>().like(StringUtils.isNotBlank(mobilePhoneOpen),"mobile_phone", mobilePhoneOpen)
        );

        return new PageUtils(page);
    }

    @Override
    public UserEntity openUser(UserEntity userEntity) {
        UserEntity user = new UserEntity();
        if(userEntity.getFaceOpen()==1){
            user.setFace(userEntity.getFace());
        }else if(userEntity.getFaceOpen()==0){
            user.setFace("https://shop.bbalt.org/charityShop/img/default.jpg");
        }
        if(userEntity.getNickNameOpen()==1){
            user.setNickName(userEntity.getNickName());
        }
        if(userEntity.getRealNameOpen()==1){
            user.setRealName(userEntity.getRealName());
        }
        if(userEntity.getSexOpen()==1){
            user.setSex(userEntity.getSex());
        }
        if(userEntity.getAgeOpen()==1){
            user.setAge(userEntity.getAge());
        }
        if(userEntity.getMobilePhoneOpen()==1){
            user.setMobilePhone(userEntity.getMobilePhone());
        }
        if(userEntity.getSignatureLineOpen()==1){
            user.setSignatureLine(userEntity.getSignatureLine());
        }
        return user;
    }

}