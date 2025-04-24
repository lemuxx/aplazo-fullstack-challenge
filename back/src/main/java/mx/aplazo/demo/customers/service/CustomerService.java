package mx.aplazo.demo.customers.service;

import mx.aplazo.demo.customers.exception.CustomerNotFoundException;
import mx.aplazo.demo.customers.models.Customer;
import mx.aplazo.demo.customers.models.CustomerRequest;
import mx.aplazo.demo.customers.models.CustomerResponse;
import mx.aplazo.demo.customers.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;


@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponse createCustomer (CustomerRequest createCustomerRequest) {
        LOGGER.info("-------> Creating customer with name: " + createCustomerRequest.getFirstName() + " " + createCustomerRequest.getLastName());
        Customer customer = new Customer();
        customer.setFirstName(createCustomerRequest.getFirstName());
        customer.setLastName(createCustomerRequest.getLastName());
        customer.setSecondLastName(createCustomerRequest.getSecondLastName());
        customer.setDateOfBirth(createCustomerRequest.getDateOfBirth());
        customer.setCreditLineAmount(0);
        customer.setAvailableCreditLineAmount(100);
        customer.setCreatedAt(Instant.now());
        customerRepository.save(customer);
        LOGGER.info("-------> User created successfully with id: " + getCustomerResponse(customer).getId());
        return getCustomerResponse(customer);
    }


    public CustomerResponse findCustomerById(UUID customerId){
        LOGGER.info("-------> Finding customer with id: " + customerId);
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> {
                    String message = "Customer with id " + customerId + " not found";
                    LOGGER.error("-------> " + message);
                    return new CustomerNotFoundException(message);
                }
        );
        return getCustomerResponse(customer);
    }



    private CustomerResponse getCustomerResponse(Customer customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getCreditLineAmount(),
                customer.getAvailableCreditLineAmount(),
                customer.getCreatedAt()
        );
    }
}
