package com.sparta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Good {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max = 255)
    private String name;

    @NotNull
    private String image_url;

    @NotNull
    private BigDecimal price;

    @Formula("(select count(*) from good_mark g where g.good_id = ID and g.mark = true)")
    private Integer likes;

    @Formula("(select count(*) from good_mark g where g.good_id = ID and g.mark = false)")
    private Integer dislikes;
}
