package net.shyshkin.study.dynamodbspringdata.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.dynamodbspringdata.model.ProductInfo;
import net.shyshkin.study.dynamodbspringdata.repository.ProductInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductInfoController {

    private final ProductInfoRepository productInfoRepository;

    @GetMapping
    public Iterable<ProductInfo> getAllProductInfos() {
        return productInfoRepository.findAll();
    }

    @GetMapping("{id}")
    public ProductInfo getProductInfos(@PathVariable("id") String id) {
        return productInfoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Info with id `" + id + "` not found"));
    }

    @GetMapping("search")
    public List<ProductInfo> search(@RequestParam("cost") String cost) {
        return productInfoRepository.findByCostContaining(cost);
    }

    @ExceptionHandler
    public String handleException(Exception ex) {
        return ex.getMessage();
    }
}
