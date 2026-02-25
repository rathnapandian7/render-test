package com.uv.core.service;

import com.uv.core.model.Category;
import com.uv.core.model.Product;
import com.uv.core.repository.CategoryRepository;
import com.uv.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    ImageUploadService imageUploadService;

    @Cacheable("categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @CacheEvict(value = "categories", allEntries = true)
    public Category saveCategory(Category category, MultipartFile file) throws IOException {
        String folder = "uv_power/categories";
        String imageName = category.getCategoryName().replaceAll(" ", "_") + "_" + System.currentTimeMillis();
        Map result = imageUploadService.uploadImage(file, folder, imageName);
        String imageUrl = result.get("secure_url").toString();
        String publicId = result.get("public_id").toString();
        category.setImageUrl(imageUrl);
        Category response = categoryRepository.save(category);
        return response;
    }


}
