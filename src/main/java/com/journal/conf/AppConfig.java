package com.journal.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by JLancaster on 3/23/2017.
 */
@EnableWebMvc
@Configuration
@Import({WebSecurityConfig.class})
public class AppConfig {

    @Bean(name="dataSource")
    public DriverManagerDataSource dataSource() {
        Properties props = getDataSourceProperties();
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(props.getProperty("spring.datasource.driver-class-name"));
        driverManagerDataSource.setUrl(props.getProperty("spring.datasource.url"));
        driverManagerDataSource.setUsername(props.getProperty("spring.datasource.username"));
        driverManagerDataSource.setPassword(props.getProperty("spring.datasource.password"));

        return driverManagerDataSource;
    }

    private Properties getDataSourceProperties() {
        Properties properties = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("application.properties");
            properties.load(stream);
        } catch (Exception e) {
            System.out.println("Error while loading database properties.");
        }

        return properties;
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/resources/templates");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }
}
