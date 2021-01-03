package net.shyshkin.study.webfluxdynamodb.repository;

import lombok.extern.slf4j.Slf4j;
import net.shyshkin.study.webfluxdynamodb.domain.Address;
import net.shyshkin.study.webfluxdynamodb.domain.Customer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    private static String customerID;

    @BeforeAll
    static void beforeAll() {
//        customerID = UUID.randomUUID().toString();
        customerID = "c6bd4b74-245f-4b48-a08c-3ea83f60a43d";
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
                .createdTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
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
                .createdTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
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
    @Order(26)
    void getAllCustomers() {
        //when
        PagePublisher<Customer> customerPagePublisher = customerRepository.getAllCustomers();

        //then
        customerPagePublisher
                .subscribe(
                        page -> assertThat(page.items())
                                .hasSizeGreaterThanOrEqualTo(1)
                                .allSatisfy(customer -> log.info("{}", customer))
                                .allSatisfy(customer -> assertThat(customer).hasNoNullFieldsOrProperties()))
                .join();
    }
}