package org.greencode.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 分店管理
 * 
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
@Data
@TableName("ba_shop")
public class ShopEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String shopName;
	/**
	 * 
	 */
	private String address;
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
