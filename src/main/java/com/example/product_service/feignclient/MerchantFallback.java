package com.example.product_service.feignclient;


import com.example.product_service.entity.Merchant;
import com.example.product_service.exceptionHandler.ApiResponse;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;



@Component
public class MerchantFallback implements FallbackFactory<MerchantFeign> {


    @Override
    public MerchantFeign create(Throwable throwable) {
        return new MerchantFeign() {
            @Override
            public ApiResponse<Merchant> getMerchantById(String merchantId) {
                return null;
            }
        };
    }
}
