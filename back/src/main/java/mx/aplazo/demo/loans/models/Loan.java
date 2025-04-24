package mx.aplazo.demo.loans.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mx.aplazo.demo.customers.models.Customer;
import mx.aplazo.demo.loans.enums.LoanStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "loans")
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @Column(nullable = false, columnDefinition = "TIMESTAMPTZ")
    private Instant createdAt;


}
