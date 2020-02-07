package org.greencode.modules.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class BossVO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;
    /**
     * 捐赠人id
     */
    private Long userId;
    /**
     * 报名分店
     */
    private String shopName;
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


}
