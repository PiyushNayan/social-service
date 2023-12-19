package com.example.product_service.feignclient;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.dto.ProductFeignDto;
import com.example.product_service.entity.Merchant;
import com.example.product_service.exceptionHandler.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@FeignClient(value = "Product",url = "http://localhost:9000",fallbackFactory = SolrFallBack.class)
public interface SolrFeign {

    @RequestMapping(method = RequestMethod.POST,value="/product/addProduct")
    ApiResponse<Merchant> saveProduct(@RequestBody ProductFeignDto productFeignDto);
}