package org.greencode.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.greencode.common.column.ExcelColumn;

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
	@ExcelColumn(value = "用户id", col = 1)
	private Long id;
	/**
	 * 用户名
	 */
	@ExcelColumn(value = "用户名", col = 2)
	private String userName;
	/**
	 * 用户密码
	 */
	@ExcelColumn(value = "用户密码", col = 3)
	private String passWord;
	/**
	 * 
	 */
	@ExcelColumn(value = "头像", col = 4)
	private String face;
	/**
	 * face是否公开,1为true,0为false
	 */
	@ExcelColumn(value = "face是否公开,1为true,0为false", col = 5)
	private Integer faceOpen;
	/**
	 * 昵称
	 */
	@ExcelColumn(value = "昵称", col = 6)
	private String nickName;
	/**
	 * 昵称是否公开,1为true,0为false
	 */
	@ExcelColumn(value = "昵称是否公开,1为true,0为false", col = 7)
	private Integer nickNameOpen;
	/**
	 * 真实姓名
	 */
	@ExcelColumn(value = "真实姓名", col = 8)
	private String realName;
	/**
	 * 真实姓名是否公开,1为true,0为false
	 */
	@ExcelColumn(value = "真实姓名是否公开,1为true,0为false", col = 9)
	private Integer realNameOpen;
	/**
	 * 性别
	 */
	@ExcelColumn(value = "性别", col = 10)
	private Integer sex;
	/**
	 * 性别是否公开 ,1为true,0为false
	 */
	@ExcelColumn(value = "性别是否公开 ,1为true,0为false", col = 11)
	private Integer sexOpen;
	/**
	 * 年龄
	 */
	@ExcelColumn(value = "年龄", col = 12)
	private Integer age;
	/**
	 * 年龄是否公开 ,1为true,0为false
	 */
	@ExcelColumn(value = "年龄是否公开 ,1为true,0为false", col = 13)
	private Integer ageOpen;
	/**
	 * 电话号
	 */
	@ExcelColumn(value = "电话号", col = 14)
	private Long mobilePhone;
	/**
	 * 电话号是否公开,1为true,0为false
	 */
	@ExcelColumn(value = "电话号是否公开,1为true,0为false", col = 15)
	private Integer mobilePhoneOpen;
	/**
	 *  签名,限制字数120字以内
	 */
	@ExcelColumn(value = "签名,限制字数120字以内", col = 16)
	private String signatureLine;
	/**
	 * 签名是否公开,1为true,0为false
	 */
	@ExcelColumn(value = "签名是否公开,1为true,0为false", col = 17)
	private Integer signatureLineOpen;
	/**
	 * 微信id
	 */
	@ExcelColumn(value = "微信id", col = 18)
	private String wechatId;
	/**
	 * 验证码
	 */
	private Integer verificationCode;
	/**
	 * 注册时间
	 */
	@ExcelColumn(value = "注册时间", col = 19)
	private Date regTime;
	/**
	 * 上一次登录时间
	 */
	@ExcelColumn(value = "上一次登录时间", col = 20)
	private Date preLoginTime;
	/**
	 * 上一次登录地址ip
	 */
	@ExcelColumn(value = "上一次登录地址ip", col = 21)
	private String preLoginIp;
	/**
	 * 最后一次登录时间
	 */
	@ExcelColumn(value = "最后一次登录时间", col = 22)
	private Date lastLoginTime;
	/**
	 * 最后一次登录地址ip
	 */
	@ExcelColumn(value = "最后一次登录地址ip", col = 23)
	private String lastLoginIp;
	/**
	 * 备忘录
	 */
	@ExcelColumn(value = "备忘录", col = 24)
	private String memo;

}
