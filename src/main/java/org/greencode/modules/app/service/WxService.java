package org.greencode.modules.app.service;

import org.greencode.common.utils.R;

/**
 * @Author mango
 * @create 2020/2/11 19:50
 */
public interface WxService {
    public R WxPushNotification(String openid, Integer type,Integer price);

    public String getAccessToken();
}
