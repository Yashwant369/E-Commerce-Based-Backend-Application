package com.yashwant.category_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yashwant.category_service.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,String>
{

	Category findByCategoryTitle(String categoryTitle);
	

}
