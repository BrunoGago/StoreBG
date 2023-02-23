package com.brunogago.crud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.brunogago.crud.data.vo.ProductVO;
import com.brunogago.crud.entities.Product;
import com.brunogago.crud.exceptions.ResourceNotFoundException;
import com.brunogago.crud.repositories.ProductRepository;

@Service
public class ProductService {

	//Injeção de dependência com a classe repository
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public ProductVO create(ProductVO productVO) {
		ProductVO productVOReturn = ProductVO.create(productRepository.save(Product.create(productVO)));
		return productVOReturn;
	}
	
	public Page<ProductVO> findAll(Pageable pageable) {
		var page = productRepository.findAll(pageable);
		return page.map(this::convertToProductVO);
	}
	
	private ProductVO convertToProductVO(Product product) {
		return ProductVO.create(product);
	}
	
	public ProductVO findById(Long id) {
		var entity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				"No records found for this ID"));
		return ProductVO.create(entity);
	}
	
	public ProductVO update(ProductVO productVO) {
		final Optional<Product> optionalProduct = productRepository.findById(productVO.getId());
		
		if(!optionalProduct.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		return ProductVO.create(productRepository.save(Product.create(productVO)));
	}
	
	public void delete(Long id) {
		var entity = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				"No records found for this ID"));
		productRepository.delete(entity);
	}
	
}
