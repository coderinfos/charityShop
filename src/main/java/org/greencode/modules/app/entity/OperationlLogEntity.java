package org.greencode.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 操作日志表
 * 
 * @author 
 * @email 
 * @date 2019-12-30 09:41:22
 */
@Data
@TableName("ba_operationl_log")
public class OperationlLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Integer operator;
	/**
	 * 
	 */
	private String operationType;
	/**
	 * 
	 */
	private String content;
	/**
	 * 捐赠人Id
	 */
	private Long userId;
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
