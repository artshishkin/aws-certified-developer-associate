package net.shyshkin.study.webfluxdynamodb.bootstrap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.shyshkin.study.webfluxdynamodb.domain.Address;
import net.shyshkin.study.webfluxdynamodb.domain.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.String.valueOf;
import static java.time.Instant.now;

@Component
@RequiredArgsConstructor
@Profile("test")
public class DynamoDBTestDataInitializer implements CommandLineRunner {

    private final DynamoDbAsyncClient asyncClient;
    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;

    @Getter
    private CompletableFuture<Void> initFinished;

    @Override
    public void run(String... args) throws Exception {
        initFinished = asyncClient
                .deleteTable(builder -> builder.tableName(Customer.class.getSimpleName()))
                .thenCompose((__) -> asyncClient.listTables())
                .thenApply(ListTablesResponse::tableNames)
                .thenAccept(tables -> {
                    if (null != tables && !tables.contains(Customer.class.getSimpleName())) {
                        DynamoDbAsyncTable<Customer> customerTable = enhancedAsyncClient
                                .table(Customer.class.getSimpleName(), TableSchema.fromBean(Customer.class));
                        customerTable.createTable();
                        List<CompletableFuture<Void>> stubCustomers = IntStream.rangeClosed(1, 5)
                                .mapToObj(this::createStubCustomer)
                                .map(customerTable::putItem)
                                .collect(Collectors.toList());
                        stubCustomers
                                .forEach(CompletableFuture::join);
                    }
                });
    }

    private Customer createStubCustomer(int index) {
        Address address = Address.builder()
                .city("City" + index)
                .country("Country" + index)
                .state("State" + index)
                .build();
        return Customer.builder()
                .address(address)
                .customerID(UUID.randomUUID().toString())
                .firstName("Artem" + index)
                .lastName("Last Name " + index)
                .contactNo("Contact No " + index)
//                .createdTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .createdTimeStamp(valueOf(now().getEpochSecond()))
                .build();
    }
}
