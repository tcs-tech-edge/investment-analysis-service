package com.techedge.ias.controller;

import com.techedge.ias.service.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mm-invest")
public class MarketPriceController {


    @Autowired
    MarketDataService marketService;

    @CrossOrigin
    @GetMapping("/market/getQuote/{symbol}")
    public ResponseEntity<String> getQuote(@PathVariable String symbol) throws  Exception{
        return ResponseEntity.ok()
                .body(marketService.getStockPrice(symbol));
    }
}
