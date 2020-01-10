package com.techedge.ias.service.impl;

import com.techedge.ias.data.entity.InvestmentPortfolio;
import com.techedge.ias.data.repository.InvestmentPortfolioRepository;
import com.techedge.ias.model.InvestmentPortfolioDetail;
import com.techedge.ias.service.UserService;
import com.techedge.ias.test.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

//https://www.baeldung.com/spring-boot-testing

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class InvestmentPortfolioServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private InvestmentPortfolioRepository investmentPortfolioRepository;

    @Before
    public void setUserRepository() {
        InvestmentPortfolio investmentPortfolio = new InvestmentPortfolio("Murugesh", "Kumar");
        when(investmentPortfolioRepository.findAll()).thenReturn(Collections.singletonList(investmentPortfolio));
    }

    @Test
    public void whenUserFoundThenShouldReturn() {
        List<InvestmentPortfolioDetail> users = userService.getAllInvestments();
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("Murugesh", users.get(0).getCustomerID());
        assertEquals("Kumar", users.get(0).getAccountID());

    }
}
