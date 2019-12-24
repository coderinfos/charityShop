package org.greencode.modules.app.entity;

//import com.baomidou.mybatisplus.annotations.TableId;
//import com.baomidou.mybatisplus.annotations.TableName;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author connie
 * @email connie1451@163.com
 * @date 2019-12-23 16:07:51
 */
@Data
@TableName("tb_shop")
public class ShopEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分店编号
	 */
	@TableId
	private Integer id;
	/**
	 * 分店名称
	 */
	private String shopname;
	/**
	 * 分店地址
	 */
	private String address;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 操作时间
	 */
	@ApiModelProperty(required = true,example = "2019-12-12 12:00:00 ")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(
			pattern = "yyyy-MM-dd HH:mm:ss"
	)
	private Date operationtime;
	/**
	 * 操作IP
	 */
	private String operatorip;
	/**
	 * 备注
	 */
	private String memo;

}
