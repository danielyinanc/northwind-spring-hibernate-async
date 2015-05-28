package org.springframework.examples.northwind;

import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.examples.northwind.service.ProductBo;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Properties;
import java.util.concurrent.Executor;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableAsync
public class Application implements AsyncConfigurer {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setThreadNamePrefix("LULExecutor-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    @Bean
    AsyncRestTemplate asyncRestTemplate() {
        return new AsyncRestTemplate();
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder =
                new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("org.springframework.examples.northwind.model")
                .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect",
                "org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }


    @Bean(name = "dataSource")
    public BasicDataSource dataSource() {

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/northwind");
        ds.setUsername("root");
        ds.setPassword("my-secret-pw");
        return ds;
    }
}
