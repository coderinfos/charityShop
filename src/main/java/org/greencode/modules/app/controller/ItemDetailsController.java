package org.greencode.modules.app.controller;

import io.swagger.annotations.ApiOperation;
import org.greencode.modules.app.entity.DonateEntity;
import org.greencode.modules.app.entity.UserEntity;
import org.greencode.modules.app.service.DonateService;
import org.greencode.modules.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemDetailsController {

    @Autowired
    private DonateService donateService;
    @Autowired
    private UserService userService;

    @GetMapping("/item/{id}.html")
    @ApiOperation("通过捐物id，来查找用户信息")
    public String item(@PathVariable("id") Long id, Model model){
        DonateEntity donateEntity = donateService.getById(id);
        UserEntity userEntity = userService.getById(donateEntity.getUserId());
        UserEntity user = userService.openUser(userEntity);
        Integer sex = user.getSex();
        String sexx="";
        if(sex!=null){
            //性别0未知1男2女
            if(sex==1){
                sexx="男";
            }else if(sex==2){
                sexx="女";
            }else{
                sexx="未知";
            }
        }
        model.addAttribute("sexx",sexx);
        model.addAttribute("user",user);
        return "item";
    }
}
