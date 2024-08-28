package com.example.SpringLearnH2db.CurrencyConverter.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringLearnH2db.CurrencyConverter.Clients.freecurrencyapiClient;
import com.example.SpringLearnH2db.CurrencyConverter.Dto.Currencydto;
import com.example.SpringLearnH2db.CurrencyConverter.Dto.Datadto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final freecurrencyapiClient freecurrencyapiClient;

    @GetMapping("/convertCurrency")
    public ResponseEntity<?> convertCurrency(@RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam Integer units) {
        try {
            Currencydto currencydto = Currencydto.builder()
                    .fromCurrency(fromCurrency)
                    .toCurrency(toCurrency)
                    .build();

            Datadto datadto = freecurrencyapiClient.getCurrencyValue(currencydto);

            if (datadto == null || datadto.getData() == null || !datadto.getData().containsKey(toCurrency)) {
                return ResponseEntity.status(404).body("Currency conversion data not found for " + toCurrency);
            }

            double conversionRate = datadto.getData().get(toCurrency).doubleValue();
            currencydto.setConvertedvalue(units * conversionRate);
            currencydto.setCurrencyValue(conversionRate);

            return ResponseEntity.ok(currencydto);

        } catch (Exception e) {
            // Log the error for debugging purposes
            return ResponseEntity.status(500).body("An error occurred while converting currency: " + e.getMessage());
        }
    }

}
