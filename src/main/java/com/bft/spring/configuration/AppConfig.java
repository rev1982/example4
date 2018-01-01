package com.bft.spring.configuration;

import com.bft.spring.ui.BaseView;
import com.bft.spring.ui.CompanyView;
import com.bft.spring.ui.TimeZoneView;
import com.bft.spring.ui.ViewInit;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan(basePackages = "com.bft.spring")
public class AppConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("msg");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public ViewInit viewInit() {
        return new ViewInit() {
            @Override
            public Component initContent() {
                return new VerticalLayout();
            }
        };
    }

    @Bean
    public CompanyView companyView() {
        return new CompanyView();
    }

    @Bean
    public TimeZoneView timeZoneView() {
        return new TimeZoneView();
    }

    @Bean
    public BaseView baseView() {
        BaseView baseView = new BaseView();
        baseView.setMessageSource(messageSource());
        return baseView;
    }

}
