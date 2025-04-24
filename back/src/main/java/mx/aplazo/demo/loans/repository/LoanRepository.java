package mx.aplazo.demo.loans.repository;

import mx.aplazo.demo.loans.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {
    Optional<Loan> findById(UUID loanId);
}
