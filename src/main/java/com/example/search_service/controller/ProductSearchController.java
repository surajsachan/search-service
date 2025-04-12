package com.example.search_service.controller;

import com.example.search_service.model.Product;
import com.example.search_service.service.OpenSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductSearchController {

    @Autowired
    private OpenSearchService searchService;

    @GetMapping("/search/{query}")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String query) {
        try {
            List<Product> products = searchService.searchDocuments(query);
            return ResponseEntity.ok(products);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
