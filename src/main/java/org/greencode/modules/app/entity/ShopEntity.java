package org.greencode.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.greencode.common.column.ExcelColumn;

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
	@ExcelColumn(value = "id", col = 1)
	private Long id;
	/**
	 * 
	 */
	@ExcelColumn(value = "店名", col = 2)
	private String shopName;
	/**
	 * 
	 */
	@ExcelColumn(value = "店名", col = 2)
	private String address;
	/**
	 *
	 */
	@ExcelColumn(value = "地址", col = 3)
	private String operator;
	/**
	 *
	 */
	@ExcelColumn(value = "操作时间", col = 4)
	private Date operationTime;
	/**
	 *
	 */
	@ExcelColumn(value = "操作IP", col = 5)
	private String operatorIp;
	/**
	 *
	 */
	@ExcelColumn(value = "备注", col = 6)
	private String memo;

}
