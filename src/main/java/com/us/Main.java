package com.us;

import com.us.runtest.service.PublishServiceTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Bruce
 * @date 16/9/21
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
        PublishServiceTest bean = applicationContext.getBean(PublishServiceTest.class);
        bean.publishServices();

    }
}
