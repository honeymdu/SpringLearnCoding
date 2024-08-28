package com.example.SpringLearnH2db.CurrencyConverter.Clients;

import com.example.SpringLearnH2db.CurrencyConverter.Dto.Currencydto;
import com.example.SpringLearnH2db.CurrencyConverter.Dto.Datadto;

public interface freecurrencyapiClient {

    Datadto getCurrencyValue(Currencydto currencydto);

}
