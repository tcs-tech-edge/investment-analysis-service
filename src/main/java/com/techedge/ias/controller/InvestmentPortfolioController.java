package com.techedge.ias.controller;

import com.techedge.ias.model.InvestmentPortfolioDetail;
import com.techedge.ias.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvestmentPortfolioController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping("/investment/list")
    public List<InvestmentPortfolioDetail> getAllInvestments() {
        return userService.getAllInvestments();
    }

    @CrossOrigin
    @PostMapping(value = "/investment/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public InvestmentPortfolioDetail addInvestment(@RequestBody InvestmentPortfolioDetail investmentPortfolioDetail) {
        return userService.addInvestment(investmentPortfolioDetail);
    }

}

