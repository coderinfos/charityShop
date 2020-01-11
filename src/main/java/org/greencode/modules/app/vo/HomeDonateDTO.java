/**
 * coderinfo Inc.
 * <p>
 * Copyright (c) 201901-2020 All Rights Reserved.
 */
package org.greencode.modules.app.vo;

import lombok.Data;
import org.greencode.common.utils.DateUtils;
import org.greencode.modules.app.entity.DonateEntity;

/**
 * @author fenggang
 * @version Id: HomeSchedulingDTO.java, v 0.1 2020年01月05日
 * 14:19 fenggang Exp $
 */
@Data
public class HomeDonateDTO {

    private String id;
    private String head;
    private String userId;
    private String shopHead;
    private String username;
    private String price;
    private String date;

    public static HomeDonateDTO getDonateEntity(DonateEntity donate){
        if(donate == null){
            return null;
        }
        HomeDonateDTO donateDTO = new HomeDonateDTO();
        donateDTO.setId(donate.getId().toString());
        donateDTO.setUserId(donate.getUserId().toString());
        donateDTO.setShopHead(donate.getDonateImage());
        donateDTO.setPrice(donate.getDonatePrice().toString());
        donateDTO.setDate(DateUtils.format(donate.getDonateSaleTime(),"yyyy.MM.dd"));
        return donateDTO;
    }
}
