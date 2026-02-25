package com.uv.core.controller;

import com.uv.core.model.Offer;
import com.uv.core.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferRepository repository;

    @PostMapping
    public Offer addOffer(@RequestBody Offer offer) {
        return repository.save(offer);
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
