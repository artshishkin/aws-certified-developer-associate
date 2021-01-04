package net.shyshkin.study.dynamodbspringdata.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import net.shyshkin.study.dynamodbspringdata.model.ProductInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accesskey=test1",
        "amazon.aws.secretkey=test231"})
class ProductInfoRepositoryIT {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    ProductInfoRepository repository;

    private static final String EXPECTED_COST = "20";
    private static final String EXPECTED_PRICE = "50";


    @BeforeEach
    void setUp() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(ProductInfo.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);

        //...

        dynamoDBMapper.batchDelete(
                (List<ProductInfo>) repository.findAll());
    }


    @Test
    public void givenItemWithExpectedCost_whenRunFindAll_thenItemIsFound() {

        ProductInfo productInfo = ProductInfo
                .builder()
                .cost(EXPECTED_COST)
                .msrp(EXPECTED_PRICE)
                .build();

        repository.save(productInfo);
        List<ProductInfo> result = (List<ProductInfo>) repository.findAll();

        assertThat(result.size()).isGreaterThan(0);
        assertThat(result.get(0).getCost()).isEqualTo(EXPECTED_COST);
    }


}