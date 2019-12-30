package org.greencode.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 店长排班表
 * 
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
@Data
@TableName("ba_boss")
public class BossEntity implements Serializable {
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
	 * 报名分店id
	 */
	private Long shopId;
	/**
	 * 值班日期
	 */
	private Date dutyDate;
	/**
	 * 1早班2晚班
	 */
	private Integer dutyType;
	/**
	 * 0为false,1为true审核通过(默认自动
	 */
	private Integer dutyStatus;
	/**
	 * 
	 */
	private Date dutySubmitTime;
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
