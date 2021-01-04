package net.shyshkin.study.dynamodbspringdata.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.dynamodbspringdata.model.ProductInfo;
import net.shyshkin.study.dynamodbspringdata.repository.ProductInfoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductInfoController {

    private final ProductInfoRepository productInfoRepository;

    @GetMapping
    public Iterable<ProductInfo> getAllProductInfos(){
        return productInfoRepository.findAll();
    }
}
