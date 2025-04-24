package mx.aplazo.demo.customers.controller;

import mx.aplazo.demo.auth.JwtUtil;
import mx.aplazo.demo.customers.models.CustomerRequest;
import mx.aplazo.demo.customers.models.CustomerResponse;
import mx.aplazo.demo.customers.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        String token = jwtUtil.generateToken(customerResponse.getId());
        URI location = URI.create("/v1/customers/" + customerResponse.getId());
        return ResponseEntity.created(location)
                .header("X-Auth-Token", token)
                .body(customerResponse);
    }

    @GetMapping("/{customerIdF}")
    public CustomerResponse findById(@PathVariable UUID customerIdF, @AuthenticationPrincipal UUID customerId) {
        return customerService.findCustomerById(customerIdF);
    }


}
