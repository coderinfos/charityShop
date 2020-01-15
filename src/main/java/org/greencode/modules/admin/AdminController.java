package org.greencode.modules.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理系统 页面跳转控制器
 */
@Controller
@RequestMapping()
public class AdminController {

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }



    @GetMapping("/boss")
    public String boss() {
        return "modules/app/boss";
    }


}
