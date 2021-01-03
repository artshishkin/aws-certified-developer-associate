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
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping//C
    public Mono<Result> saveCustomer(@RequestBody Customer customer) {
        return customerService.createNewCustomer(customer);
    }

    @GetMapping("{customerId}")//R
    public Mono<Customer> getCustomer(@PathVariable("customerId") String customerId) {
        return customerService.getCustomerByCustomerId(customerId);
    }

    @DeleteMapping("{customerId}")//D
    public Mono<Result> deleteCustomer(@PathVariable("customerId") String customerId) {
        return customerService.deleteCustomerByCustomerId(customerId);
    }

    @PutMapping("{customerId}")//U
    public Mono<Result> updateCustomer(@PathVariable("customerId") String customerId, @RequestBody Customer customer) {
        customer.setCustomerID(customerId);
        return customerService.updateCustomer(customer);
    }

    @PatchMapping("{customerId}")
    public Mono<Result> updateCustomerFields(@PathVariable("customerId") String customerId, @RequestBody Customer customer) {
        customer.setCustomerID(customerId);
        return customerService.updateCustomerFields(customer);
    }

    @GetMapping("{customerId}/address")
    public Mono<Address> queryCustomerAddress(@PathVariable("customerId") String customerId) {
        return customerService.queryAddressByCustomerId(customerId);
    }

    @GetMapping
    public Flux<Customer> getAllCustomer() {
        return customerService.getCustomerList();
    }
}
