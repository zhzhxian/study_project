package com.itheima.shopping.controller;

/**
 * * 卖家商品展示
 */

import com.itheima.shopping.entity.ProductInfo;
import com.itheima.shopping.entity.Region;
import com.itheima.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SellerController {
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public String createProject(@RequestBody ProductInfo productInfo) {
        productService.createProduct(productInfo);
        return "创建成功!";
    }

    @GetMapping(value = "/products/{page}/{pageSize}")
    public List<ProductInfo> queryProduct(@PathVariable("page") int page, @PathVariable("pageSize") int
            pageSize) {
        return productService.queryProduct(page, pageSize);
    }

    @GetMapping(value = "/regions")
    public List<Region> getAllRegion() {
        return productService.getAllRegion();
    }

    @PostMapping("/region/add")
    public void addRegion(@RequestBody Region region) {
        productService.insertRegion(region);
    }

}