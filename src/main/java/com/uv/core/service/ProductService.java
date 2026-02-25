package com.uv.core.service;

import com.cloudinary.Cloudinary;
import com.uv.core.model.Product;
import com.uv.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.cache.annotation.Cacheable;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    ImageUploadService imageUploadService;

    @Cacheable(value="products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product saveProduct(Product product,MultipartFile file) throws IOException {
        String folder = "uv_power/products";
        String imageName = product.getProductName().replaceAll(" ", "_") + "_" + System.currentTimeMillis();
        Map result = imageUploadService.uploadImage(file, folder, imageName);
        String imageUrl = result.get("secure_url").toString();
        String publicId = result.get("public_id").toString();
        product.setImageUrl(imageUrl);
        Product response = productRepository.save(product);
        return response;
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}