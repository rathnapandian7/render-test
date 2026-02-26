package com.uv.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uv.core.model.Category;
import com.uv.core.model.Product;
import com.uv.core.repository.CategoryRepository;
import com.uv.core.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository repository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Category addCategory(@RequestPart("category") String category, @RequestPart("file") MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Category categoryObj = mapper.readValue(category, Category.class);
        return categoryService.saveCategory(categoryObj, file);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Category updateCategory(@PathVariable Long id, @RequestPart("category") String category, @RequestPart(value = "file",required = false) MultipartFile file) throws IOException {
        return  categoryService.updateCategory(id, category,file);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
