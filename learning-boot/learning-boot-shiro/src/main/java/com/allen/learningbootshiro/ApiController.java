package com.allen.learningbootshiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chan
 * @date 2020/10/7
 * description: TODO
 */
@Controller
public class ApiController {

    @RequestMapping("/login")
    @ResponseBody
    public String login() {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("chenhuijun", "1234"));
        return "SUCCESS";
    }

    @RequestMapping("/add")
    @ResponseBody
    @RequiresRoles(value = {"admin", "system"}, logical = Logical.OR)
    public String add() {
        return "SUCCESS";
    }

    @RequestMapping("/del")
    @ResponseBody
    @RequiresPermissions({"goods:del"})
    public String del() {
        return "SUCCESS";
    }


}
