package com.dg.drimansy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dg.drimansy.model.Product;
import com.dg.drimansy.repository.IProductRepository;
import com.dg.drimansy.view.vo.ProductVO;

@Service
public class ProductService {
	private final IProductRepository productRepository;
	
	public ProductService(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<ProductVO> getProductsVO() {
		List<Product> products = this.productRepository.findAllOrdered();
		return getProductsVO(products);
	}	
	
	@Transactional(readOnly = true)
	public List<Product> findAllOrdered() {
		return this.productRepository.findAllOrdered();
	}
	
	public List<ProductVO> getProductsVO(List<Product> products){
		List<ProductVO> productsVO = new ArrayList<ProductVO>();
		for(Product product: products) {
			ProductVO pVO = new ProductVO();
			pVO.toVO(product);
			productsVO.add(pVO);
		}
		return productsVO;
	}
}
