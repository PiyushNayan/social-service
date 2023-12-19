package com.example.product_service.controller;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.entity.Merchant;
import com.example.product_service.entity.Product;
import com.example.product_service.entity.ProductMerchant;
import com.example.product_service.exceptionHandler.ApiResponse;
import com.example.product_service.feignclient.MerchantFeign;
import com.example.product_service.repository.ProductMerchantCustomRepository;
import com.example.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @Autowired
    private MerchantFeign merchantFeign;


//    @PostMapping("/addProduct")
//    public ResponseEntity<Boolean> addProduct(@RequestBody ProductDto productDto) {
//        Boolean inserted = productService.addProduct(productDto);
//        if (inserted) {
//            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
//    }

    @PostMapping("/addProduct")
    public ApiResponse<String> addProduct(@RequestBody ProductDto productDto) {
        ApiResponse<String> apiResponse;
        try {
            Boolean inserted = productService.addProduct(productDto);
            if (inserted) {
                apiResponse = new ApiResponse<>("Product added");
            } else {
                apiResponse = new ApiResponse<>("404", "Could not add the product");
            }

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Could not add the product");
        }

        return apiResponse;
    }

    @GetMapping("/{productId}")
    public ApiResponse<Product> getProduct(@PathVariable("productId") String productId) {
        ApiResponse<Product> apiResponse;
        try {
            Optional<Product> product = productService.getProductById(productId);
            if (!product.isPresent()) {
                apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");
            } else {
                apiResponse = new ApiResponse<>(product.get());
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");
        }

        return apiResponse;

    }




    @GetMapping("/products")
    public ApiResponse<List<Product>> getAllProducts() {
        ApiResponse<List<Product>> apiResponse;
        try {
            List<Product> products = productService.getAllProducts();
            apiResponse = new ApiResponse<>(products);

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "No products found");
        }


        return apiResponse;
    }

    @PutMapping("/{productId}")
    public ApiResponse<String> updateProduct(@PathVariable("productId") String productId, @RequestBody ProductDto productDto) {
//        return productService.updateProductById(productId, productDto);

        ApiResponse<String> apiResponse;
        try {
            Optional<Product> product = productService.getProductById(productId);
            if (product.isPresent()) {
                productService.updateProductById(productId, productDto);
                apiResponse = new ApiResponse<String>("Product added");
            } else {
                apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");
        }

        return apiResponse;
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<String> deleteProductById(@PathVariable("productId") String productId) {

        ApiResponse<String> apiResponse;
        try {
            Optional<Product> product = productService.getProductById(productId);
            if (product.isPresent()) {
                productService.deleteProductById(productId);
                apiResponse = new ApiResponse<>("product deleted");
            } else
                apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");

        }


        return apiResponse;
    }

//    @GetMapping("getAllMerchants/{productId}")
//    public ApiResponse<List<Merchant>> getAllMerchants(@PathVariable("productId") String productId) {
////        List<ProductMerchant> productMerchant = productMerchantRepository.findByProductId(productId);
////        return new ResponseEntity<>(productMerchant, HttpStatus.OK);
//
//        ApiResponse<List<Merchant>> apiResponse;
//        List<Merchant> merchants = new ArrayList<>();
//        try {
//            List<ProductMerchant> productMerchants = productMerchantRepository.findByProductId(productId);
//            for (ProductMerchant productMerchant : productMerchants) {
//                Merchant merchant = merchantFeign.getMerchantById(productMerchant.getMerchantId()).getResultData();
//                merchant.setRating(productMerchant.getRating());
//                merchant.setStocks(productMerchant.getStocks());
//                merchant.setPrice(productMerchant.getPrice());
//                merchants.add(merchant);
//            }
//
//            apiResponse = new ApiResponse<>(merchants);
//        } catch (Exception e) {
//            apiResponse = new ApiResponse<>("404", "");
//        }
//        return apiResponse;
//    }
//
//    @PutMapping("/updateProductCount")
//    public ApiResponse<String> updateProductCount(@RequestParam("productId") String productId, @RequestParam("merchantId") String merchantId, @RequestParam("count") int count) {
//
//        ApiResponse<String> apiResponse;
//        try {
//            ProductMerchant productMerchant = productMerchantRepository.findByProductIdAndMerchantId(productId, merchantId);
//            int newCount = productMerchant.getStocks() + count;
//            productMerchantCustomRepository.updateStocksByProductIdAndMerchantId(productId, merchantId, newCount);
//            apiResponse = new ApiResponse<>("product updated");
//
//
//        } catch (Exception e) {
//            apiResponse = new ApiResponse<>("404", "Check the product id and try again, product not found");
//        }
//
//        return apiResponse;
//    }

//    @GetMapping("/checkProductInStock")
//    public Boolean checkProductInStock(@RequestParam("productId") String productId, @RequestParam("merchantId") String merchantId, @RequestParam("stockRequired") int stockRequired) {
//        ProductMerchant productMerchant = productMerchantRepository.findByProductIdAndMerchantId(productId, merchantId);
//        int stocks = productMerchant.getStocks();
//        if (stocks >= stockRequired) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }
}
