package org.greencode.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 基本用户信息表
 * 
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
@Data
@TableName("ba_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Long id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String passWord;
	/**
	 * 
	 */
	private String face;
	/**
	 * face是否公开,1为true,0为false
	 */
	private Integer faceOpen;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 昵称是否公开,1为true,0为false
	 */
	private Integer nickNameOpen;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 真实姓名是否公开,1为true,0为false
	 */
	private Integer realNameOpen;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 性别是否公开 ,1为true,0为false
	 */
	private Integer sexOpen;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 年龄是否公开 ,1为true,0为false
	 */
	private Integer ageOpen;
	/**
	 * 电话号
	 */
	private Long mobilePhone;
	/**
	 * 电话号是否公开,1为true,0为false
	 */
	private Integer mobilePhoneOpen;
	/**
	 *  签名,限制字数120字以内
	 */
	private String signatureLine;
	/**
	 * 签名是否公开,1为true,0为false
	 */
	private Integer signatureLineOpen;
	/**
	 * 微信id
	 */
	private String wechatId;
	/**
	 * 验证码
	 */
	private Integer verificationCode;
	/**
	 * 注册时间
	 */
	private Date regTime;
	/**
	 * 上一次登录时间
	 */
	private Date preLoginTime;
	/**
	 * 上一次登录地址ip
	 */
	private String preLoginIp;
	/**
	 * 最后一次登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 最后一次登录地址ip
	 */
	private String lastLoginIp;
	/**
	 * 备忘录
	 */
	private String memo;

}
