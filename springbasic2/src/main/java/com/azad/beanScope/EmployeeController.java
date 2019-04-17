package com.azad.beanScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.security.Provider;

@Controller
public class EmployeeController {
    @Autowired
    private Provider<EmployeeDetails> employeeDetailsProvider;


}
