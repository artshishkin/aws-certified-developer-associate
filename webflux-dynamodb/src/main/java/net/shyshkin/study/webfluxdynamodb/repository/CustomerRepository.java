package net.shyshkin.study.webfluxdynamodb.repository;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.webfluxdynamodb.domain.Customer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

import java.util.concurrent.CompletableFuture;

import static software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional.keyEqualTo;

@Repository
public class CustomerRepository {

    private final DynamoDbAsyncTable<Customer> customerDynamoDbAsyncTable;

    public CustomerRepository(DynamoDbEnhancedAsyncClient asyncClient) {
        this.customerDynamoDbAsyncTable = asyncClient
                .table(Customer.class.getSimpleName(), TableSchema.fromBean(Customer.class));
    }

    //CREATE
    public CompletableFuture<Void> save(Customer customer) {
        return customerDynamoDbAsyncTable.putItem(customer);
    }

    //READ
    public CompletableFuture<Customer> getCustomerByID(String customerId) {
        return customerDynamoDbAsyncTable.getItem(getKeyBuild(customerId));
    }

    //UPDATE
    public CompletableFuture<Customer> updateCustomer(Customer customer) {
        return customerDynamoDbAsyncTable.updateItem(customer);
    }

    //DELETE
    public CompletableFuture<Customer> deleteCustomerById(String customerId) {
        return customerDynamoDbAsyncTable.deleteItem(getKeyBuild(customerId));
    }

    //READ_CUSTOMER_ADDRESS_ONLY
    public PagePublisher<Customer> getCustomerAddress(String customerId) {
        return customerDynamoDbAsyncTable
                .query(r -> r.queryConditional(keyEqualTo(k -> k.partitionValue(customerId)))
                        .addAttributeToProject("CustomerAddress"));
    }

    //GET_ALL_ITEM
    public PagePublisher<Customer> getAllCustomers() {
        return customerDynamoDbAsyncTable.scan();
    }

    private Key getKeyBuild(String customerId) {
        return Key.builder().partitionValue(customerId).build();
    }
}
