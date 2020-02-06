package org.greencode.modules.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DonateVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String shopName;
    private String donateName;
    private Integer donateType;
    private Date donateRegisterTime;
    private Date donateSaleTime;
    private Integer donatePrice;

}
