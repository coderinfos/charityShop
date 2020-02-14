package org.greencode.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.greencode.common.column.ExcelColumn;

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
	@ExcelColumn(value = "id", col = 1)
	private Long id;
	/**
	 * 捐赠人id
	 */
	@ExcelColumn(value = "捐赠人id", col = 2)
	private Long userId;
	/**
	 * 报名分店id
	 */
	@ExcelColumn(value = "报名分店id", col = 3)
	private Long shopId;
	/**
	 * 值班日期
	 */
	@ExcelColumn(value = "值班日期", col = 4)
	private Date dutyDate;
	/**
	 * 1早班2晚班
	 */
	@ExcelColumn(value = "1早班2晚班", col = 5)
	private Integer dutyType;
	/**
	 * 0为false,1为true审核通过(默认自动
	 */
	@ExcelColumn(value = "0为false,1为true审核通过(默认自动)", col = 6)
	private Integer dutyStatus;
	/**
	 * 
	 */
	@ExcelColumn(value = "提交时间", col = 7)
	private Date dutySubmitTime;
	/**
	 * 
	 */
	@ExcelColumn(value = "操作员", col = 8)
	private String operator;
	/**
	 * 
	 */
	@ExcelColumn(value = "操作时间", col = 9)
	private Date operationTime;
	/**
	 * 
	 */
	@ExcelColumn(value = "操作IP", col = 10)
	private String operatorIp;
	/**
	 * 
	 */
	@ExcelColumn(value = "备注", col = 11)
	private String memo;

}
