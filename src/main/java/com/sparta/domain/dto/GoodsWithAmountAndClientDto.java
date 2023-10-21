package com.sparta.domain.dto;

import com.sparta.domain.Client;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GoodsWithAmountAndClientDto {

    private List<GoodWithAmount> goodWithAmountList;

    private Client client;

    private String message;
}
