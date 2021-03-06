package com.itheima.shopping.service;

import com.itheima.shopping.entity.ProductInfo;
import com.itheima.shopping.entity.Region;

import java.util.List;

/**
 * Created by Administrator.
 */
public interface ProductService {
    //添加商品
    public void createProduct(ProductInfo product);

    //查询商品
    public List<ProductInfo> queryProduct(int page, int pageSize);

    List<Region> getAllRegion();

    void insertRegion(Region region);
}
