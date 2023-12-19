package com.example.product_service.feignclient;

import com.example.product_service.entity.Merchant;
import com.example.product_service.exceptionHandler.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@FeignClient(value = "Merchant",url = "http://localhost:8088",fallbackFactory = MerchantFallback.class)
public interface MerchantFeign {


    @RequestMapping(method = RequestMethod.GET,value="/merchant/{merchantId}")
    ApiResponse<Merchant> getMerchantById(@PathVariable("merchantId") String merchantId);

}
