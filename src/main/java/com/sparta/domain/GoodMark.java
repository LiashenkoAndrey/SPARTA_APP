package com.sparta.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GoodMark {

    public GoodMark(Long goodId, Long clientId, Boolean mark) {
        this.goodId = goodId;
        this.clientId = clientId;
        this.mark = mark;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long goodId;

    private Long clientId;

    private Boolean mark;
}
