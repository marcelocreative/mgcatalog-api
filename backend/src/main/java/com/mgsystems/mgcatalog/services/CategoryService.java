package com.mgsystems.mgcatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mgsystems.mgcatalog.dto.CategoryDTO;
import com.mgsystems.mgcatalog.entities.Category;
import com.mgsystems.mgcatalog.repositories.CategoryRepository;
import com.mgsystems.mgcatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll(){
		
		 List<Category> list= repository.findAll();
		 
		 return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
	
		Optional<Category> obj= repository.findById(id);
		Category entity= obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
	}

}
