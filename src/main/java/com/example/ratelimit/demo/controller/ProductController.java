package com.example.ratelimit.demo.controller;


import com.example.ratelimit.demo.data.entity.Product;
import com.google.common.util.concurrent.RateLimiter;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final Bucket bucket;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        boolean okToGo = bucket.tryConsume(1);

        if (okToGo) {
            return new ResponseEntity<List<Product>>(
                   Arrays.asList(new Product("product 1", "category 1", new Date()),
                                 new Product("product 2", "category 1", new Date()),
                                 new Product("product 3", "category 2", new Date())
                     ),
                    HttpStatus.OK);
        }
        else
            return new ResponseEntity("You have exceeded the 10 requests in 1 minute limit!", HttpStatus.TOO_MANY_REQUESTS);
    }
}
