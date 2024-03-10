package com.nagp.config;

import com.nagp.client.RestaurantClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;
    @Bean
    public WebClient restaurantWebClient(){
        return WebClient.builder()
                .baseUrl("http://restaurant-info-service")
                .filter(filterFunction)
                .build();
    }
    @Bean
    public RestaurantClient restaurantClient(){
        HttpServiceProxyFactory sF = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(restaurantWebClient()))
                .build();
        return sF.createClient(RestaurantClient.class);
    }

}
