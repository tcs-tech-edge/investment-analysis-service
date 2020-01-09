package com.techedge.ias.controller;

import com.techedge.ias.service.MarketDataService;
import com.techedge.ias.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mm-invest")
public class MarketPriceController {


    @Autowired
    MarketDataService marketService;

    @CrossOrigin
    @GetMapping("/market/getQuote")
    public ResponseEntity<String> getQuote(@PathVariable("symbol") String symbol) throws  Exception{
        return ResponseEntity.ok()
                .body(marketService.getStockPrice(symbol));
    }
}
