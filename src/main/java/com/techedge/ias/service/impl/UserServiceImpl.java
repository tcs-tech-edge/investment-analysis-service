package com.techedge.ias.service.impl;

import com.techedge.ias.data.entity.InvestmentPortfolio;
import com.techedge.ias.data.repository.InvestmentPortfolioRepository;
import com.techedge.ias.mapper.InvestmentPortfolioMapper;
import com.techedge.ias.model.InvestmentPortfolioDetail;
import com.techedge.ias.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private InvestmentPortfolioRepository investmentPortfolioRepository;

    @Autowired
    InvestmentPortfolioMapper investmentPortfolioMapper;

    @Override
    public InvestmentPortfolioDetail addInvestment(InvestmentPortfolioDetail investmentPortfolioDetail) {
        InvestmentPortfolio investmentPortfolio = investmentPortfolioMapper.map(investmentPortfolioDetail);
        investmentPortfolioRepository.save(investmentPortfolio);
        return investmentPortfolioDetail;
    }

    @Override
    public List<InvestmentPortfolioDetail> getAllInvestments() {
        List<InvestmentPortfolioDetail> investmentPortfolioDetails = new ArrayList<>();
        investmentPortfolioRepository.findAll().forEach(user -> investmentPortfolioDetails.add(investmentPortfolioMapper.map(user)));
        return investmentPortfolioDetails;
    }
}
