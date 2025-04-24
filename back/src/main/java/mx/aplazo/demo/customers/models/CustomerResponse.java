package mx.aplazo.demo.customers.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CustomerResponse {

    private UUID id;
    private double creditLineAmount;
    private double availableCreditLineAmount;
    private Instant createdAt;

}
