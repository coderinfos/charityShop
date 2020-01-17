package org.greencode.modules.app.entity;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HomeBossVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 捐赠人id
     */
    private Long userId;
    /**
     * 值班日期
     */
    private Date dutyDate;
    /**
     * 1早班2晚班
     */
    private Integer dutyType;
    /**
     *头像
     */
    private String face;
    /**
     * face是否公开,1为true,0为false
     */
    private Integer faceOpen;
    /**
     * 店名
     */
    private String shopName;

    private Integer shopId;
}
