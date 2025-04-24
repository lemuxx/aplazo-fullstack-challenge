package mx.aplazo.demo.loans.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LoanResponse {
    private UUID id;
    private UUID customerId;
    private double amount;
    private Instant createdAt;


}
