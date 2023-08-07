package com.example.currencies.service;

import com.example.currencies.model.CurrencyRequest;
import com.example.currencies.model.CurrencyResponse;
import com.example.currencies.model.Rate;
import com.example.currencies.repository.CurrencyRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CurrencyService {


    private final CurrencyRequestRepository requestRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public CurrencyService(CurrencyRequestRepository requestRepository){this.requestRepository = requestRepository;}

    private Date convertToLocalDateTime(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public CurrencyRequest getActualCurrencyValue(CurrencyRequest request){
        String currencyCode = request.getCurrency();
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/" + currencyCode + "?format=json";

        try{
            CurrencyResponse currencyResponse = restTemplate.getForObject(url, CurrencyResponse.class);
            if(currencyResponse != null){
                Double value = currencyResponse.getRates().get(0).getMid();

                CurrencyRequest savedRequest = requestRepository.save(new CurrencyRequest(
                        null,
                        request.getCurrency(),
                        request.getName(),
                        value,
                        convertToLocalDateTime(LocalDateTime.now())));
                return savedRequest;
            }
        }catch(Exception e){e.printStackTrace();}

        return null;
    }

    public Iterable<CurrencyRequest> getAllDatabaseRequests() {
        return requestRepository.findAll();
    }
}
