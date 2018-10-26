package com.wim.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiyuxuan
 */
@SpringBootApplication(scanBasePackages = {"com.wim.quartz","com.wim.component"})
public class JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }

}
