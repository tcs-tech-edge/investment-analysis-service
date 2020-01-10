package com.techedge.ias.data.repository;

import com.techedge.ias.data.entity.InvestmentPortfolio;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface InvestmentPortfolioRepository extends CrudRepository<InvestmentPortfolio, String> {

}
