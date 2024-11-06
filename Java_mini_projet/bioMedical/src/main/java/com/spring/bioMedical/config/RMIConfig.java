package com.spring.bioMedical.config;

import com.spring.bioMedical.rmi.ChatbotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class RMIConfig {
    @Bean
    public RmiProxyFactoryBean chatbotService() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/ChatbotService");
        rmiProxyFactoryBean.setServiceInterface(ChatbotService.class);
        return rmiProxyFactoryBean;
    }
}
