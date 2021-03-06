package org.spring.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.spring.springboot.controller.Storage;
import org.spring.springboot.controller.StoragePro;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Boot 应用启动类
 *
 * Created by bysocket on 16/4/26.
 */
// Spring Boot 应用的标识
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("org.spring.springboot.dao")
@EnableConfigurationProperties(StoragePro.class)
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class,args);
    }
    @Bean

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry) {

                corsRegistry.addMapping("/*").allowedOrigins("*").allowedMethods("POST", "GET", "OPTIONS", "PUT", "DELETE");
            }

//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new TokenInterceptor());
//            }
        };
    }


    @Bean
    CommandLineRunner init(Storage storage) {
        return (args) -> {
            storage.init();
        };
    }
}
