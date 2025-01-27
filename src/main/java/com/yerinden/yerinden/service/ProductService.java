package com.yerinden.yerinden.service;

import com.yerinden.yerinden.controller.request.BasketAddRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.entity.Product;
import com.yerinden.yerinden.model.BusinessException;
import com.yerinden.yerinden.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;

    public Product findById(Long productId) throws BusinessException {
         return repository.findById(productId).orElseThrow(BusinessException::productNotFound);
    }
}
