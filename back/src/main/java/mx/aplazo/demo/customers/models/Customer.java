package mx.aplazo.demo.customers.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String secondLastName;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private double creditLineAmount;

    @Column(nullable = false)
    private double availableCreditLineAmount;

    @Column(nullable = false, columnDefinition = "TIMESTAMPTZ")
    private Instant createdAt;
}
