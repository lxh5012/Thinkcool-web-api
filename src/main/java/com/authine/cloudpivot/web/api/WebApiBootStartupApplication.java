package com.authine.cloudpivot.web.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author longhai
 */
@SpringBootApplication(
        scanBasePackages = {"com.authine.cloudpivot.web.api", "com.authine.cloudpivot.web.sso", "com.authine.cloudpivot.ext"}
)
@EnableSwagger2
@MapperScan(basePackages = "com.authine.cloudpivot.ext")
public class WebApiBootStartupApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WebApiBootStartupApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApiBootStartupApplication.class);
    }
}