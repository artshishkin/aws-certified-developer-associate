package net.shyshkin.study.dynamodbspringdata.bootstrap;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.dynamodbspringdata.model.ProductInfo;
import net.shyshkin.study.dynamodbspringdata.repository.ProductInfoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
//@Profile("!test")
public class InitData implements CommandLineRunner {

    private final ProductInfoRepository productInfoRepository;
    private final AmazonDynamoDB amazonDynamoDB;

    @Override
    public void run(String... args) throws Exception {

        String exclusiveTableName = ProductInfo.class.getSimpleName();
//        ListTablesResult listTablesResult = amazonDynamoDB.listTables(exclusiveTableName, 1);
        ListTablesResult listTablesResult = amazonDynamoDB.listTables();
        if (!listTablesResult.getTableNames().contains(exclusiveTableName)) {
            DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

            CreateTableRequest tableRequest = dynamoDBMapper
                    .generateCreateTableRequest(ProductInfo.class);
            tableRequest.setProvisionedThroughput(
                    new ProvisionedThroughput(1L, 1L));
            CreateTableResult createTableResult = amazonDynamoDB.createTable(tableRequest);
            log.debug("Create table: {}", createTableResult);
        }

        if (productInfoRepository.count() == 0) {
            List<ProductInfo> productInfoList = IntStream.rangeClosed(1, 5)
                    .mapToObj(this::stubProductInfo)
                    .collect(Collectors.toList());
            productInfoRepository.saveAll(productInfoList);
        }
    }

    private ProductInfo stubProductInfo(int idx) {
        return ProductInfo.builder()
                .cost("cost" + idx)
                .msrp("msrp" + idx)
                .build();
    }
}
