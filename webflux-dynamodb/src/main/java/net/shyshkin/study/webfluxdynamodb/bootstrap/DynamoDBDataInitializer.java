package net.shyshkin.study.webfluxdynamodb.bootstrap;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.webfluxdynamodb.domain.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class DynamoDBDataInitializer implements CommandLineRunner {

    private final DynamoDbAsyncClient asyncClient;
    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;

    @Override
    public void run(String... args) throws Exception {
        asyncClient.listTables()
                .thenApply(ListTablesResponse::tableNames)
                .thenAccept(tables -> {
                    if (null != tables && !tables.contains(Customer.class.getSimpleName())) {
                        DynamoDbAsyncTable<Customer> customer = enhancedAsyncClient
                                .table(Customer.class.getSimpleName(), TableSchema.fromBean(Customer.class));
                        customer.createTable();
                    }
                });
    }
}
