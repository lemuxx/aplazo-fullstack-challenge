package mx.aplazo.demo.loans.controller;

import mx.aplazo.demo.loans.models.LoanRequest;
import mx.aplazo.demo.loans.models.LoanResponse;
import mx.aplazo.demo.loans.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponse> create(@Validated @RequestBody LoanRequest loanRequest) {
        LoanResponse loanResponse = loanService.createLoan(loanRequest);
        URI location = URI.create("/v1/loans/" + loanResponse.getId());
        return ResponseEntity.created(location)
                .body(loanResponse);
    }

    @GetMapping("/{loanId}")
    public LoanResponse findById(@PathVariable UUID loanId, @AuthenticationPrincipal UUID customerId) {
        return loanService.findLoanById(loanId);
    }
}
