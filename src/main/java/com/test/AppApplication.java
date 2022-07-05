package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.test.**"})
public class AppApplication {

    public static void main(String[] args) {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(AppApplication.class);
        SpringApplication app = builder.application();
        //添加监听器
        addEventListeners(app);
        app.run();

    }


    /**
     * 加载完成后
     *
     * @param springApplication
     */
    private static final void addEventListeners(SpringApplication springApplication) {
        // springApplication.addListeners(new ApplicationReadyEventListener());

    }

}
