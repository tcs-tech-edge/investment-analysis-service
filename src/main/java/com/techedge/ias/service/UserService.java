package com.techedge.ias.service;

import com.techedge.ias.model.InvestmentPortfolioDetail;

import java.util.List;

public interface UserService {

    InvestmentPortfolioDetail addInvestment(InvestmentPortfolioDetail investmentPortfolioDetail);

    List<InvestmentPortfolioDetail> getAllInvestments();
}
