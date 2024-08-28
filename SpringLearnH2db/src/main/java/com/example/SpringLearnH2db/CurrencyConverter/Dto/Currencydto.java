package com.example.SpringLearnH2db.CurrencyConverter.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Currencydto {

    private String fromCurrency;
    private String toCurrency;
    private double currencyValue;
    private double convertedvalue;

}
