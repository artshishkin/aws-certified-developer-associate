package net.shyshkin.study.webfluxdynamodb.repository;

import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.webfluxdynamodb.bootstrap.DynamoDBTestDataInitializer;
import net.shyshkin.study.webfluxdynamodb.domain.Address;
import net.shyshkin.study.webfluxdynamodb.domain.Customer;
import net.shyshkin.study.webfluxdynamodb.extensions.DynamoDBLocalServerExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static java.lang.String.valueOf;
import static java.time.Instant.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ExtendWith(DynamoDBLocalServerExtension.class)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DynamoDBTestDataInitializer dynamoDBTestDataInitializer;
    static boolean initFinished;

    private static String customerID;

    @BeforeAll
    static void beforeAll() {
//        customerID = UUID.randomUUID().toString();
        customerID = "c6bd4b74-245f-4b48-a08c-3ea83f60a43d";
    }

    @BeforeEach
    void setUp() {
        if (!initFinished) {
            dynamoDBTestDataInitializer.getInitFinished().join();
            initFinished = true;
        }
    }

    @Test
    @Order(10)
    void saveAndGetById() {
        //given
        Address address = Address.builder()
                .city("Kramatorsk")
                .country("Ukraine")
                .state("Donetsk region")
                .build();
        Customer customerToSave = Customer.builder()
                .address(address)
                .customerID(customerID)
                .firstName("Artem")
                .lastName("Shyshkin")
                .contactNo("d.art.shishkin@gmail.com")
//                .createdTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .createdTimeStamp(valueOf(now().getEpochSecond()))
                .build();

        //when
        CompletableFuture<Void> saveCF = customerRepository.save(customerToSave);

        //then
        saveCF
                .thenCompose((saveEnded) -> customerRepository
                        .getCustomerByID(customerToSave.getCustomerID()))
                .thenAccept(customer ->
                        assertAll(
                                () -> assertNotNull(customer),
                                () -> assertThat(customer).isEqualTo(customerToSave)
                        ))
                .join();
    }

    @Test
    void getCustomerById_whenAbsent() {
        //when
        CompletableFuture<Customer> someFakeCustomerCF = customerRepository.getCustomerByID("someFakeId");

        //then
        someFakeCustomerCF
                .thenAccept(customer -> assertThat(customer).isNull())
                .join();
    }

    @Test
    @Order(20)
    void updateCustomer() {
        //given
        Address address = Address.builder()
                .city("Kyiv")
                .country("Ukraine")
                .state("")
                .build();

        Customer customerToUpdate = Customer.builder()
                .address(address)
                .customerID(customerID)
                .firstName("Artem")
                .lastName("Shyshkin")
                .contactNo("d.art.shishkin@gmail.com")
//                .createdTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .createdTimeStamp(valueOf(now().getEpochSecond()))
                .build();

        //when
        CompletableFuture<Customer> updateCF = customerRepository.updateCustomer(customerToUpdate);

        //then
        updateCF
                .thenAccept(customer -> assertThat(customer).isEqualTo(customerToUpdate))
                .join();

    }

    @Test
    @Order(30)
    void deleteCustomerById() {
        //when
        CompletableFuture<Customer> deleteCF = customerRepository.deleteCustomerById(customerID);

        //then
        deleteCF
                .thenAccept(deletedCustomer -> assertThat(deletedCustomer.getCustomerID()).isEqualTo(customerID))
                .thenCompose((fin) -> customerRepository.getCustomerByID(customerID))
                .thenAccept(notExistingCustomer -> assertThat(notExistingCustomer).isNull())
                .join();
    }

    @Test
    @Order(31)
    void deleteCustomerById_whenAbsent() {
        //when
        CompletableFuture<Customer> deleteCF = customerRepository.deleteCustomerById("someFakeId");

        //then
        deleteCF
                .thenAccept(deletedCustomer -> assertThat(deletedCustomer).isNull())
                .join();
    }

    @Test
    @Order(23)
    void getCustomerAddress() {
        //when
        PagePublisher<Customer> customerPagePublisher = customerRepository.getCustomerAddress(customerID);

        //then
        customerPagePublisher
                .items()
                .subscribe(
                        customer -> assertAll(
                                () -> log.info("Customer is `{}`", customer),
                                () -> assertThat(customer.getAddress()).hasNoNullFieldsOrProperties(),
                                () -> assertThat(customer).hasAllNullFieldsOrPropertiesExcept("address")
                        ))
                .join();
    }

    @Test
    @Order(24)
    void getCustomerAddress_whenAbsent() {
        //when
        PagePublisher<Customer> customerPagePublisher = customerRepository.getCustomerAddress("non existing customer");

        //then
        customerPagePublisher
                .items()
                .subscribe(customer -> assertThat(customer).isNull())
                .join();
    }

    @Test
    @Order(26)
    void getAllCustomers() {
        //when
        PagePublisher<Customer> customerPagePublisher = customerRepository.getAllCustomers();

        //then
        assertTimeoutPreemptively(
                Duration.ofSeconds(1),
                () -> customerPagePublisher
                        .subscribe(
                                page -> assertThat(page.items())
                                        .hasSizeGreaterThanOrEqualTo(1)
                                        .allSatisfy(customer -> log.info("{}", customer))
                                        .allSatisfy(customer -> assertThat(customer).hasNoNullFieldsOrProperties()))
                        .join());
    }
}