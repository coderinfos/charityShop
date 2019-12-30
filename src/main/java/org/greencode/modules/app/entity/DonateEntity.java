package org.greencode.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 捐赠表
 * 
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
@Data
@TableName("ba_donate")
public class DonateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 捐赠人id
	 */
	private Long userId;
	/**
	 * 捐赠至分店id
	 */
	private Long shopId;
	/**
	 * 
	 */
	private String donateName;
	/**
	 * 1物品,2书籍
	 */
	private Integer donateType;
	/**
	 * 
	 */
	private String donateImage;
	/**
	 * 
	 */
	private Integer donatePrice;
	/**
	 * 
	 */
	private Date donateSubmitTime;
	/**
	 * 
	 */
	private Date donateRegisterTime;
	/**
	 * 
	 */
	private Date donateSaleTime;
	/**
	 * 
	 */
	private String operator;
	/**
	 * 
	 */
	private Date operationTime;
	/**
	 * 
	 */
	private String operatorIp;
	/**
	 * 
	 */
	private String memo;

}
