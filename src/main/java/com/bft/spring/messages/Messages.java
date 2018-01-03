package com.bft.spring.messages;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by rev on 03.01.2018.
 */
@Component("messages")
public class Messages {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    @PostConstruct
    public void init() {
        messageSource.setBasename("msg");
        messageSource.setDefaultEncoding("UTF-8");;
    }

    public String getMessage(String s) {
        return messageSource.getMessage(s, null, null);
    }
}
