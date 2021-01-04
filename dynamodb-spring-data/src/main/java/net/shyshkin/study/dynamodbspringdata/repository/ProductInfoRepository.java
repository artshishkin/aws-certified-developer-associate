package net.shyshkin.study.dynamodbspringdata.repository;

import net.shyshkin.study.dynamodbspringdata.model.ProductInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ProductInfoRepository extends CrudRepository<ProductInfo, String> {

    List<ProductInfo> findByCostContaining(String costPart);

}
