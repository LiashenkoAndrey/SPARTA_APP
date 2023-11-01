package com.sparta.domain;

import com.sparta.domain.dto.GoodWithAmount;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client_order")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @NotNull
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "client_order_id")
    @NotNull
    private List<GoodWithAmount> goodsWithAmount = new ArrayList<>();

    private String message;

    @NotNull
    private String methodOfDelivery;

    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreationTimestamp
    private Date createdOn;
}
