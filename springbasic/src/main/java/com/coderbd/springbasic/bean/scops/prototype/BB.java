package com.coderbd.springbasic.bean.scops.prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class BB {
    public BB() {
        System.out.println("Prototype Object created");
    }
}
