package com.rest.appstore.config;

import com.rest.appstore.dataloader.DataLoaderTask;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppStoreConfig {


    @Bean
    @ConditionalOnProperty(name = "dataload.enabled", havingValue = "true")
    public DataLoaderTask dataLoaderTask(){
        return new DataLoaderTask();
    }
}
