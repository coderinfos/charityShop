package org.greencode.modules.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HomeDonateVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 昵称是否公开,1为true,0为false
     */
    private Integer nickNameOpen;
    /**
     *头像
     */
    private String face;
    /**
     * face是否公开,1为true,0为false
     */
    private Integer faceOpen;
    /**
     * 捐赠人id
     */
    private Long userId;
    /**
     * 1物品,2书籍
     */
    private Integer donateType;
    /**
     *售价
     */
    private Integer donatePrice;

    /**
     *售出时间
     */
    private Date donateSaleTime;
}
