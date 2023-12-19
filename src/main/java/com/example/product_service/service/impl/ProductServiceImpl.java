package com.example.product_service.service.impl;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.dto.ProductFeignDto;
import com.example.product_service.entity.Product;
import com.example.product_service.entity.ProductCategory;
import com.example.product_service.entity.ProductMerchant;
import com.example.product_service.feignclient.SolrFeign;
import com.example.product_service.repository.ProductCategoryRepository;
import com.example.product_service.repository.ProductMerchantRepository;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMerchantRepository productMerchantRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private SolrFeign solrFeign;

    public boolean productCategoryExists(String productCategoryName) {
        return productCategoryRepository.existsByProductCategoryName(productCategoryName);
    }

    public String getProductCategoryId(String productCategoryName) {
        return productCategoryRepository.findProductCategoryIdByProductCategoryName(productCategoryName);
    }

    public Boolean addProduct(ProductDto productDto ){
        try {
            if(productDto!=null && productDto.getProductName()!=null){
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);
            if (product.getProductName() == null) {
                throw new IllegalArgumentException("Product details cannot be null");
            }
            String productId = UUID.randomUUID().toString();
            product.setProductId(productId);
            productRepository.save(product);
            return true;
            }
            else {
                throw new IllegalArgumentException("ProductDto cannot be null");
            }
         }catch (Exception e) {
         return false;
         }
}




//    @Override
//    public Boolean addProduct(ProductDto productDto ) {
//        try {
//            Product product = new Product();
//            BeanUtils.copyProperties(productDto, product);
//            String productId = UUID.randomUUID().toString();
//            List<Product> existingProduct = productRepository.findByProductName(productDto.getProductName());
//            if (!existingProduct.isEmpty()) {
//                productId = existingProduct.get(0).getProductId();
//                product.setProductId(productId);
////
//            } else {
//                product.setProductId(productId);
//                productRepository.save(product);
//            }


//        String productCategoryName = productDto.getProductCategoryName();
//        if(!productCategoryExists(productCategoryName)) {
//            ProductCategory newProductCategory = new ProductCategory();
//            newProductCategory.setProductCategoryId(UUID.randomUUID().toString());
//            newProductCategory.setProductCategoryName(productCategoryName);
//            productCategoryRepository.save(newProductCategory);
//        }
//        else {
//            String productCategoryId = getProductCategoryId(productCategoryName);
//            if(productCategoryId!=null) {
//                product.setProductCategoryId(productCategoryId);
//            }
//        }


//        Product newProduct =

//            ProductMerchant productMerchant = new ProductMerchant();
//            productMerchant.setMerchantId(productDto.getMerchantId());
//            productMerchant.setProductId(productId);
//            productMerchant.setRating(5);
//            productMerchant.setStocks(productDto.getCount());
//            productMerchant.setPrice(productDto.getPrice());
//            productMerchantRepository.save(productMerchant);
//
//
//            ProductFeignDto productFeignDto = new ProductFeignDto();
//            BeanUtils.copyProperties(product, productFeignDto);
//            productFeignDto.setMerchantRating(productMerchant.getRating());
//            productFeignDto.setProductsAvailable(productDto.getCount());
//            productFeignDto.setProductsSold(4);
//
//            solrFeign.saveProduct(productFeignDto);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
////        return Objects.nonNull(newProduct);
//    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String productId){
        Optional<Product> product = productRepository.findById(productId);
        return product;
    }

    public Boolean updateProductById(String productId, ProductDto productDto) {
        Product updatedProduct = new Product();
        BeanUtils.copyProperties(productDto, updatedProduct);
        updatedProduct = productRepository.save(updatedProduct);
        return updatedProduct!=null;
    }

    public void deleteProductById(String productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> getProductsByIds(List<String> productIds) {

        List<Product> productList = new ArrayList<>();
        for(String productId: productIds){
            Product product = productRepository.findById(productId).orElse(null);
            productList.add(product);
        }

        return productList;
    }


}

