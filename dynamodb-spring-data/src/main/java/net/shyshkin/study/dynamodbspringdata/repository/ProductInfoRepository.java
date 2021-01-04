package net.shyshkin.study.dynamodbspringdata.repository;


import net.shyshkin.study.dynamodbspringdata.model.ProductInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProductInfoRepository extends CrudRepository<ProductInfo, String> {
}
