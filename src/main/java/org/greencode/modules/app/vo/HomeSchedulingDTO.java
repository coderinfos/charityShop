/**
 * coderinfo Inc.
 * <p>
 * Copyright (c) 201901-2020 All Rights Reserved.
 */
package org.greencode.modules.app.vo;

import lombok.Data;
import org.greencode.common.utils.DateUtils;
import org.greencode.modules.app.entity.BossEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author fenggang
 * @version Id: HomeSchedulingDTO.java, v 0.1 2020年01月05日
 * 14:19 fenggang Exp $
 */
@Data
public class HomeSchedulingDTO {

    private String upId;
    private String downId;
    private String upUserId;
    private String upHead;
    private String upUsername;
    private String downUserId;
    private String downHead;
    private String downUsername;
    private String date;

    public static HomeSchedulingDTO getBossEntity(BossEntity boss) {
        if (boss == null) {
            return null;
        }
        HomeSchedulingDTO schedulingDTO = new HomeSchedulingDTO();

        schedulingDTO.setDate(DateUtils.format(boss.getDutyDate(), "MM月dd日")+" "+DateUtils.format(boss.getDutyDate(), "E"));
        if (Objects.equals(boss.getDutyType(), 1)) {
            schedulingDTO.setUpId(boss.getId().toString());
            schedulingDTO.setUpUserId(boss.getUserId().toString());
        }
        if (Objects.equals(boss.getDutyType(), 2)) {
            schedulingDTO.setDownId(boss.getId().toString());
            schedulingDTO.setDownUserId(boss.getUserId().toString());
        }

        return schedulingDTO;
    }
}
