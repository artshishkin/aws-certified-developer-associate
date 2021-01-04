package net.shyshkin.study.webfluxdynamodb.service;

import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.webfluxdynamodb.domain.Address;
import net.shyshkin.study.webfluxdynamodb.domain.Customer;
import net.shyshkin.study.webfluxdynamodb.domain.Result;
import net.shyshkin.study.webfluxdynamodb.repository.CustomerRepository;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@Slf4j
@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CustomerServiceIT {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    private static String customerID;
    private Customer defaultCustomer;
    private Address defaultAddress;

    @BeforeAll
    static void beforeAll() {
//        customerID = UUID.randomUUID().toString();
        customerID = "c6bd4b74-245f-4b48-a08c-3ea83f66666b";
    }

    @BeforeEach
    void setUp() {
        defaultAddress = Address.builder()
                .city("Kramatorsk")
                .country("Ukraine")
                .state("Donetsk region")
                .build();
        defaultCustomer = Customer.builder()
                .address(defaultAddress)
                .customerID(customerID)
                .firstName("Kate")
                .lastName("Shyshkina")
                .contactNo("kate@example.com")
                .build();
    }

    @Test
    @Order(10)
    void createNewCustomer() {
        //given
        Customer customerToSave = defaultCustomer;

        //when
        Mono<Result> newCustomer = customerService.createNewCustomer(customerToSave);

        //then
        StepVerifier.create(newCustomer)
                .expectNext(Result.SUCCESS)
                .verifyComplete();

        customerRepository
                .getCustomerByID(customerID)
                .thenAccept(customer -> assertThat(customer)
                        .isEqualToIgnoringGivenFields(customerToSave, "createdTimeStamp"))
                .join();
    }

    @Test
    @Order(20)
    void getCustomerByCustomerId() {
        //when
        Mono<Customer> customerMono = customerService.getCustomerByCustomerId(customerID);

        //then
        StepVerifier.create(customerMono)
                .assertNext(customer -> assertThat(customer)
                        .isEqualToIgnoringGivenFields(defaultCustomer, "createdTimeStamp")
                        .is(new Condition<>(customer1 -> StringUtils.isNotBlank(customer1.getCreatedTimeStamp()), "Created Time must not be null"))
                )
                .verifyComplete();
    }

    @Test
    @Order(21)
    void getCustomerByCustomerId_whenAbsent() {
        //when
        Mono<Customer> customerMono = customerService.getCustomerByCustomerId("some Absent ID");

        //then
        StepVerifier.create(customerMono)
                .verifyErrorSatisfies(ex -> assertThat(ex)
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("Invalid customerId"));
    }

    @Test
    @Order(30)
    void queryAddressByCustomerId() {
        //when
        Mono<Address> addressMono = customerService.queryAddressByCustomerId(customerID);

        //then
        StepVerifier
                .create(addressMono)
                .assertNext(address -> assertThat(address).isEqualTo(defaultAddress))
                .verifyComplete();
    }

    @Test
    @Order(30)
    void queryAddressByCustomerId_whenAbsent() {
        //when
        Mono<Address> addressMono = customerService.queryAddressByCustomerId("absent ID");

        //then
        StepVerifier
                .create(addressMono)
                .verifyComplete();
    }

    @Test
    @Order(40)
    void updateCustomerFields() {
        //given
        Customer customerToUpdate = Customer.builder()
                .address(Address.builder().city("Mariupol").build())
                .customerID(customerID)
                .firstName("Tanya")
                .contactNo("tanya@example.com")
                .build();

        //when
        Mono<Result> resultMono = customerService.updateCustomerFields(customerToUpdate);

        //then
        StepVerifier
                .create(resultMono)
                .expectNext(Result.SUCCESS)
                .verifyComplete();

        customerRepository
                .getCustomerByID(customerID)
                .thenAccept(
                        customer -> assertAll(
                                () -> assertThat(customer)
                                        .hasNoNullFieldsOrProperties()
                                        .isEqualToIgnoringGivenFields(defaultCustomer,
                                                "firstName", "contactNo", "address", "createdTimeStamp")
                                        .hasFieldOrPropertyWithValue("firstName", "Tanya")
                                        .hasFieldOrPropertyWithValue("contactNo", "tanya@example.com"),
                                () -> assertThat(customer.getAddress())
                                        .hasNoNullFieldsOrProperties()
                                        .hasFieldOrPropertyWithValue("city", "Mariupol")
                                        .hasFieldOrPropertyWithValue("state", "Donetsk region")
                                        .hasFieldOrPropertyWithValue("country", "Ukraine")
                        ))
                .join();
    }

    @Test
    @Order(40)
    void updateCustomerFields_whenAbsent() {
        //given
        Customer customerToUpdate = Customer.builder()
                .address(Address.builder().city("Mariupol").build())
                .customerID("absent ID")
                .firstName("Tanya")
                .build();

        //when
        Mono<Result> resultMono = customerService.updateCustomerFields(customerToUpdate);

        //then
        StepVerifier
                .create(resultMono)
                .expectNext(Result.FAIL)
                .verifyComplete();
    }

    @Test
    @Order(50)
    void updateCustomer() {
        //given
        Customer customerToUpdate = Customer.builder()
                .address(defaultAddress)
                .customerID(customerID)
                .firstName("Arina")
                .lastName("Shyshkina")
                .contactNo("arina@example.com")
                .build();

        //when
        Mono<Result> resultMono = customerService.updateCustomer(customerToUpdate);

        //then
        StepVerifier
                .create(resultMono)
                .expectNext(Result.SUCCESS)
                .verifyComplete();

        customerRepository
                .getCustomerByID(customerID)
                .thenAccept(
                        customer -> assertThat(customer)
                                .hasNoNullFieldsOrProperties()
                                .isEqualTo(customerToUpdate))
                .join();
    }

    @Test
    @Order(51)
    void updateCustomer_whenAbsent() {
        //given
        Customer customerToUpdate = Customer.builder()
                .customerID("some absent ID")
                .firstName("Arina")
                .lastName("Shyshkina")
                .contactNo("arina@example.com")
                .build();

        //when
        Mono<Result> resultMono = customerService.updateCustomer(customerToUpdate);

        //then
        StepVerifier
                .create(resultMono)
                .expectNext(Result.FAIL)
                .verifyComplete();
    }

    @Test
    @Order(60)
    void deleteCustomerByCustomerId() {
        //when
        Mono<Result> resultMono = customerService.deleteCustomerByCustomerId(customerID);

        //then
        StepVerifier.create(resultMono)
                .expectNext(Result.SUCCESS)
                .verifyComplete();
    }

    @Test
    @Order(61)
    void deleteCustomerByCustomerId_whenAbsent() {
        //when
        Mono<Result> resultMono = customerService.deleteCustomerByCustomerId("absent ID");

        //then
        StepVerifier.create(resultMono)
                .expectNext(Result.FAIL)
                .verifyComplete();
    }

    @Test
    @Order(35)
    void getCustomerList() {
        //when
        Flux<Customer> customerFlux = customerService.getCustomerList();

        //then
        AtomicInteger totalCount = new AtomicInteger();
        StepVerifier
                .create(customerFlux)
                .thenConsumeWhile((__) -> true, (customer) -> {
                    totalCount.getAndIncrement();
                    log.info("{}", customer);
                    assertThat(customer).hasNoNullFieldsOrProperties();
                })
                .verifyComplete();
        log.info("Total count: {}", totalCount);
        assertThat(totalCount).hasValueGreaterThan(1);
    }

    @Test
    @Order(5)
    void getCustomerList_afterInit() {
        //when
        Flux<Customer> customerFlux = customerService.getCustomerList();

        //then
        AtomicInteger totalCount = new AtomicInteger();
        StepVerifier
                .create(customerFlux)
                .thenConsumeWhile((__) -> true, (customer) -> {
                    totalCount.getAndIncrement();
                    log.info("{}", customer);
                    assertThat(customer).hasNoNullFieldsOrProperties();
                })
                .verifyComplete();
        log.info("Total count: {}", totalCount);
        assertThat(totalCount).hasValue(5);
    }
}