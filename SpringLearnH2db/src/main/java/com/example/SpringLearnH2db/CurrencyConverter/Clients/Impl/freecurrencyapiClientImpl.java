package com.example.SpringLearnH2db.CurrencyConverter.Clients.Impl;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.SpringLearnH2db.CurrencyConverter.Clients.freecurrencyapiClient;
import com.example.SpringLearnH2db.CurrencyConverter.Dto.Currencydto;
import com.example.SpringLearnH2db.CurrencyConverter.Dto.Datadto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class freecurrencyapiClientImpl implements freecurrencyapiClient {

    private final RestClient restClient;
    private final ModelMapper modalMapper;

    @Override
    public Datadto getCurrencyValue(Currencydto currencydto) {

       Object object =  restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("base_currency", currencydto.getFromCurrency())
                        .queryParam("currencies", currencydto.getToCurrency())
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, Object>>() {
                });

            return modalMapper.map(object, Datadto.class);

}

}