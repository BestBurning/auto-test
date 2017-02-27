package com.us;

import com.us.runtest.service.QCloud;
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
        int count = 3;
        for (int i = 0; i < count; i++) {
            QCloud bean = applicationContext.getBean(QCloud.class);
            new Thread(bean).start();
        }

    }
}
