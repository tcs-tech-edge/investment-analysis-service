package com.techedge.ias.mapper;

import com.techedge.ias.data.entity.InvestmentPortfolio;
import com.techedge.ias.model.InvestmentPortfolioDetail;
import org.springframework.stereotype.Component;

@Component
public class InvestmentPortfolioMapper {

    public InvestmentPortfolio map(InvestmentPortfolioDetail investmentPortfolioDetail) {
        InvestmentPortfolio investmentPortfolio = new InvestmentPortfolio();
        investmentPortfolio.setCustomerID(investmentPortfolioDetail.getCustomerID());
        investmentPortfolio.setAccountID(investmentPortfolioDetail.getAccountID());
        return investmentPortfolio;
    }

    public InvestmentPortfolioDetail map(InvestmentPortfolio investmentPortfolio) {
        InvestmentPortfolioDetail investmentPortfolioDetail = new InvestmentPortfolioDetail();
        investmentPortfolioDetail.setCustomerID(investmentPortfolio.getCustomerID());
        investmentPortfolioDetail.setAccountID(investmentPortfolio.getAccountID());
        return investmentPortfolioDetail;
    }
}
