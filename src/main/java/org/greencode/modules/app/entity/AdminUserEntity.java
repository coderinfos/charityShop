package org.greencode.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 管理员账号表
 * 
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
@Data
@TableName("ba_admin_user")
public class AdminUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String passWord;
	/**
	 * 级别
	 */
	private Integer level;
	/**
	 * 微信id
	 */
	private String wechatId;
	/**
	 * 用户id 默认0
	 */
	private Long userId;
	/**
	 * 默认为0为false,1为true
	 */
	private Integer bossModel;
	/**
	 * 上一次登录时间
	 */
	private Date preLoginTime;
	/**
	 * 上一次登录IP
	 */
	private String preLoginIp;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 最后一次登录IP
	 */
	private String lastLoginIp;
	/**
	 * 验证码
	 */
	private Integer verificationCode;
	/**
	 * 备注
	 */
	private String memo;

}
