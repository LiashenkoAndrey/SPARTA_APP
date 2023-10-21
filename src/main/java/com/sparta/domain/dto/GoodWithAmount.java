package com.sparta.domain.dto;

import com.sparta.domain.Good;
import com.sparta.domain.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "good_amount")
public class GoodWithAmount {

    public GoodWithAmount(Good good, Integer amount) {
        this.good = good;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Good good;

    @NotNull
    private Integer amount;
}
