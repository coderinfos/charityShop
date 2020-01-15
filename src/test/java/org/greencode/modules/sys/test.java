package org.greencode.modules.sys;

import com.google.code.kaptcha.Producer;
import org.apache.commons.lang.RandomStringUtils;
import org.greencode.common.utils.R;
import org.greencode.modules.sys.entity.SysUserEntity;
import org.greencode.modules.sys.service.SysUserService;
import org.greencode.modules.sys.shiro.ShiroUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    private SysUserService sysUserService;
    @Test
    public void register(){
        String username = "lhn";
        String password = "123456";
        SysUserEntity user = new SysUserEntity();
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setUsername(username);
        user.setSalt(salt);
        user.setPassword(ShiroUtils.sha256(password, user.getSalt()));
        sysUserService.save(user);

    }
}
