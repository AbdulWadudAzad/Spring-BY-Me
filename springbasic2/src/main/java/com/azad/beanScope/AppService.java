package com.azad.beanScope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;

@Service
public class AppService {
    @Autowired
    private Provider<EmployeeDetails> employeeDetailsProvider;

    public void findEmployeeSalary() {
        EmployeeDetails employeeDetails = employeeDetailsProvider.get();
        Employee employee = employeeDetails.getE
    }
}
