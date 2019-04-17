package com.coderbd.springbasic;

import com.coderbd.springbasic.autowire.Employee;
import com.coderbd.springbasic.context.xml.Vehicle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringbasicApplication {

    public static void main(String[] args) {


        ConfigurableApplicationContext context1 = SpringApplication.run(SpringbasicApplication.class, args);
        // context1.getBean(com.coderbd.springbasic.ioc.annotation.A.class);
        //context1.getBean("myA");


        ApplicationContext context4 = new ClassPathXmlApplicationContext("beansioc.xml");
        //context4.getBean("aaa");


        //Context Example
        System.out.println("Vehicle Example Started");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Vehicle vehicle = (Vehicle) context.getBean("vehicle");
        vehicle.drive();
        System.out.println("Vehicle Example Started");
        //Autowire
        System.out.println("Autowired Example Started");
        Employee employee1 = context1.getBean(Employee.class);
        employee1.showDetails();
        System.out.println("Autowired Example Ended");

        /////////////////get all beans from ioc container
        String[] allBeanNames = context4.getBeanDefinitionNames();
        for (String beanName : allBeanNames) {
            System.out.println(beanName);
        }


        //////////Beans scope check
        //Signleton scope
        com.coderbd.springbasic.bean.scops.singleton.AA a1 = context1.getBean(com.coderbd.springbasic.bean.scops.singleton.AA.class);
        com.coderbd.springbasic.bean.scops.singleton.AA a2 = context1.getBean(com.coderbd.springbasic.bean.scops.singleton.AA.class);
        //////////prototype scope check

        com.coderbd.springbasic.bean.scops.prototype.BB b1 = context1.getBean(com.coderbd.springbasic.bean.scops.prototype.BB.class);
        com.coderbd.springbasic.bean.scops.prototype.BB b2 = context1.getBean(com.coderbd.springbasic.bean.scops.prototype.BB.class);

    }
}
