package com.learnspring.hello_spring.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fee")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "amount_paid", nullable = false)
    BigDecimal amountPaid;

    @Column(name = "payment_date")
    LocalDateTime paymentDate = LocalDateTime.now();

    @Column(name = "is_paid", nullable = false)
    boolean isPaid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id", unique = true)
    Student student;
}
