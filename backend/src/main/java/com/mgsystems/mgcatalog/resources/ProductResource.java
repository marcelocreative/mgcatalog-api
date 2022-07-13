package com.mgsystems.mgcatalog.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mgsystems.mgcatalog.dto.ProductDTO;
import com.mgsystems.mgcatalog.services.ProductService;

@RestController
@RequestMapping(value= "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findAll(
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value="direction", defaultValue = "ASC") String direction,
			@RequestParam(value= "orderBy", defaultValue = "name") String orderBy
			){
		
		PageRequest pageRequest= PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ProductDTO> list = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
		
		ProductDTO dto = service.finById(id);
		
		return ResponseEntity.ok().body(dto);
		
	}

}
