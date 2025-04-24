package mx.aplazo.demo.loans.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class LoanRequest {

    @NotBlank(message = "Customer id cannot be null")
    private String customerId;

    @DecimalMin(value = "0.01", message = "The amount must be greater than 0")
    private double amount;
}
