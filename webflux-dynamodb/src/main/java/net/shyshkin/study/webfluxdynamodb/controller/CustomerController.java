package net.shyshkin.study.webfluxdynamodb.controller;

import lombok.RequiredArgsConstructor;
import net.shyshkin.study.webfluxdynamodb.domain.Address;
import net.shyshkin.study.webfluxdynamodb.domain.Customer;
import net.shyshkin.study.webfluxdynamodb.domain.Result;
import net.shyshkin.study.webfluxdynamodb.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping("/save")//C
    public Mono<Result> saveCustomer(@RequestBody Customer customer) {
        return customerService.createNewCustomer(customer);
    }

    @GetMapping("/get/{customerId}")//R
    public Mono<Customer> getCustomer(@PathVariable("customerId") String customerId) {
        return customerService.getCustomerByCustomerId(customerId);
    }

    @PutMapping("/updateCustomerOrCreate")//U
    public Mono<Result> updateOrCreateCustomer(@RequestBody Customer customer) {
        return customerService.updateExistingOrCreateCustomer(customer);
    }

    @DeleteMapping("/delete/{customerId}")//D
    public Mono<Result> deleteCustomer(@PathVariable("customerId") String customerId) {
        return customerService.deleteCustomerByCustomerId(customerId);
    }

    @PutMapping("/updateCustomer")
    public Mono<Result> updateCustomer(@RequestBody Customer customer) {
        return customerService.updateExistingCustomer(customer);
    }

    @GetMapping("/query/{customerId}")
    public Mono<Address> queryCustomerAddress(@PathVariable("customerId") String customerId) {
        return customerService.queryAddressByCustomerId(customerId);
    }

    @GetMapping("/allCustomerList")
    public Flux<Customer> getAllCustomer() {
        return customerService.getCustomerList();
    }



}
