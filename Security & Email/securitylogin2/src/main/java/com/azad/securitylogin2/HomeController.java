package com.azad.securitylogin2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value ={ "/","/login"})
    public String loginView(){
        return "login";
    }
    @GetMapping(value ="/public/r")
    public String regiView(){
        return "public/regi";
    }
    @GetMapping(value ="/admin/h")
    public String homeView(){
        return "admin/home";
    }
    @GetMapping(value ="/secure/s")
    public String serviceView(){
        return "secure/service";
    }
    @GetMapping(value ="/user/u")
    public String userView(){
        return "user/user";
    }

}
