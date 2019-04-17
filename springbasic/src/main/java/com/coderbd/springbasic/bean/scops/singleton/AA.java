package com.coderbd.springbasic.bean.scops.singleton;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
public class AA {
    public AA() {
        System.out.println("Singleton object created");
    }
}
