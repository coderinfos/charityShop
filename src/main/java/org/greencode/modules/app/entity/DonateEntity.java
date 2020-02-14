package org.greencode.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.greencode.common.column.ExcelColumn;

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
	@ExcelColumn(value = "id", col = 1)
	private Long id;
	/**
	 * 捐赠人id
	 */
	@ExcelColumn(value = "捐赠人id", col = 2)
	private Long userId;
	/**
	 * 捐赠至分店id
	 */
	@ExcelColumn(value = "分店id", col = 3)
	private Long shopId;
	/**
	 * 
	 */
	@ExcelColumn(value = "物品名称", col = 4)
	private String donateName;
	/**
	 * 1物品,2书籍
	 */
	@ExcelColumn(value = "1物品,2书籍", col = 5)
	private Integer donateType;
	/**
	 * 
	 */
	@ExcelColumn(value = "物品图片", col = 6)
	private String donateImage;
	/**
	 * 
	 */
	@ExcelColumn(value = "价格", col = 7)
	private Integer donatePrice;
	/**
	 * 
	 */
	@ExcelColumn(value = "提交寄货时间", col = 8)
	private Date donateSubmitTime;
	/**
	 * 
	 */
	@ExcelColumn(value = "登记时间", col = 9)
	private Date donateRegisterTime;
	/**
	 * 
	 */
	@ExcelColumn(value = "售出时间", col = 10)
	private Date donateSaleTime;
	/**
	 *
	 */
	@ExcelColumn(value = "操作时间", col = 11)
	private Date operationTime;
	/**
	 *
	 */
	@ExcelColumn(value = "操作IP", col = 12)
	private String operatorIp;
	/**
	 *
	 */
	@ExcelColumn(value = "备注", col = 13)
	private String memo;

}
