package org.greencode.modules.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DonateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long shopId;

    private Integer number;

    private Long mobilePhone;

    private Date donateRegisterTime;

    private Integer donateType;
}
