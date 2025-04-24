package mx.aplazo.demo.loans.service;

import jakarta.transaction.Transactional;
import mx.aplazo.demo.auth.exception.UnauthorizedException;
import mx.aplazo.demo.customers.models.Customer;
import mx.aplazo.demo.customers.repository.CustomerRepository;
import mx.aplazo.demo.loans.enums.LoanStatus;
import mx.aplazo.demo.loans.exception.InvalidLoanRequestException;
import mx.aplazo.demo.loans.exception.LoanNotFoundException;
import mx.aplazo.demo.loans.models.Loan;
import mx.aplazo.demo.loans.models.LoanRequest;
import mx.aplazo.demo.loans.models.LoanResponse;
import mx.aplazo.demo.loans.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class LoanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanService.class);

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public LoanResponse createLoan(LoanRequest loanRequest){
        LOGGER.info("-------> Creation loan for customer: " + loanRequest.getCustomerId());
        /*
        if(!customerId.toString().equals(loanRequest.getCustomerId())){
            throw new UnauthorizedException("The customer does'nt match authenticated customer");
        }
        */

        Customer customer = customerRepository.findById(UUID.fromString(loanRequest.getCustomerId()))
                .orElseThrow(() -> {
                    String message = "Customer not found. Customer id " + loanRequest.getCustomerId();
                    LOGGER.error("-------> " + message);
                    return new UnauthorizedException(message);
                });

        if (loanRequest.getAmount() > customer.getAvailableCreditLineAmount()) {
            String message = "The loan amount exceeds available credit line amount. The available credit line amount is: " + customer.getAvailableCreditLineAmount();
            LOGGER.error("-------> " + message);
            throw new InvalidLoanRequestException(message);
        }

        Loan loan = new Loan();
        loan.setAmount(loanRequest.getAmount());
        loan.setCustomer(customer);
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setCreatedAt(Instant.now());
        Loan newLoan = loanRepository.save(loan);

        customer.setAvailableCreditLineAmount(
                customer.getAvailableCreditLineAmount() - loanRequest.getAmount());
        customerRepository.save(customer);
        LOGGER.info("-------> Loan created successfully");
        return getLoanResponse(loan);
    }

    public LoanResponse findLoanById(UUID loanId){
        LOGGER.info("-------> Finding loan with id: " + loanId);
        Loan loan = loanRepository.findById(loanId).orElseThrow(
                () -> {
                    String message = "Loan with id " + loanId + " not found";
                    LOGGER.error("-------> " + message);
                    return new LoanNotFoundException(message);
                }
        );
        /*
        UUID loanCustomerId = loan.getCustomer().getId();
        if (!customerId.equals(loanCustomerId)) {
            String message = "The customer with id " + customerId + " does'nt match authenticated customer";
            LOGGER.error("-------> " + message);
            throw new UnauthorizedException("The customer with id " + customerId + " does'nt match authenticated customer");
        }*/
        return getLoanResponse(loan);
    }


    private LoanResponse getLoanResponse(Loan loan){
        return new LoanResponse(
                loan.getId(),
                loan.getCustomer().getId(),
                loan.getAmount(), loan.getCreatedAt()
        );
    }
}
