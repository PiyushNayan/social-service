package com.example.product_service.feignclient;

import com.example.product_service.entity.Merchant;
import com.example.product_service.exceptionHandler.ApiResponse;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;



@Component
class SolrFallBack implements FallbackFactory<SolrFeign> {


    @Override
    public SolrFeign create(Throwable throwable) {
        return null;
    }
}
