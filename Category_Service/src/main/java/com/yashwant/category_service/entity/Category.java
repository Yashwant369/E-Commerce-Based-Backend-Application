package com.yashwant.category_service.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Category 
{
	@Id
	private String categoryId;
	private String categoryTitle;
	private String categoryDescription;
	
	

}
