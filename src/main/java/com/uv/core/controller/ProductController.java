package com.uv.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uv.core.model.Category;
import com.uv.core.model.Product;
import com.uv.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addProduct(@RequestPart("product") String productJson, @RequestPart("file") MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product productObj = mapper.readValue(productJson, Product.class);
        return productService.saveProduct(productObj, file);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product updateCategory(@PathVariable Long id, @RequestPart("product") String product, @RequestPart(value = "file",required = false) MultipartFile file) throws IOException {
        return  productService.updateCategory(id, product,file);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
