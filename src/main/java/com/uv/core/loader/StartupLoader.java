package com.uv.core.loader;

import com.uv.core.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupLoader {

    private final ProductRepository repository;

    // ✅ Constructor Injection
    public StartupLoader(ProductRepository repository) {
        this.repository = repository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadProductsIntoCache() {
        repository.findAll();
        System.out.println("Products preloaded into cache");
    }
}
