package com.yashwant.product_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yashwant.product_service.dtos.ProductDto;
import com.yashwant.product_service.entity.Product;

public interface ProductRepo extends JpaRepository<Product,String>
{

	Product findByProductTitle(String name);

	@Query(value = "select * from product where product_title like :name%", nativeQuery = true)
	List<Product> getByName(String name);

	List<Product> findByProductLive(String live);

	@Modifying
	@Query(value = "delete from product where category_id = :categoryId", nativeQuery = true)
	void deleteProductByCategoryId(String categoryId);

	@Modifying
	@Query(value = "update product set product_category = :productCategory where category_id = :categoryId", nativeQuery = true)
	void updateProductByCategoryName(String productCategory, String categoryId);
    
	@Query(value = "select * from product where product_category = :productCategory", nativeQuery = true)
	List<Product> findProductCategory(String productCategory);
	
	

}
