package com.example.currencies.controller;

import com.example.currencies.model.CurrencyRequest;
import com.example.currencies.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {this.currencyService = currencyService;}

    @PostMapping("/get-current-currency-value-command")
    public ResponseEntity<CurrencyRequest> getActualCurrencyValue(@RequestBody CurrencyRequest req){
        CurrencyRequest res = currencyService.getActualCurrencyValue(req);
        if(res != null){
            return ResponseEntity.ok(res);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/requests")
    public List<CurrencyRequest> getAllDatabaseRequests(){
        return (List<CurrencyRequest>) currencyService.getAllDatabaseRequests();
    }

}
