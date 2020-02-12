package org.greencode.modules.app.entity.wx;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;


@Data
public class MpTemplateMsgVo {
    @ApiModelProperty(value = "公众号appid，要求与小程序有绑定且同主体")
    private String appid;
    @ApiModelProperty(value = "公众号模板id")
    private String template_id;
    @ApiModelProperty(value = "公众号模板消息所要跳转的url")
    private String url;
    @ApiModelProperty(value = "公众号模板消息所要跳转的小程序，小程序的必须与公众号具有绑定关系(appid,pagepath)")
    private Map<String,String> miniprogram;
    @ApiModelProperty(value = "公众号模板消息的数据")
    private Map<String, TemplateData> data;
}
