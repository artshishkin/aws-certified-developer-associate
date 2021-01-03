package net.shyshkin.study.webfluxdynamodb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.webfluxdynamodb.domain.Address;
import net.shyshkin.study.webfluxdynamodb.domain.Customer;
import net.shyshkin.study.webfluxdynamodb.domain.Result;
import net.shyshkin.study.webfluxdynamodb.repository.CustomerRepository;
import net.shyshkin.study.webfluxdynamodb.utils.Helper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.async.SdkPublisher;

import java.util.concurrent.CompletableFuture;

import static java.lang.String.valueOf;
import static java.time.Instant.now;
import static net.shyshkin.study.webfluxdynamodb.domain.Result.FAIL;
import static net.shyshkin.study.webfluxdynamodb.domain.Result.SUCCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Mono<Result> createNewCustomer(Customer customer) {
        customer.setCreatedTimeStamp(valueOf(now().getEpochSecond()));
        Result createStatus = customerRepository.save(customer)
                .handle((__, ex) -> ex == null ? SUCCESS : FAIL)
                .join(); //blocked until data is retrieved
        // TODO: 03.01.2021 Exclude join
        return Mono.just(createStatus);

    }

    public Mono<Customer> getCustomerByCustomerId(String customerId) {
        CompletableFuture<Customer> customer = customerRepository.getCustomerByID(customerId)
                .whenComplete((cus, ex) -> {
                    if (null == cus) {
                        throw new IllegalArgumentException("Invalid customerId");
                    }
                })
                .exceptionally(ex -> new Customer());
        return Mono.fromFuture(customer);
    }

    public Mono<Address> queryAddressByCustomerId(String customerId) {
        SdkPublisher<Address> customerAddress = customerRepository.getCustomerAddress(customerId)
                .items()
                .map(Customer::getAddress);
        return Mono.from(customerAddress)
                .onErrorReturn(new Address());
    }

    public Mono<Result> updateCustomer(Customer customer) {
        customer.setCreatedTimeStamp(valueOf(now().getEpochSecond()));
        Result updateStatus = customerRepository.getCustomerByID(customer.getCustomerID())
                .thenApply(retrievedCustomer -> {
                    if (null == retrievedCustomer) {
                        throw new IllegalArgumentException("Invalid CustomerID");
                    }
                    return retrievedCustomer;
                }).thenCompose(__ -> customerRepository.updateCustomer(customer))
                .handle((__, ex) -> ex == null ? SUCCESS : FAIL)
                .join();//blocked until data is retrieved
        // TODO: 03.01.2021 Exclude join
        return Mono.just(updateStatus);
    }

    public Mono<Result> updateCustomerFields(Customer customer) {
        CompletableFuture<Result> resultCF = customerRepository.getCustomerByID(customer.getCustomerID())
                .thenApply(retrievedCustomer -> {
                    if (null == retrievedCustomer) {
                        throw new IllegalArgumentException("Invalid CustomerID");
                    }
                    Address addressToUpdate = customer.getAddress();
                    Address retrievedAddress = retrievedCustomer.getAddress();
                    BeanUtils.copyProperties(retrievedAddress, addressToUpdate, Helper.getNonNullPropertyNames(addressToUpdate));

                    BeanUtils.copyProperties(customer, retrievedCustomer, Helper.getNullPropertyNames(customer));

                    return retrievedCustomer;
                })
                .thenCompose(customerRepository::updateCustomer)
                .handle((__, ex) -> ex == null ? SUCCESS : FAIL);
        return Mono.fromFuture(resultCF);
    }

    @Deprecated
    public Mono<Result> updateExistingOrCreateCustomer(Customer customer) {
        customer.setCreatedTimeStamp(valueOf(now().getEpochSecond()));
        Result updateStatus = customerRepository.updateCustomer(customer)
                .handle((__, ex) -> ex == null ? SUCCESS : FAIL)
                .join();//blocked until data is retrieved
        // TODO: 03.01.2021 Exclude join
        return Mono.just(updateStatus);
    }

    public Mono<Result> deleteCustomerByCustomerId(String customerId) {
        Result deleteStatus = customerRepository.deleteCustomerById(customerId)
                .handle((__, ex) -> ex == null ? SUCCESS : FAIL)
                .join();//blocked until data is retrieved
        // TODO: 03.01.2021 Exclude join
        return Mono.just(deleteStatus);
    }

    public Flux<Customer> getCustomerList() {
        return Flux.from(customerRepository.getAllCustomers().items())
                .onErrorReturn(new Customer());
    }
}
