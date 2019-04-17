package com.azad.templatequickjob.imagoeptimizer;

import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyGobalControllerAdvice {
@Autowired
    private UserRepo userRepo;

@ModelAttribute("user")
private User getUserDetails(){
    Authentication auth= SecurityContextHolder.getContext().getAuthentication();
    User user=this.userRepo.findByUserName(auth.getName());
    return user;
}
}
